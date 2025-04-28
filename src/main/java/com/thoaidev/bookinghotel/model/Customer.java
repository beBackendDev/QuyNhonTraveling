package com.thoaidev.bookinghotel.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_id")
    private Integer customerId;
    
    @Column(name = "cus_name")
    private String customerFullname;

    @Column(name = "cus_email")
    private String customerEmail;

    @Column(name = "cus_phone")
    private String customerPhoneNum;

    @Column(name = "cus_address")
    private String customerAddress;

    @Column(name = "cus_image")
    private String customerImage;

    @Column(name = "cus_pw")
    private String customerPassword;

    @Column(name = "cus_created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "cus_updated_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;
}
