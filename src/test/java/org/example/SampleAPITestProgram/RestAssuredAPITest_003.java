package org.example.SampleAPITestProgram;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RestAssuredAPITest_003 {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the NonBDDStyle Testcase")
    @Test
    public void APITesting_NonBDDStyle() {

        RequestSpecification r = RestAssured.given();
        String Pincode = "560037";

        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/"+Pincode);
        r.when().log().all().get();
        r.then().log().all().statusCode(200);


    }


}
