package com.thoaidev.bookinghotel.dto;
import java.time.LocalDateTime;

//Dto stands for <Data transfer Object> là một lớp được sử dụng để truyền dữ liệu giữa các tầng
//đặc biệt là giữa FrontEnd và BackEnd. Nó giúp tăng tính bảo mật vì chỉ gửi những thông tin cần thiết
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class HotelDto {

    private Integer hotelId;//Id khach san
    private String hotelName;//ten khach san
    private String hotelAddress;//dia chi khach san
    private String hotelLowestPrice;//gia tien thap nhat
    private String hotelFacility;//tien ich
    private String hotelRating;//danh gia
    private String hotelContactMail;//Email lien he
    private String hotelContactPhone;//Phone lien he
    private String hotelDescription;//mo ta khách san
    private String hotelImage;//hinh anh khách san
    private LocalDateTime hotelCreatedAt;//ngay tao
    private LocalDateTime hotelUpdatedAt;//ngay duoc nang cap
}
