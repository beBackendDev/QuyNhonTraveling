package com.thoaidev.bookinghotel.controller.admin_owner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thoaidev.bookinghotel.dto.HotelDto;
import com.thoaidev.bookinghotel.dto.RoomDto;
import com.thoaidev.bookinghotel.service.RoomService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/khach-san/")
public class RoomController {
    
    private RoomService roomService;
    @Autowired
    private RoomController(RoomService roomService){
        this.roomService = roomService;
    }

//GET methods
    @GetMapping("/{hotelId}/cac-phong")
    public List<RoomDto> getRoomByHotelId(@PathVariable(value = "hotelId") Integer hotelId ){
        return roomService.getRoomByHotelId(hotelId);
    }
    
    @GetMapping("/{hotelId}/cac-phong/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable(value = "hotelId") Integer hotelId, @PathVariable(value = "roomId") Integer roomId) {
        RoomDto roomDto = roomService.getRoomById(roomId, hotelId);
        return new ResponseEntity<>(roomDto, HttpStatus.OK);
    }
//POST methods
    @PostMapping("/{hotelId}/tao-phong")
    public RoomDto createRoomDto(@PathVariable(value = "hotelId") Integer hotelId, @RequestBody RoomDto roomDto) {
        return roomService.createRoom(hotelId, roomDto);
    }
//PUT methods
    @PutMapping("/{hotelId}/cap-nhat-phong/{roomId}")
    public ResponseEntity<RoomDto> updateRoom(@RequestBody RoomDto roomDto, @PathVariable("hotelId") Integer hotelId, @PathVariable("roomId") Integer roomId){
        RoomDto response = roomService.updateRoom(hotelId, roomId, roomDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
//DELETE methods
    @DeleteMapping("/{hotelId}/xoa-phong/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable(value = "hotelId") Integer hotelId, @PathVariable(value = "roomId") Integer roomId){
        roomService.deleteRoombyId(hotelId, roomId);
        return new ResponseEntity<>("Room id =  + {roomId} + đã xóa", HttpStatus.OK);
    }
}

