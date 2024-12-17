package org.example.SampleAPITestProgram.SerializationDeserializationFramework;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SerializationDeserializationTest {

    @Owner("MdIrshadPasha")
    @Link("bookingid verification")
    @Description("Verify serialization and Deserialization Test")
    @Test
    public void PerformSerialDeserialTest() {

        String Base_URL = "https://restful-booker.herokuapp.com";
        String Base_Path= "/booking";
        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;
        Integer Booking_ID;

        Booking Booking_Pojo = new Booking();

        Booking_Pojo.setFirstname("Pramode");
        Booking_Pojo.setLastname("Dutta");
        Booking_Pojo.setTotalprice(2222);
        Booking_Pojo.setDepositpaid(true);
        Bookingdates BookingDates_Pojo = new Bookingdates();
        BookingDates_Pojo.setCheckin("2024-02-02");
        BookingDates_Pojo.setCheckout("2024-02-04");
        Booking_Pojo.setBookingdates(BookingDates_Pojo);
        Booking_Pojo.setAdditionalneeds("Breakfast");

        // Serialization of properties using Gson

        Gson GSON = new Gson();
        String Booking_GsonData = GSON.toJson(Booking_Pojo);

        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.body(Booking_GsonData);
        Req_Spec.contentType(ContentType.JSON);

        Res = Req_Spec.log().all().when().post();
        Integer HamcrestBookingid = Res.jsonPath().getInt("bookingid");
        Res.jsonPath().getString("booking.firstname");
        Res.jsonPath().getString("booking.lastname");
        System.out.println("This is Hamcrest bookingid:"+HamcrestBookingid);

        // Validations using Testng
        Val_Res = Res.then().log().all();
        Integer TestngBookingid = Val_Res.extract().path("bookingid");
        String TestngFirstname = Val_Res.extract().path("booking.firstname");
        String TestngLastname = Val_Res.extract().path("booking.lastname");
        System.out.println("This is testng bookingid:"+TestngBookingid);

        Assert.assertEquals(TestngFirstname,"Pramode");
        Assert.assertEquals(TestngLastname,"Dutta");
        Assert.assertNotNull(TestngBookingid);

        // Capture Response

        String Response_Json = Res.asString();
        System.out.println("This is Response_Json captured:"+Response_Json);

        // Deserialization using Gson

        BookingidWithResponse BookingDeserialization = GSON.fromJson(Response_Json,BookingidWithResponse.class);

        // Assertj validations

        assertThat(BookingDeserialization.getBooking().getFirstname()).isEqualTo("Pramode");
        assertThat(BookingDeserialization.getBookingid()).isNotZero();
        assertThat(BookingDeserialization.getBooking().getBookingdates().getCheckin()).isEqualTo("2024-02-02");

    }
}
