package com.thoaidev.bookinghotel.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoaidev.bookinghotel.dto.RoomDto;
import com.thoaidev.bookinghotel.exceptions.NotFoundException;
import com.thoaidev.bookinghotel.model.Hotel;
import com.thoaidev.bookinghotel.model.Room;
import com.thoaidev.bookinghotel.repository.HotelRepository;
import com.thoaidev.bookinghotel.repository.RoomRepository;
import com.thoaidev.bookinghotel.service.RoomService;

@Service
public class RoomServiceImplement implements RoomService {
    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;
    
    @Autowired 
    public RoomServiceImplement(HotelRepository hotelRepository, RoomRepository roomRepository){
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }
//GET methods
@Override
public List<RoomDto> getRoomByHotelId(Integer id) {
    List<Room> rooms = roomRepository.findByHotel_HotelId(id);
    return rooms.stream()
            .map(room -> mapToRoomDto(room))
            .collect(Collectors.toList());    
}


@Override
public RoomDto getRoomById(Integer roomId, Integer hotelId) {
    Hotel hotel = 
        hotelRepository.findById(hotelId).orElseThrow(() -> new NotFoundException("Đối tượng Hotel không tồn tại"));
    Room room = 
        roomRepository.findById(hotelId).orElseThrow(() -> new NotFoundException("Đối tượng Room không tồn tại"));
    if (room.getHotel().getHotelId() != hotel.getHotelId()){
        throw new NotFoundException("Đối tượng Room không tồn tại trong Hotel");
    }

    return mapToRoomDto(room);
}

//POST methods
    @Override
    public RoomDto createRoom(Integer hotelId, RoomDto roomDto) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new NotFoundException("Đối tượng Hotel không tồn tại"));

        Room room = new Room();
        room.setRoomId(roomDto.getRoomId());
        room.setRoomName(roomDto.getRoomName());
        room.setRoomImage(roomDto.getRoomImage());
        room.setRoomOccupancy(roomDto.getRoomOccupancy());
        room.setRoomStatus(roomDto.isRoomStatus());
        room.setRoomType(roomDto.getRoomType());
        room.setRoomPricePerNight(roomDto.getRoomPricePerNight());
        room.setHotel(hotel);

        Room createdRoom = roomRepository.save(room);
        return mapToRoomDto(createdRoom);

    }
//UPDATE methods
    @Override
    public RoomDto updateRoom(Integer hotelId, Integer roomId, RoomDto roomDto) {
        Hotel hotel = 
            hotelRepository.findById(hotelId).orElseThrow(() -> new NotFoundException("Đối tượng Hotel không tồn tại"));
        Room room = 
            roomRepository.findById(hotelId).orElseThrow(() -> new NotFoundException("Đối tượng Room không tồn tại"));
        
        room.setRoomName(roomDto.getRoomName());
        room.setRoomImage(roomDto.getRoomImage());
        room.setRoomOccupancy(roomDto.getRoomOccupancy());
        room.setRoomPricePerNight(roomDto.getRoomPricePerNight());
        room.setRoomStatus(roomDto.isRoomStatus());
        room.setRoomType(roomDto.getRoomType());
        room.setHotel(hotel);

        Room updatedRoom = roomRepository.save(room);
        return mapToRoomDto(updatedRoom);
    }

//DELETE methods
@Override
public void deleteRoombyId(Integer hotelId, Integer roomId) {
    Hotel hotel = 
        hotelRepository.findById(hotelId).orElseThrow(() -> new NotFoundException("Đối tượng Hotel không tồn tại"));
    Room room = 
        roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("Đối tượng Room không tồn tại"));

    if(room.getHotel().getHotelId() != hotel.getHotelId()){
        throw new NotFoundException("Đối tượng Room không nằm trong Hotel");
    
    }
    roomRepository.delete(room);
}
        //_____________Other Methods_____________
    
        private RoomDto mapToRoomDto(Room room){
            RoomDto roomDto = RoomDto.builder()
                                .roomId(room.getRoomId())
                                .roomImage(room.getRoomImage())
                                .roomName(room.getRoomName())
                                .roomType(room.getRoomType())
                                .roomOccupancy(room.getRoomOccupancy())
                                .roomStatus(room.isRoomStatus())
                                .roomPricePerNight(room.getRoomPricePerNight())
                                
                                .hotelId(room.getHotel().getHotelId())// foreign key 
    
    
                                .build();
                                return roomDto;
        }

}
