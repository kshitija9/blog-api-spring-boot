package com.telusko.blog.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.telusko.blog.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePostNotFound(PostNotFoundException ex, HttpServletRequest request){
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Not Found");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request){
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setError("Internal Server Error");
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request){
		String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
		
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Validation failed");
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(message);
		error.setPath(request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleBadCredentials(
	        BadCredentialsException ex, 
	        HttpServletRequest request) {
	    
	    ErrorResponse error = new ErrorResponse();
	    error.setTimestamp(LocalDateTime.now());
	    error.setStatus(HttpStatus.UNAUTHORIZED.value());
	    error.setError("Unauthorized");
	    error.setMessage("Invalid username or password");
	    error.setPath(request.getRequestURI());
	    
	    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}

}
