package com.blogapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.app.entitys.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer>{

	
}
