package com.thoaidev.bookinghotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thoaidev.bookinghotel.model.Room;
import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Integer>{
    List<Room> findByHotel_HotelId(Integer hotelId);
}
