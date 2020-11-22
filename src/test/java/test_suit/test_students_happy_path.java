package test_suit;

import api.Students;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.Student;
import setup.HttpStatusCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class test_students_happy_path {

    //private static Logger LOG = LoggerFactory.getLogger(test_students_happy_path.class);
    private final static Logger LOG = Logger.getLogger(test_students_happy_path.class.getName());

    @Test
    public void testGetAllStudentRecords() {
        LOG.info("Step - 1 : Send GET Request");
        Response response = Students.getAllStudents();

        LOG.info("Step - 2 : Print the JSON response body");
        response.getBody().prettyPrint();

        LOG.info("Step - 3 : Assert StatusCode = 200");
        assertEquals(response.getStatusCode(), HttpStatusCode.OK, "http status code" + response.getStatusCode());

        LOG.info("Step - 4 : Validate total records");
        //TODO: add validation
    }

    @Test
    public void testFileUpload() throws IOException {
        File file = new File("D:\\IntelliJProjects\\APIAutomationFrameworkJava\\src\\test\\resources\\students.json");

        Response response = Students.uploadStudentsInfo(file);
        assertEquals(response.getStatusCode(), HttpStatusCode.CREATED, "Upload students info fail");
        response.getBody().prettyPrint();
    }

    @Test
    public void testFileDownload() throws IOException {
        Response response = Students.getAllStudents();
        assertEquals(response.getStatusCode(), HttpStatusCode.OK, "http status code " + response.getStatusCode());

        byte[] bytes = response.getBody().asByteArray();
        assertTrue(bytes.length > 0, "response content length is 0");

        // writing the byte[] to file
        File file = new File("D:\\IntelliJProjects\\APIAutomationFrameworkJava\\src\\test\\resources\\students_output.json");
        Files.write(file.toPath(), bytes);

        // validating the existence and size of file
        assertTrue(file.exists(), "file " + file + " does not exist");
        assertTrue(file.length() > 0, "file size is 0");
        assertEquals(bytes.length, file.length(), "file size and response content");

        String content = new String(Files.readAllBytes(file.toPath()));
        LOG.info("printing content of the file => {}" + content);
    }

}
