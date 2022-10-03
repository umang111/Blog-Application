package com.blogapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blogapp.app.entitys.Post;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

	@Query("select post from Post as post where categorys.categoryId =?1")
	public List<Post> getPostByCategoryId(Integer categoryId);
	
	@Query("select post from Post as post where users.id=?1")
	public List<Post> grtPostByUserId(Integer userId);

	@Query("select p from Post as p where p.title like :key")
	public List<Post> searchPost(@Param("key") String keyword);
	
	
}
