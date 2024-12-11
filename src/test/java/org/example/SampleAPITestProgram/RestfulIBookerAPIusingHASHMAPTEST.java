package org.example.SampleAPITestProgram;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.bytebuddy.build.Plugin;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;

public class RestfulIBookerAPIusingHASHMAPTEST {


    @Description("Verifying POST Method of Restful Booker")
    @Step("Verify the post method of Restful booker API")
    @Test
    public void HashMapAPITest() {

    Map<String,Object> JsonBodyMap = new LinkedHashMap<>();

        JsonBodyMap.put("firstname","Celena");
        JsonBodyMap.put("lastname","Gomez");
        JsonBodyMap.put("totalprice",123);
        JsonBodyMap.put("depositpaid",true);

        Map<String,Object> BookingDatesMap = new LinkedHashMap<>();

        BookingDatesMap.put("checkin","2024-02-02");
        BookingDatesMap.put("checkout","2024-02-04");
        JsonBodyMap.put("bookingdates",BookingDatesMap);
        JsonBodyMap.put("additionalneeds","lunch");

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
        Req_Spec.body(JsonBodyMap);
        Req_Spec.contentType(ContentType.JSON);

        // when()
        Res = Req_Spec.when().log().all().post();

        Booking_id = Res.jsonPath().getInt("bookingid");
        System.out.println("Captured Bookingid is:"+Booking_id);

        Val_Res = Res.then();
        Val_Res.log().all().statusCode(200);

    }

}


