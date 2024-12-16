package org.example.SampleAPITestProgram;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class Assertionstest {

    @Description("Verifying the assertions hamcrust, testng, asertj using post method of booking")
    @Test
    public void getBookingid() {

        String Base_URL = "https://restful-booker.herokuapp.com";
        String Base_path = "/booking";
        String Req_payload = "{\n" +
                "    \"firstname\" : \"Rajesh\",\n" +
                "    \"lastname\" : \"Reddy\",\n" +
                "    \"totalprice\" : 1131,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Integer Booking_id;
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
        Booking_id = Res.jsonPath().getInt("bookingid");
        System.out.println("Created booking id is :" +Booking_id);
      //  String First_Name = Res.jsonPath().getString("firstname");
     //   String Last_Name = Res.jsonPath().getString("lastname");

        // Verify the validation
        Res_Val = Res.then().log().all();
        Res_Val.statusCode(200);
        Res_Val.contentType(ContentType.JSON);
     //  Res_Val.cookie("name",Matchers.equalTo("sdsds"));

        // Assertions for hamcrust practice

        Res_Val.body("bookingid", Matchers.equalTo(Booking_id));
        Res_Val.body("booking.firstname", Matchers.equalTo("Rajesh"));
        Res_Val.body("booking.lastname", Matchers.equalTo("Reddy"));

        // Testng Assertions Practice

        Integer TestngBookingid = Res_Val.extract().path("bookingid");
        String TestngFirstName = Res_Val.extract().path("booking.firstname");
        String TestngLastName = Res_Val.extract().path("booking.lastname");

        System.out.println("this is testngbookingid:"+TestngBookingid);
        System.out.println("this is testngfirstname:"+TestngFirstName);
        System.out.println("this is testnglastname:"+TestngLastName);

        Assert.assertNotNull(TestngBookingid);
        Assert.assertEquals(TestngBookingid,Booking_id);
        Assert.assertEquals(TestngFirstName,"Rajesh");
        Assert.assertEquals(TestngLastName,"Reddy");

        // Assertions using Assertj
        assertThat(TestngFirstName).isEqualTo("Rajesh");
        assertThat(TestngLastName).isEqualTo("Reddy");
        assertThat(TestngBookingid).isEqualTo(Booking_id);

    }

}
