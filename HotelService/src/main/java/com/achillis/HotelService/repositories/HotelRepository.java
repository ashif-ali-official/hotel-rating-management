package com.achillis.HotelService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achillis.HotelService.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
