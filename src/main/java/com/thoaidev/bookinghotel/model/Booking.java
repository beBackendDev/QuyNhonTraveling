package com.thoaidev.bookinghotel.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Booking {
    private int bookingId;
    private int customerId;//foreign key references from Customer
    private int roomId;//foreign key references from Room
    private LocalDateTime checkinDate;
    private LocalDateTime checkoutDate;
    private String bookingStatus;//(confirmed, cancelled..)
    private int totalPrice;
    private LocalDateTime createdAt;
}
