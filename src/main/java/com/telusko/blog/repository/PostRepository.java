package com.telusko.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.blog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
