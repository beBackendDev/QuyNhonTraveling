package com.thoaidev.bookinghotel.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Payment {
    private int paymentId;
    private int bookingId;;//foreign key references from Booking
    private int amount;//so tien thanh toan
    private Date paymentDate;//ngay thanh toan
    private String paymentStatus;//('Pending', 'Completed', 'Failed'..)
    private String paymentMethod;//Cash, Card, Momo...
}
