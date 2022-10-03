package com.blogapp.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.app.payloads.ApiResponse;
import com.blogapp.app.payloads.UserDto;
import com.blogapp.app.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
		UserDto userSaved = userService.createUser(userDto);
		return new ResponseEntity<>(userSaved,HttpStatus.CREATED);
	}
	
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable("id") Integer UserId) {
		return ResponseEntity.ok(userService.getUserById(UserId));
	}
	
	// update user
	
	@PutMapping("/updateuser/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("id") Integer userId) {
		UserDto updateUser=userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updateUser);
	}
	
	// find all user
	@GetMapping("/getAllUser")
	public List<UserDto> getAllUser(){
		return userService.findAllUsers();
	}
	
	// delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
	}
}
