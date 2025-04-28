package com.thoaidev.bookinghotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thoaidev.bookinghotel.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Optional<Role> findByRoleName(String roleName);
    
}