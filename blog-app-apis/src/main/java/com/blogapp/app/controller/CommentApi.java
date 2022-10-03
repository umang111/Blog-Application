package com.blogapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.app.entitys.Post;
import com.blogapp.app.payloads.CommentDto;
import com.blogapp.app.services.CommentService;

@RestController
public class CommentApi {

	@Autowired 
	private CommentService commentService;
	
	@PostMapping("/postComment/{postId}")
	public CommentDto postComment(@PathVariable("postId") Integer postid, @RequestBody CommentDto commentDto) {
		CommentDto postedComment = commentService.postComment(postid, commentDto);
		return postedComment;
	}
}
