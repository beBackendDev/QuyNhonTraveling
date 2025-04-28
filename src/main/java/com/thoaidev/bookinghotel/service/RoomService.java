package com.thoaidev.bookinghotel.service;

import java.util.List;

import com.thoaidev.bookinghotel.dto.RoomDto;

public interface RoomService {
//GET methods
    List <RoomDto> getRoomByHotelId(Integer id);
    RoomDto getRoomById(Integer roomId, Integer hotelId);
//POST methods
    RoomDto createRoom (Integer hotelId, RoomDto roomDto);
//PUT methods
    RoomDto updateRoom (Integer hotelId, Integer roomId, RoomDto roomDto);
//DELETE methods
    void deleteRoombyId(Integer hotelId, Integer roomId);
}
