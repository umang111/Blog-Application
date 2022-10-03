package com.blogapp.app.payloads;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	private int postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date AddedDate;
	
	private CategoryDto categorys;
	
	private UserDto users;
}
