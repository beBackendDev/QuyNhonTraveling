package com.thoaidev.bookinghotel.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "full_name")
    private String fullname;


    @Column(name = "user_pw")
    private String password;


    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;
    
    @ManyToMany(fetch  = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",joinColumns= @JoinColumn(name = "user_id", referencedColumnName="user_id"),
                inverseJoinColumns= @JoinColumn(name  = "role_id", referencedColumnName= "role_id"))

    private List<Role> roles = new ArrayList<>();

    
}
