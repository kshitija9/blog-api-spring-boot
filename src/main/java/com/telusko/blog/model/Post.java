package com.telusko.blog.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Title cannot be empty")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	@Column(nullable=false)
	private String title;
	
	@NotBlank(message = "Content cannot be empty")
	@Size(min = 10, message = "Content must be at least 10 characters")
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;
	
	@NotBlank(message = "Author cannot be empty")
	@Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
	@Column(nullable=false)
	private String author;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void someMethodName() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void anotherMethodName() {
    updatedAt = LocalDateTime.now();
	}

}
