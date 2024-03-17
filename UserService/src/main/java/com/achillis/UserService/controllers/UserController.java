package com.achillis.UserService.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achillis.UserService.entity.User;
import com.achillis.UserService.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "userRatingHotelBreaker", fallbackMethod = "userRatingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	//Fallback method for userRatingHotelBreaker
	public ResponseEntity<User> userRatingHotelFallback(String userId, Exception ex){
		
		logger.info("Fallback is executed because service is down:", ex.getMessage());
		User user = User.builder()
						.email("dummy@gmail.com")
						.name("Dummy")
						.about("No data")
						.build();
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> userList = userService.getAllUser();
		return ResponseEntity.ok(userList);
	}
	
}
