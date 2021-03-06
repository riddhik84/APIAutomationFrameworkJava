package io.automation.test_suit;

import io.automation.api.Students;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.automation.model.Student;
import io.automation.config_manager.HttpStatusCode;

import java.util.logging.Logger;

import static org.testng.Assert.*;

public class test_workflow {

    private final static Logger LOG = Logger.getLogger(test_workflow.class.getName());
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void test_students_workflow() throws JsonProcessingException {

        Response response = null;
        int newStudentID = 0;

        LOG.info("--> Step 1 - Get all students list");
        response = Students.getAllStudents();
        response.getBody().prettyPrint();
        assertEquals(response.getStatusCode(), HttpStatusCode.OK, "http status code " + response.getStatusCode());
        //assertTrue(response.getBody().jsonPath().getList("id").contains(101));

        LOG.info("--> Step 2 - Create a new student info");
        Student student = new Student("Sam", "Bailey", "Female");
        byte[] data = MAPPER.writeValueAsBytes(student);
        String json = MAPPER.writeValueAsString(student);

        response = Students.createStudent(student);
        assertEquals(response.getStatusCode(), HttpStatusCode.CREATED, "http status");
        newStudentID = response.path("id");
        assertNotNull(newStudentID, "created student id is null");
        LOG.info("created student id => {}" + newStudentID);

        LOG.info("--> Step 3 - Update the new student's info");
        student = new Student("Sam", "Matrin", "Female");
        student.setId((long) newStudentID);
        response = Students.updateStudent(student, newStudentID);
        response.getBody().prettyPrint();
        assertEquals(response.getStatusCode(), HttpStatusCode.OK, "response code is " + response.getStatusCode());
        int newStudentID1 = response.path("id");
        assertEquals(newStudentID, newStudentID1, "new id is generated while updating existing student record");

        LOG.info("--> Step 4 - Get the new student's record");
        response = Students.getStudentInfo(newStudentID);
        response.getBody().prettyPrint();
        assertEquals(response.getStatusCode(), HttpStatusCode.OK, "response status code is " + response.getStatusCode());
        //deserialize and compare response
        Student studentA = Students.getStudentInfoAsStudent(newStudentID);
        assertEquals(studentA.getLastName(), "Matrin", "Last name is not updated " + studentA.getLastName());

        LOG.info("Step 5 - Get all students records, count should be +1");

        LOG.info("Step 6 - Delete 1 student record");
        response = Students.deleteStudent(newStudentID);
        assertEquals(response.getStatusCode(), HttpStatusCode.NO_CONTENT, "Student record is not deleted");

        LOG.info("Step 7 - Get all students records, count should be -1");
    }
}
