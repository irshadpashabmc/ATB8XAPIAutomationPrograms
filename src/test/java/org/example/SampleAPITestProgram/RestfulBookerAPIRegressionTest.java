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
import org.testng.asserts.Assertion;
import static org.assertj.core.api.Assertions.assertThat;


public class RestfulBookerAPIRegressionTest {


    public String getToken() {

        String Base_URL = "https://restful-booker.herokuapp.com/";
        String Base_Path = "auth";
        String Req_Payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;
        String Token;

        // Given()
        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.body(Req_Payload);
        Req_Spec.contentType(ContentType.JSON);

        // when()
        Res = Req_Spec.when().log().all().post();

        // Verify the token and capture the token
        Token = Res.jsonPath().getString("token");
        System.out.println("Captured Token is:"+Token);

        // Validate the response and verify the status code and Token value
        Val_Res = Res.then();
        Val_Res.statusCode(200).log().all();
     //   Val_Res.body("token", Matchers.equalTo(Token));

        return Token;
    }


    public Integer getBookingID() {

        String First = String.valueOf(Math.random()*6);
        String Last = String.valueOf(Math.random()*5);
        String First_Name = "John"+First;
        String Last_Name = "Cena"+Last;
        String Base_URL = "https://restful-booker.herokuapp.com/";
        String Base_Path = "booking";
        String Req_Payload = "{\n" +
                "    \"firstname\" : \""+First_Name+"\",\n" +
                "    \"lastname\" : \""+Last_Name+"\",\n" +
                "    \"totalprice\" : 555,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-02-02\",\n" +
                "        \"checkout\" : \"2024-02-02\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;
        Integer Booking_id;

        // Given()
        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.body(Req_Payload);
        Req_Spec.contentType(ContentType.JSON);

        // when()
        Res = Req_Spec.when().log().all().post();

        // Verify the booking_id and capture the booking_id
        Booking_id = Res.jsonPath().getInt("bookingid");
        String FN = Res.jsonPath().getString("booking.firstname");
        String LN = Res.jsonPath().getString("booking.lastname");
        Integer TP = Res.jsonPath().getInt("booking.totalprice");

        System.out.println("Captured Bookingid is:"+Booking_id);

        // Validate the response and verify the status code
        Val_Res = Res.then();
        Val_Res.log().all().statusCode(200);
        Integer Bk = Val_Res.extract().path("bookingid");
        System.out.println("booking id is:"+Bk);
        String Fir = Val_Res.extract().path("booking.firstname");
        System.out.println("firstname is:"+Fir);
        String Las = Val_Res.extract().path("booking.lastname");
        System.out.println("Lastname is:"+Las);
        Integer Total_Price = Val_Res.extract().path("booking.totalprice");
        Assert.assertEquals(Bk,Booking_id);
        Assert.assertEquals(Fir,FN);
        Assert.assertEquals(Las,LN);
        Assert.assertEquals(Total_Price,TP);

     //   Val_Res.body("bookingid",Matchers.equalTo(Booking_id));
    //    Val_Res.body("firstname",Matchers.equalTo(First_name));
     //   Val_Res.body("lastname",Matchers.equalTo(Last_Name));

        return Booking_id;
    }

    @Description("Update Booking Details using Post Method")
    @Test (priority = 1)
   public void UpdateBookingDetails() {

        Integer Bid = getBookingID();
        String Tkn = getToken();

        String Base_URL = "https://restful-booker.herokuapp.com/";
        String Base_Path = "booking/"+Bid;
        String Req_Payload = "{\n" +
                "    \"firstname\" : \"Wayne\",\n" +
                "    \"lastname\" : \"Johnson\",\n" +
                "    \"totalprice\" : 333,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-03-03\",\n" +
                "        \"checkout\" : \"2024-03-03\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";
        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;

        // Given()
        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.body(Req_Payload);
        Req_Spec.contentType(ContentType.JSON);
        Req_Spec.cookie("token",Tkn);

        // when()
        Res = Req_Spec.when().log().all().put();

        String FN = Res.jsonPath().getString("booking.firstname");
        String LN = Res.jsonPath().getString("booking.lastname");
        Integer TP = Res.jsonPath().getInt("totalprice");

        // Verify the First name and last name and all details

        // Validate the response and verify the status code
        Val_Res = Res.then();
        Val_Res.statusCode(200);

        String First_name = Val_Res.extract().path("booking.firstname");
        String Last_name = Val_Res.extract().path("booking.lastname");
        Integer Total_Price = Val_Res.extract().path("totalprice");

        Assert.assertEquals(First_name,FN);
        Assert.assertEquals(Last_name,LN);
        Assert.assertEquals(Total_Price,TP);

    }

