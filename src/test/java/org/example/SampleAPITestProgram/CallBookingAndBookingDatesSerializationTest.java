package org.example.SampleAPITestProgram;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CallBookingAndBookingDatesSerializationTest {

    BookingSerializationTest JsonBookingRes1 = new BookingSerializationTest();
    BookingDatesSerializationTest JsonBookingRes2 = new BookingDatesSerializationTest();
    BookingidDeSerializationTest JsonBookingRes3 = new BookingidDeSerializationTest();

    @Description("Verifying POST Method of Restful Booker using serialization")
    @Step
    @Test
    public void SerializationAPITest() {

        JsonBookingRes1.setFirstname("aguleraaa");
        JsonBookingRes1.setLastname("Christineee");
        JsonBookingRes1.setTotalprice(773);
        JsonBookingRes1.setDepositpaid(true);
        JsonBookingRes2.setCheckin("2024-02-03");
        JsonBookingRes2.setCheckout("2024-02-05");
        JsonBookingRes1.setBookingdates(JsonBookingRes2);
        JsonBookingRes1.setAdditionalneeds("Lunch");

        String Base_URL = "https://restful-booker.herokuapp.com/";
        String Base_Path = "booking";
        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;
        Integer Booking_id;

        // gson request payload
        Gson GSON = new Gson();
        String JsonStringBooking = GSON.toJson(JsonBookingRes1);
        System.out.println("the request_payload is:"+JsonStringBooking);

        // Given()
        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.body(JsonBookingRes1);
        Req_Spec.contentType(ContentType.JSON);

        // when()
        Res = Req_Spec.when().log().all().post();

        // gson response payload JsonBookingRes3

        String ResponseJsonData = Res.asString();

        BookingidDeSerializationTest Responsebookingid = GSON.fromJson(ResponseJsonData,BookingidDeSerializationTest.class);

        assertThat(Responsebookingid.getBookingid()).isNotZero();

        Booking_id = Res.jsonPath().getInt("bookingid");
        System.out.println("Captured Bookingid is:"+Booking_id);

        Val_Res = Res.then();
        Val_Res.log().all().statusCode(200);

    }
}
