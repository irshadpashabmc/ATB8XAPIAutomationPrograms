package org.example.SampleAPITestProgram;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class CreateBookingidUsingPOJOAPITest {

    APITestingUsingPOJOTest POJOTest = new APITestingUsingPOJOTest();

    @Description("Testing the functionalities of User Booking(Restful Booker)")
    @Test
    public void APIUserBookingidTest() {

    POJOTest.POJOAPITest();

    }

}
