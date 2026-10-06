package com.telusko.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
	@NotBlank(message = "Title cannot be empty")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	private String title;
	
	@NotBlank(message = "Content cannot be empty")
	@Size(min = 10, message = "Content must be at least 10 characters")
	private String content;
	
	@NotBlank(message = "Author cannot be empty")
	@Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
	private String author;

}
