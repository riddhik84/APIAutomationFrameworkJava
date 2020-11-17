package test_suit;

import api.Students;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import setup.HttpStatusCode;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class test_students_happy_path {

    //private static Logger LOG = LoggerFactory.getLogger(test_students_happy_path.class);
    private final static Logger LOG = Logger.getLogger(test_students_happy_path.class.getName());

    @Test
    public void getAllStudentRecords() {
        LOG.info("Step - 1 : Send GET Request");
        Response response = Students.getAllStudents();

        LOG.info("Step - 2 : Print the JSON response body");
        response.getBody().prettyPrint();

        LOG.info("Step - 3 : Assert StatusCode = 200");
        assertEquals(response.getStatusCode(), HttpStatusCode.OK, "http status code" + response.getStatusCode());

        LOG.info("Step - 4 : Validate total records");

    }

}
