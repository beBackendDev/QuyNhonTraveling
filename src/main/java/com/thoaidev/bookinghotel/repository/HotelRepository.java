package com.thoaidev.bookinghotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thoaidev.bookinghotel.model.Hotel;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    public List<Hotel> findByHotelAddressContainingIgnoreCase(String location);
    public List<Hotel> findByHotelNameContainingIgnoreCase(String name);

}
