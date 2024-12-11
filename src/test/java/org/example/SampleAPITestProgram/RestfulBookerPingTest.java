package org.example.SampleAPITestProgram;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RestfulBookerPingTest {

    // verify the health of the Restful_Booker

    @Description("Verify the Restful_Booker Working or not")
    @Test
    public void RestfulBookerPingTestCase() {

        String Base_URL = "https://restful-booker.herokuapp.com";
        String Base_path = "/ping";

        RequestSpecification Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_path);

        Response Res = Req_Spec.when().get();

        ValidatableResponse VR = Res.then();
        VR.log().all().statusCode(201);

    }

}
