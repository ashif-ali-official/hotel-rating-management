package com.achillis.HotelService.payload;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

	private String message;
	private boolean success;
	private HttpStatus status;
	
}
