package org.example.SampleAPITestProgram;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITestingUsingPOJOTest {


    RestfulIBookerAPIusingComplexPOJOTEST Body1 = new RestfulIBookerAPIusingComplexPOJOTEST();
    Bookingdates Body2 = new Bookingdates();

    @Description("Verifying POST Method of Restful Booker")
    @Step
    @Test
    public void POJOAPITest() {

        Body1.setFirstname("aguleraa");
        Body1.setLastname("Christinee");
        Body1.setTotalprice(777);
        Body1.setDepositpaid(true);
        Body2.setCheckin("2024-02-03");
        Body2.setCheckout("2024-02-04");
        Body1.setBookingdates(Body2);
        Body1.setAdditionalneeds("Lunch");

        String Base_URL = "https://restful-booker.herokuapp.com/";
        String Base_Path = "booking";
        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;
        Integer Booking_id;

        // Given()
        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.body(Body1);
        Req_Spec.contentType(ContentType.JSON);

        // when()
        Res = Req_Spec.when().log().all().post();

        Booking_id = Res.jsonPath().getInt("bookingid");
        System.out.println("Captured Bookingid is:"+Booking_id);

        Val_Res = Res.then();
        Val_Res.log().all().statusCode(200);

    }

}