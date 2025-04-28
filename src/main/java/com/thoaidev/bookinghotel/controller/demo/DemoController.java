package com.thoaidev.bookinghotel.controller.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@RequestMapping("/api/admin")

public class DemoController {
    @GetMapping("/demo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminDashboard() {
    return ResponseEntity.ok("Welcome owner!");
    }
}
