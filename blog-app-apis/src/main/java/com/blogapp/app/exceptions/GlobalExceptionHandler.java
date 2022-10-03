package com.blogapp.app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapp.app.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(resourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(resourceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> HandleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message =error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}

}
