package com.blogapp.app.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4, message="name should be greater then 4 character !")
	private String name;
	
	@Email(message="Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min=4,max=10,message="Password must be min 4 chars and max of 10 char !!")
	private String password;
	
	@NotEmpty
	private String about;
}

/*
{
"name":"umang",
"email":"umang@gmail.com",
"password":"12345",
"about":"my name is umang"
}*/