package org.example.SampleAPITestProgram;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingidDeSerializationTest {

    @SerializedName("bookingid")
    @Expose
    private Integer bookingid;
    @SerializedName("booking")
    @Expose
    private BookingSerializationTest booking;

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingSerializationTest getBooking() {
        return booking;
    }

    public void setBooking(BookingSerializationTest booking) {
        this.booking = booking;
    }

}
