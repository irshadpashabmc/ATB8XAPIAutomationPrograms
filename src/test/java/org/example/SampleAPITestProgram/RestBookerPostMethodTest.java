package org.example.SampleAPITestProgram;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RestBookerPostMethodTest {

    @Description("Verifying the post method of booking")
    @Test
    public void getBookingid() {

        String Base_URL = "https://restful-booker.herokuapp.com";
        String Base_path = "/booking";
        String Req_payload = "{\n" +
                "    \"firstname\" : \"Prhamod\",\n" +
                "    \"lastname\" : \"Duthta\",\n" +
                "    \"totalprice\" : 1121,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        String Booking_id;
        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Res_Val;


        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_path);
        Req_Spec.contentType(ContentType.JSON);
        Req_Spec.body(Req_payload);

        Res = Req_Spec.log().all().when().post();

        // verify the created bookingid
        Booking_id = Res.jsonPath().getString("bookingid");
        System.out.println("Created booking id is :" +Booking_id);

        // Verify the validation
        Res_Val = Res.then().statusCode(200);

    }



}
