package com.telusko.blog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.blog.dto.PostMapper;
import com.telusko.blog.dto.PostRequest;
import com.telusko.blog.dto.PostResponse;
import com.telusko.blog.model.Post;
import com.telusko.blog.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService service;
	
	
	@PostMapping
	public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest request) {
		 Post savedPost = service.createPost(request);
		 return new ResponseEntity<>(PostMapper.toResponse(savedPost), HttpStatus.CREATED);
		 
	}
	
	@GetMapping
	public ResponseEntity<List<PostResponse>> getAllPosts(){
		List<Post> posts = service.getAllPosts();
		List<PostResponse> responses = posts.stream()
				.map(PostMapper::toResponse)
                .collect(Collectors.toList());
		return ResponseEntity.ok(responses);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> getPostById(@PathVariable Long id){
		Post post = service.getPostById(id);
		return ResponseEntity.ok(PostMapper.toResponse(post));
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<PostResponse> updatePost(@PathVariable Long id,@Valid @RequestBody PostRequest request){
		Post post = service.updatePost(id, request);
		return ResponseEntity.ok(PostMapper.toResponse(post));
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id){
			service.deletePost(id);
			return ResponseEntity.ok("Post deleted successfully!");
	}

}
