package com.thoaidev.bookinghotel.dto;

import java.util.List;

import lombok.Data;
@Data
public class HotelResponse{
    private List<HotelDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPage;
    private boolean last;
}
