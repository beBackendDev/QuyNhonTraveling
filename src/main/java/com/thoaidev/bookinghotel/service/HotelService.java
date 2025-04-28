package com.thoaidev.bookinghotel.service;

import java.util.List;

import com.thoaidev.bookinghotel.dto.HotelDto;
import com.thoaidev.bookinghotel.dto.HotelResponse;
import com.thoaidev.bookinghotel.model.Hotel;
import java.time.LocalDateTime;

public interface HotelService {

    //xu li thuc hien lay du lieu trong database de dien vao thymeleaf
//GET methods
    HotelResponse getAllHotels(int pageNo, int pageSize);

    public HotelDto getHotelById(Integer id);

    public List<Hotel> getHotelsByAddress(String location);

    public List<Hotel> getHotelsByName(String name);

    //Get in rapidApi
    public List<HotelDto> fetchHotelsFromRapidAPI(LocalDateTime checkin, LocalDateTime checkout);
//POST methods

    public HotelDto createHotel(HotelDto hotelDto);
//PUT methods

    public HotelDto updateHotel(HotelDto hotelDto, Integer id);
//DELETE methods

    public void deleteHotelById(Integer id);

}
