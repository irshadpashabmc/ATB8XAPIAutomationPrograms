package org.example.SampleAPITestProgram;

import io.restassured.RestAssured;

public class APITest_001 {

    public static void main(String[] args) {
        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .when()
                .get()
                .then()
                .log()
                .all()
                .statusCode(200);

    }
}
