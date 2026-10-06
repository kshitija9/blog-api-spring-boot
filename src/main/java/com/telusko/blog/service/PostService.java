package com.telusko.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.blog.dto.PostMapper;
import com.telusko.blog.dto.PostRequest;
import com.telusko.blog.exception.PostNotFoundException;
import com.telusko.blog.model.Post;
import com.telusko.blog.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepo;
	
	public Post createPost(PostRequest request) {
		Post post = PostMapper.toEntity(request);
		return postRepo.save(post);
	}
	
	public List<Post> getAllPosts() {
		return postRepo.findAll();
	}
	
	public Post getPostById(Long id) {
		return postRepo.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with id :" + id));
	}
	
	public Post updatePost(Long id, PostRequest request) {
		Post existing = getPostById(id);
		existing.setTitle(request.getTitle());
		existing.setContent(request.getContent());
		existing.setAuthor(request.getAuthor());
		return postRepo.save(existing);
	}
	
	public void deletePost(Long id) {
		getPostById(id);
		postRepo.deleteById(id);
	}

}
