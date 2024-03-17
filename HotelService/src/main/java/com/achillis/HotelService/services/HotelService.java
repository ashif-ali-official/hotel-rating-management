package com.achillis.HotelService.services;

import java.util.List;

import com.achillis.HotelService.entities.Hotel;

public interface HotelService {

	Hotel createHotel(Hotel hotel);
	
	List<Hotel> getAllHotels();
	
	Hotel get(String hotelId);
	
}
