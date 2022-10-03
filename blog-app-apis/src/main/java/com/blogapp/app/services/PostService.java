package com.blogapp.app.services;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blogapp.app.entitys.Category;
import com.blogapp.app.entitys.Post;
import com.blogapp.app.entitys.User;
import com.blogapp.app.exceptions.resourceNotFoundException;
import com.blogapp.app.payloads.PostDto;
import com.blogapp.app.payloads.PostResponse;
import com.blogapp.app.repositories.CategoryRepo;
import com.blogapp.app.repositories.PostRepo;
import com.blogapp.app.repositories.UserRepository;

@Service
public class PostService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo; 
	
	@Autowired 
	private UserRepository userRepo;   
	
	@Autowired
	private PostRepo postRepo;

	public PostDto createUser(Integer userId, Integer categoryId, PostDto postDto) { 
		User user= userRepo.findById(userId).get();
		Category category = categoryRepo.findById(categoryId).get();
		Post post =PostDtoToPost(postDto);
		post.setImageName("defaultImage.png");
		post.setAddedDate(new Date(categoryId));
		post.setUsers(user);
		post.setCategorys(category);
		Post postedUser=postRepo.save(post);
		PostDto postedUserDto=modelMapper.map(postedUser, postDto.getClass());
		return postedUserDto;
	}

	public PostDto updatePost(Integer postId, PostDto postDto) {
		Post post =postRepo.findById(postId).get();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post updatedPost =postRepo.save(post);
		return postToPostDto(updatedPost);
	}
	


	public void deletePost(Integer ostId) {
		Post post =postRepo.findById(ostId).get();
		postRepo.delete(post);
	}

	public PostDto getPostById(Integer postId) {
		Post post =postRepo.findById(postId).get();
		return postToPostDto(post);
	}

	public PostResponse getAllPost(Integer pageNumber,Integer pageSize) {
		Page<Post> allPost=postRepo.findAll(PageRequest.of(pageNumber,pageSize));
//		List<Post> allPostobject =allPost.getContent();
		List<PostDto> getAllPost =allPost.stream().map(post->postToPostDto(post)).collect(Collectors.toList());
		PostResponse postResponse =new PostResponse();
		
		postResponse.setContent(getAllPost);
		postResponse.setPageNumber(allPost.getNumber());
		postResponse.setPageSize(allPost.getSize());
		postResponse.setTotalElement((int) allPost.getTotalElements());
		postResponse.setTotalPages(allPost.getTotalPages());
		postResponse.setLastPage(allPost.isLast());
		return postResponse;
	}
	
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		Category checkCategory = categoryRepo.findById(categoryId).orElseThrow(()-> new resourceNotFoundException("Category","Id",categoryId));
		List<Post> allPost=postRepo.getPostByCategoryId(checkCategory.getCategoryId());
		List<PostDto> Apost= allPost.stream().map(post->postToPostDto(post)).collect(Collectors.toList());
		return Apost;
	}

	public List<PostDto> getAllPostByUserId(Integer userId) {
		List<Post> listOfPost= postRepo.grtPostByUserId(userId);
		List<PostDto> listOfPostDto =listOfPost.stream().map(post->postToPostDto(post)).collect(Collectors.toList());
		return listOfPostDto;
	}
	
	public List<PostDto> searchPost(String keyword) {
		List<Post> post =postRepo.searchPost("%"+keyword+"%");
		List<PostDto> listOfPostDto =post.stream().map(posts->postToPostDto(posts)).collect(Collectors.toList());
		return listOfPostDto;
	}
	
	
	
	
//	*********************************************************
	public Post PostDtoToPost(PostDto postDto) {
		Post post =modelMapper.map(postDto, Post.class);
		return post;
	}
	
	public PostDto postToPostDto(Post post) {
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;
	}
	
//***********************************************************
}





	







