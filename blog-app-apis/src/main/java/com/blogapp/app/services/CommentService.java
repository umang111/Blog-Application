package com.blogapp.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.app.entitys.Comment;
import com.blogapp.app.entitys.Post;
import com.blogapp.app.payloads.CommentDto;
import com.blogapp.app.repositories.CommentRepository;
import com.blogapp.app.repositories.PostRepo;

@Service
public class CommentService {
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@Autowired 
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepository CommentRepo;

	public CommentDto postComment(Integer postid, CommentDto commentDto) {
		Post postForComment=postRepo.findById(postid).get();
		Comment commentDtoWithPost =new Comment();
		commentDtoWithPost.setContent(commentDto.getContent());
		commentDtoWithPost.setPost(postForComment);
		Comment savedComment=CommentRepo.save(commentDtoWithPost);
		return commentToCommentDto(savedComment);
	}

	public Comment CommentDtoToComment(CommentDto commentDto) {
		Comment comment =modelMapper.map(commentDto, Comment.class);
		return comment;
	}
	
	public CommentDto commentToCommentDto(Comment comment) {
		CommentDto commentDto= modelMapper.map(comment, CommentDto.class);
				return commentDto;
	}
}
