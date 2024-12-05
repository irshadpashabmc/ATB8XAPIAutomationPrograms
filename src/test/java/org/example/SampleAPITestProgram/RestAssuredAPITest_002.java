package org.example.SampleAPITestProgram;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class RestAssuredAPITest_002 {

    // BDD Style

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the BDDStyle Testcase")
    @Test
    public void APITesting_BDDStyle () {

        String Pincode = "560037";

        RestAssured.given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+Pincode)
                .when()
                .log().all().get()
                .then()
                .log().all()
                .statusCode(200);

    }
}
