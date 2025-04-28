package com.thoaidev.bookinghotel.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "room_id")
    private Integer roomId;

    @Column (name = "room_image")
    private String roomImage;

    @Column (name = "room_name")
    private String roomName;

    @Column (name = "room_type")
    private String roomType;

    @Column (name = "room_occupancy")
    private int roomOccupancy;

    @Column (name = "room_status")
    private boolean roomStatus;
    @Column (name = "room_price_per_night")
    private double roomPricePerNight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id") // đây là cột khóa ngoại trỏ tới Hotel
    private Hotel hotel;
}
