package com.thoaidev.bookinghotel;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.thoaidev.bookinghotel.repository.HotelRepository;

import lombok.RequiredArgsConstructor;

@ComponentScan("com.thoaidev.bookinghotel")
@SpringBootApplication
@RequiredArgsConstructor
public class BookinghotelApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BookinghotelApplication.class, args);
		HotelRepository hotelRepository = context.getBean(HotelRepository.class);
        System.out.println("findAll: ");
        hotelRepository.findAll()
                      .forEach(System.out::println);
	}

}
