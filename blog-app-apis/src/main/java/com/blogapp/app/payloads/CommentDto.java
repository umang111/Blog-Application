package com.blogapp.app.payloads;

import com.blogapp.app.entitys.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

	private int commentId;
	private String content;
	
	private Post post;
}
