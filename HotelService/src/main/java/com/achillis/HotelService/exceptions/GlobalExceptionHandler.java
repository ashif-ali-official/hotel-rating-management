package com.achillis.HotelService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.achillis.HotelService.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException ex){
		ApiResponse response = ApiResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
	
}
