package com.telusko.blog.dto;

import com.telusko.blog.model.Post;

public class PostMapper {
	
	
	public static Post toEntity(PostRequest request) {
			Post post = new Post();
			post.setTitle(request.getTitle());
			post.setContent(request.getContent());
			post.setAuthor(request.getAuthor());
			return post;
	}
	
	public static PostResponse toResponse(Post post) {
		PostResponse response = new PostResponse();
		response.setId(post.getId());
		response.setTitle(post.getTitle());
		response.setAuthor(post.getAuthor());
		response.setContent(post.getContent());
		response.setCreatedAt(post.getCreatedAt());
		response.setUpdatedAt(post.getUpdatedAt());
		return response;
	}
	

}
