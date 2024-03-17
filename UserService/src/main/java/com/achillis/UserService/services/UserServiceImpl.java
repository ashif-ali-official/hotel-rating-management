package com.achillis.UserService.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.achillis.UserService.entity.Hotel;
import com.achillis.UserService.entity.Rating;
import com.achillis.UserService.entity.User;
import com.achillis.UserService.exceptions.ResourceNotFoundException;
import com.achillis.UserService.external.services.HotelService;
import com.achillis.UserService.external.services.RatingService;
import com.achillis.UserService.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> userList = userRepository.findAll(); 
		for(User user:userList) {
			String userId = user.getUserId();
			List<Rating> ratingArray = ratingService.getAllRatingsByUserId(userId);
			
			List<Rating> ratingList = ratingArray.stream().map( rating -> {
				Hotel hotel = hotelService.getHotel(rating.getHotelId());
				rating.setHotel(hotel);
				return rating;
			}).collect(Collectors.toList());
			
			user.setRatings(ratingList);
		}
		return userList;
	}

	@Override
	public User getUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given ID:"+ userId));
		List<Rating> userRatings = ratingService.getAllRatingsByUserId(userId);
		
		List<Rating> ratingList = userRatings.stream().map( rating -> {
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
