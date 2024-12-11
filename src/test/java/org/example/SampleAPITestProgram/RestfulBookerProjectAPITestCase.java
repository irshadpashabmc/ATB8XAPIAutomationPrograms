package org.example.SampleAPITestProgram;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class RestfulBookerProjectAPITestCase {

    String Token;
    String BookingId;
    RequestSpecification Req_Spec;
    Response Res;
    ValidatableResponse ValRes;

  //  @Description("Create Token using Post method ")

    public String getToken() {

        String Base_URL = "https://restful-booker.herokuapp.com";
        String Base_path = "/auth";
        String ReqPayload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_path);
        Req_Spec.contentType(ContentType.JSON).log().all();
        Req_Spec.body(ReqPayload);

        Res = Req_Spec.when().post();

        // Capture Token
        Token = Res.jsonPath().getString("token");
        System.out.println("Capture Token is:" +Token);

        // Verify the Status Code
        ValRes = Res.then();
        ValRes.log().all().statusCode(200);

        return Token;
    }

    // @Description("Create BookingID using Post method ")

    public String getBookingid() {
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

        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_path);
        Req_Spec.contentType(ContentType.JSON);
        Req_Spec.body(Req_payload).log().all();

        Res = Req_Spec.when().post();

        // Get Validatable response to perform validation
        ValRes = Res.then().log().all();
        ValRes.statusCode(200);

        BookingId = Res.jsonPath().getString("bookingid");
        System.out.println(BookingId);

        return BookingId;

    }
    @Description("Verify the Created BookingID Response using Get Method")
    @Test
    public void VerifyBookingIDAndToken() {

        String Token_val = getToken();
        String Booking_ID = getBookingid();

        System.out.println(Token_val);
        System.out.println(Booking_ID);

    }

}
