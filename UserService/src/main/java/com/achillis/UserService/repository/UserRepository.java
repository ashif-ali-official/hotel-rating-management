package com.achillis.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achillis.UserService.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
