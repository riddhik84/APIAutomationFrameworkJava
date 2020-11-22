package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Student;
import setup.Environment;
import setup.EnvironmentConfigs;

import java.io.File;

public class Students {
    static EnvironmentConfigs config = new EnvironmentConfigs(Environment.TEST);
    static String username = config.getUsername();
    static String password = config.getPassword();

    public static Response getAllStudents() {
        return RestAssured.given()
                .log().all(true)
                .spec(getRequestSpecification())
                .get()
                .andReturn();
    }

    public static Response getStudentInfo(int studentId) {
        String url = config.getURL() + "educative-rest/auth/students/" + studentId;
        return RestAssured.given()
                .log().all(true)
                .auth().basic(username, password)
                .get(url)
                .andReturn();
    }

    public static Student getStudentInfoAsStudent(int studentId) {
        String url = config.getURL() + "educative-rest/auth/students/" + studentId;
        return RestAssured.given()
                .log().all(true)
                .auth().basic(username, password)
                .get(url)
                .as(Student.class);
    }

    public static Response createStudent(Student student) {
        String url = config.getURL() + "educative-rest/auth/students";
        return RestAssured.given()
                .log().all(true)
                .auth().basic(username, password)
                .contentType("application/json")
                .accept("application/json")
                .body(student)
                .post(url)
                .andReturn();
    }

    public static Response updateStudent(Student student, int studentID) {
        String url = config.getURL() + "educative-rest/auth/students/" + studentID;
        return RestAssured.given()
                .log().all(true)
                .auth().basic(username, password)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(student)
                .put(url)
                .andReturn();
    }

    public static Response deleteStudent(int studentId) {
        String url = config.getURL() + "educative-rest/auth/students/" + studentId;
        return RestAssured.given()
                .log().all(true)
                .auth().basic(username, password)
                .delete(url)
                .andReturn();
    }

    public static Response uploadStudentsInfo(File json_file) {
        String url = config.getURL() + "educative-rest/auth/students/upload";
        return RestAssured.given()
                .multiPart("file", json_file)
                .log().all()
                .auth().basic(username, password)
                .post(url)
                .thenReturn();
    }

    public static RequestSpecification getRequestSpecification() {
        String authCode = "Basic dGVzdHVzZXI6dGVzdHBhc3M=";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("accept", "application/json");
        builder.addHeader("content-type", "application/json");
        builder.addHeader("authorization", authCode);
        builder.setBaseUri(config.getURL());
        builder.setBasePath("/educative-rest/auth/students");

        RequestSpecification requestSpec = builder.build();
        return requestSpec;
    }
}
