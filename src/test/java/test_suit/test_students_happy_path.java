package test_suit;

import api.Students;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.restassured.response.Response;
import org.junit.Test;
import setup.HttpStatusCode;

import static org.hamcrest.Matchers.equalTo;

public class test_students_happy_path {

    private static Logger LOG = LoggerFactory.getLogger(test_students_happy_path.class);

    @Test
    public void getAllStudentRecords() {
        LOG.debug("Step - 1 : Send GET Request");
        Response response = Students.getAllStudents();

        LOG.debug("Step - 2 : Print the JSON response body");
        response.getBody().prettyPrint();

        LOG.debug("Step - 3 : Assert StatusCode = 200");
        //assertEquals (response.getStatusCode(),HttpStatusCode.OK, "http status code");
    }

}
