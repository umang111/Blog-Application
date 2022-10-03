package com.blogapp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.app.payloads.ApiResponse;
import com.blogapp.app.payloads.PostDto;
import com.blogapp.app.payloads.PostResponse;
import com.blogapp.app.services.PostService;

@RestController
public class PostController {

	@Autowired 
	private PostService postService;
	
	//create Post
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId,@RequestBody PostDto postDto) {
		PostDto postedUser=postService.createUser(userId,categoryId,postDto);
		return new ResponseEntity<PostDto>(postedUser,HttpStatus.CREATED);
	}
	
	//updatePost
	@PutMapping("/updatepost/{postid}")
	public ResponseEntity<PostDto> updatePost(@PathVariable("postid") Integer postId,@RequestBody PostDto postDto){
		PostDto updatePost=postService.updatePost(postId,postDto);
		return ResponseEntity.ok(updatePost);
	}
	
	//delete post
	
	@DeleteMapping("/deletePost/{PostId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("PostId") Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post Deleted Successfully",true),HttpStatus.OK);
	}
	
	//get post by id
	
	@GetMapping("/getPostId/{postId}")
	public ResponseEntity<PostDto> GetPostById(@PathVariable("postId") Integer postId){
		PostDto getPost =postService.getPostById(postId);
		return ResponseEntity.ok(getPost);
	}
	
	//get all post
	@GetMapping("/getAllPost")
	public PostResponse GetAllPost(@RequestParam(value="pageNumber",defaultValue="1",required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="3",required=false) Integer pageSize){
		PostResponse getPost =postService.getAllPost(pageNumber,pageSize);
		return getPost;
	}
	
	//get all post by category
	
	@GetMapping("/getAllPost/category/{categoryid}")
	public List<PostDto> getAllPostByCategory(@PathVariable("categoryid") Integer categoryId){
		List<PostDto> AllPost=postService.getAllPostByCategory(categoryId);
		return AllPost;
	}
	
	//get all post by user
	@GetMapping("/getAllPost/userId/{userid}")
	public List<PostDto> getAllPostByUserId(@PathVariable("userid") Integer userId){
		List<PostDto> AllPost=postService.getAllPostByUserId(userId);
		return AllPost;
	}
	//search post
	@GetMapping("/serchPost/{keyword}")
	public List<PostDto> searchPost(@PathVariable("keyword") String keyword){
		List<PostDto> post=postService.searchPost(keyword);
		return post;
	}
}













