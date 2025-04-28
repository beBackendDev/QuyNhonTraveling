package com.thoaidev.bookinghotel.dto;

import com.thoaidev.bookinghotel.model.Hotel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {
     private Integer roomId;
    
    
    private String roomName;
    private String roomImage;
    private String roomType;
    private int roomOccupancy;
    private boolean roomStatus;
    private double roomPricePerNight;

    private Hotel hotel;
    private Integer hotelId;// chỉ cần lấy hotelId trong Dto để truy vấn
}