    @Description("Verify the updated booking details using Get Method")
    @Test (priority = 2)
    public void getUpdatedBookingDetails() {
        Integer Bid = getBookingID();
        String Base_URL = "https://restful-booker.herokuapp.com/";
        String Base_Path = "booking/"+Bid;

        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;

        // Given()
        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.contentType(ContentType.JSON);

        // when()
        Res = Req_Spec.when().log().all().get();
        String first_name = Res.jsonPath().getString("booking.firstname");
        String last_name = Res.jsonPath().getString("booking.lastname");

        // Verify the booking_id and capture the booking_id


        // Validate the response and verify the status code
        Val_Res = Res.then();
        Val_Res.statusCode(200);
        String Fir = Val_Res.extract().path("booking.firstname");
        String Las = Val_Res.extract().path("booking.lastname");

        assertThat(Fir).isEqualTo(first_name);
        assertThat(Las).isEqualTo(last_name);


    }

    @Description("Partial update booking details using Patch Method")
    @Test (priority = 3)
    public void PartialUpdateBookingDetails() {

        Integer Bid = getBookingID();
        String Tkn = getToken();

        String Base_URL = "https://restful-booker.herokuapp.com/";
        String Base_Path = "booking/"+Bid;
        String Req_Payload = "{\n" +
                "    \"firstname\" : \"wayne\",\n" +
                "    \"lastname\" : \"Johnson\"\n" +
                "}";
        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;

        // Given()
        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.body(Req_Payload);
        Req_Spec.contentType(ContentType.JSON);
        Req_Spec.accept(ContentType.JSON);
        Req_Spec.cookie("token",Tkn);

        // when()
        Res = Req_Spec.when().log().all().patch();

        // Verify the First name and last name and all details

        // Validate the response and verify the status code
        Val_Res = Res.then();
        Val_Res.statusCode(500);

    }

    @Description("Verify the Partial updated booking details using Get Method")
    @Test (priority = 4)
    public void getPartialUpdatedBookingDetails() {

        Integer Bid = getBookingID();
        String Base_URL = "https://restful-booker.herokuapp.com/";
        String Base_Path = "booking/"+Bid;

        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;

        // Given()
        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.contentType(ContentType.JSON);

        // when()
        Res = Req_Spec.when().log().all().get();

        // Verify the booking_id and capture the booking_id

        // Validate the response and verify the status code
        Val_Res = Res.then();
        Val_Res.statusCode(200);

    }

    @Description("Delete the Booking using Delete Method")
    @Test (priority = 5)
    public void DeleteBookingDetails() {

        Integer Bid = getBookingID();
        String Tkn = getToken();
        String Base_URL = "https://restful-booker.herokuapp.com/";
        String Base_Path = "booking/"+Bid;

        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;

        // Given()
        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.contentType(ContentType.JSON);
        Req_Spec.cookie("token",Tkn);

        // when()
        Res = Req_Spec.when().log().all().delete();

        // Validate the response and verify the status code
        Val_Res = Res.then();
        Val_Res.statusCode(201);

    }


    @Description("Verify the Deleted Bookingid exists or not using Get Method")
    @Test (priority = 6)
    public void VerifyDeletesBookingDetails() {

        Integer Bid = getBookingID();
        String Base_URL = "https://restful-booker.herokuapp.com/";
        String Base_Path = "booking/"+Bid;

        RequestSpecification Req_Spec;
        Response Res;
        ValidatableResponse Val_Res;

        // Given()
        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(Base_URL);
        Req_Spec.basePath(Base_Path);
        Req_Spec.contentType(ContentType.JSON);

        // when()
        Res = Req_Spec.when().log().all().get();

        // Validate the response and verify the status code
        Val_Res = Res.then();
        Val_Res.statusCode(200);

    }

}
