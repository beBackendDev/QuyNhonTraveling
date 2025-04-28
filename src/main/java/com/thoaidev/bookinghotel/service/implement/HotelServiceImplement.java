package com.thoaidev.bookinghotel.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thoaidev.bookinghotel.dto.HotelDto;
import com.thoaidev.bookinghotel.dto.HotelResponse;
import com.thoaidev.bookinghotel.exceptions.NotFoundException;
import com.thoaidev.bookinghotel.model.Hotel;
import com.thoaidev.bookinghotel.repository.HotelRepository;
import com.thoaidev.bookinghotel.service.HotelService;

@Service
public class HotelServiceImplement implements HotelService{
    @Autowired 
    private HotelRepository hotelRepository;

    
    public HotelServiceImplement(HotelRepository hotelRepo){
        this.hotelRepository = hotelRepo;
    }
    //User: TÌm toàn bộ danh sách các khách sạn
    @Override
    public HotelResponse getAllHotels(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
       Page<Hotel> hotels = hotelRepository.findAll(pageable);
       List<Hotel> listOfHotels = hotels.getContent();
       List<HotelDto> content =  listOfHotels.stream().map((hotel)-> mapToHotelDto(hotel)).collect(Collectors.toList());
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setContent(content);
        hotelResponse.setPageNo(hotels.getNumber());
        hotelResponse.setPageSize(hotels.getSize());
        hotelResponse.setTotalElements(hotels.getTotalElements());
        hotelResponse.setTotalPage(hotels.getTotalPages());
        hotelResponse.setLast(hotels.isLast());
        return hotelResponse;
    }


    //User: lấy thông tin khách sạn theo ID
    @Override
    public HotelDto getHotelById(Integer id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new NotFoundException("Đối tượng Hotel không tồn tại"));
        return mapToHotelDto(hotel);
    }
    //
    @Override
public List<Hotel> getHotelsByAddress(String location) {
    return hotelRepository.findByHotelAddressContainingIgnoreCase(location);  // Tìm khách sạn theo địa chỉ
}
@Override
public List<Hotel> getHotelsByName(String name) {
    return hotelRepository.findByHotelNameContainingIgnoreCase(name);  // Tìm khách sạn theo địa chỉ
}
    //Admin: tạo mởi khách sạn
    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
       Hotel hotel = new Hotel();
            hotel.setHotelName(hotelDto.getHotelName());
            hotel.setHotelAddress(hotelDto.getHotelAddress());
            hotel.setHotelDescription(hotelDto.getHotelDescription());
            hotel.setHotelFacility(hotelDto.getHotelFacility());
            hotel.setHotelRating(hotelDto.getHotelRating());
            hotel.setHotelContactMail(hotelDto.getHotelContactMail());
            hotel.setHotelContactPhone(hotelDto.getHotelContactPhone());
            hotel.setHotelLowestPrice(hotelDto.getHotelLowestPrice());
            hotel.setHotelImage(hotelDto.getHotelImage());
            // Set thời gian tạo và cập nhật
            LocalDateTime now = LocalDateTime.now();
            hotel.setHotelCreatedAt(now);
            hotel.setHotelUpdatedAt(now);

            Hotel savedHotel = hotelRepository.save(hotel);//goị tới Repository để update lên DB

            return mapToHotelDto(savedHotel);
    }

//Admin: Xóa khách sạn
    @Override
    public void deleteHotelById(Integer id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException("Đối tượng Hotel không tồn tại"));
        hotelRepository.delete(hotel);
    }
//Admin: Cập nhật khách sạn
    @Override
    public HotelDto updateHotel(HotelDto hotelDto, Integer id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException("Đối tượng Hotel không tồn tại"));
        hotel.setHotelName(hotelDto.getHotelName());
        hotel.setHotelAddress(hotelDto.getHotelAddress());
        hotel.setHotelDescription(hotelDto.getHotelDescription());
        hotel.setHotelFacility(hotelDto.getHotelFacility());
        hotel.setHotelLowestPrice(hotelDto.getHotelLowestPrice());
        hotel.setHotelRating(hotelDto.getHotelRating());
        hotel.setHotelContactPhone(hotelDto.getHotelContactPhone());
        hotel.setHotelContactMail(hotelDto.getHotelContactMail());
        hotel.setHotelImage(hotelDto.getHotelImage());

        Hotel updatedHotel =    hotelRepository.save(hotel);
        return mapToHotelDto(updatedHotel);
    }

    //_____________Other Methods_____________
    
    private HotelDto mapToHotelDto(Hotel hotel){
        HotelDto hotelDto = HotelDto.builder()
                            .hotelId(hotel.getHotelId())
                            .hotelName(hotel.getHotelName())
                            .hotelImage(hotel.getHotelImage())
                            .hotelLowestPrice(hotel.getHotelLowestPrice())
                            .hotelFacility(hotel.getHotelFacility())
                            .hotelAddress(hotel.getHotelAddress())
                            .hotelContactMail(hotel.getHotelContactMail())
                            .hotelContactPhone(hotel.getHotelContactPhone())
                            .hotelDescription(hotel.getHotelDescription())
                            .hotelRating(hotel.getHotelRating())
                            .hotelCreatedAt(hotel.getHotelCreatedAt())
                            .hotelUpdatedAt(hotel.getHotelUpdatedAt())
                            
                            .build();
                            return hotelDto;
    }
    @Override
    public List<HotelDto> fetchHotelsFromRapidAPI(LocalDateTime checkin, LocalDateTime checkout) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchHotelsFromRapidAPI'");
    }
}


