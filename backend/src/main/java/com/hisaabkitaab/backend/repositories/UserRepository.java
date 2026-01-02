package com.hisaabkitaab.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hisaabkitaab.backend.entities.User;

public interface UserRepository extends CrudRepository<User, String>{
    
    public User findByEmail(String email);
}
