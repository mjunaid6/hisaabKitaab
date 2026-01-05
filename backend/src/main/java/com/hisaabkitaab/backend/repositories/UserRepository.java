package com.hisaabkitaab.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hisaabkitaab.backend.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
    
    public User findByEmail(String email);
}
