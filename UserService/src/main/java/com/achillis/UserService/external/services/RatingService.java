package com.achillis.UserService.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.achillis.UserService.entity.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	@GetMapping("/ratings/users/{userId}")
	public List<Rating> getAllRatingsByUserId(@PathVariable String userId);
	
}
