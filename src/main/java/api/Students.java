package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.Student;
import setup.Environment;
import setup.EnvironmentConfigs;

public class Students {
    static EnvironmentConfigs config = new EnvironmentConfigs(Environment.TEST);

    public static Response getAllStudents() {
        String url = config.getURL() + "educative-rest/students";
        return RestAssured.given().get(url).andReturn();
    }

    public static Response getStudentInfo(int studentId) {
        String url = config.getURL() + "educative-rest/students/" + studentId;
        return RestAssured.given().get(url).andReturn();
    }

    public static Response createStudent(Student student) {
        String url = config.getURL() + "educative-rest/students";
        Response response = RestAssured.given()
                .contentType("application/json")
                .log().all(true)
                .accept("application/json")
                .body(student)
                .post(url)
                .andReturn();

        return response;
    }

    public static Response updateStudent(Student student, int studentID) {
        String url = config.getURL() + "educative-rest/students/" + studentID;
        Response response = RestAssured.given()
                .contentType("application/json")
                .log().all(true)
                .accept("application/json")
                .body(student)
                .put(url)
                .andReturn();

        return response;
    }

    public static Response deleteStudent(int studentId) {
        String url = config.getURL() + "educative-rest/students/" + studentId;
        return RestAssured.given().delete(url).andReturn();
    }
}
