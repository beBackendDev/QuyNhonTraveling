package com.thoaidev.bookinghotel.controller.admin_owner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.thoaidev.bookinghotel.repository.HotelRepository;
import com.thoaidev.bookinghotel.service.HotelService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thoaidev.bookinghotel.dto.HotelDto;
import com.thoaidev.bookinghotel.dto.HotelResponse;
import com.thoaidev.bookinghotel.model.Hotel;

@RestController
@RequestMapping("/api")
public class HotelController {

    private final HotelRepository hotelRepository;
    @Autowired
    private HotelService hotelService;

    public HotelController(HotelService hotelService, HotelRepository hotelRepository) {
        this.hotelService = hotelService;
        this.hotelRepository = hotelRepository;
    }
//-- GET methods
    //lấy toàn bộ thông tin khách sạn
    //http://localhost:8080/api/khach-san?pageNo=0&pageSize=5

    @GetMapping("/khach-san")
    public ResponseEntity<HotelResponse> listHotels(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(hotelService.getAllHotels(pageNo, pageSize), HttpStatus.OK);

    }
    //lấy thông tin khách sạn theo id

    @GetMapping("/khach-san/{id}")
    public ResponseEntity<HotelDto> hotelDetail(@PathVariable Integer id) {
        HotelDto hotelDto = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotelDto);
    }
    //Lấy khách sạn theo vị trí

    @GetMapping("/khach-san/vi-tri/{location}")
    public List<Hotel> getHotelsByAddress(@PathVariable String location) {
        return hotelService.getHotelsByAddress(location);
    }
    //lấy thông tin dựa theo nhiều tiêu chí(tên, đánh giá...)
    // Lấy khách sạn theo tên

    @GetMapping("/khach-san/tim-kiem/{name}")
    public List<Hotel> getHotelsByName(@RequestParam String name) {
        return hotelService.getHotelsByName(name);
    }

//-- POST methods
    // Tạo khách sạn mới
    @PostMapping("khach-san/tao-moi")
    public HotelDto createHotel(@RequestBody HotelDto hotelDto) {
        return hotelService.createHotel(hotelDto);
    }

//PUT methods
    @PutMapping("/khach-san/{id}/cap-nhat")
    public ResponseEntity<HotelDto> updateHotel(@RequestBody HotelDto hotelDto, @PathVariable("id") Integer hotelId) {
        HotelDto response = hotelService.updateHotel(hotelDto, hotelId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//DELETE methods
    // Xóa khách sạn theo ID
    @DeleteMapping("khach-san/{id}/xoa")
    public ResponseEntity<String> deleteHotel(@PathVariable Integer id) {
        hotelService.deleteHotelById(id);
        return new ResponseEntity<>("Hotel deleted", HttpStatus.OK);
    }
}
