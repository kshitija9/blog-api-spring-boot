	package com.telusko.blog.security;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
	import org.springframework.security.core.context.SecurityContextHolder;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
	import org.springframework.stereotype.Component;
	import org.springframework.web.filter.OncePerRequestFilter;
	   
	import com.telusko.blog.service.MyUserDetailsService;
	import java.io.IOException;   
	import jakarta.servlet.FilterChain;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;

	@Component
	public class JwtFilter extends OncePerRequestFilter{
		@Autowired
		private JwtUtil jwtUtil;

		@Autowired
		private MyUserDetailsService userDetailsService;
		
		@Override
		protected void doFilterInternal(
		        HttpServletRequest request,
		        HttpServletResponse response,
		        FilterChain filterChain) throws ServletException, IOException {
			String authHeader = request.getHeader("Authorization");
			String token = null;
			String username = null;
			
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
			    token = authHeader.substring(7);      // Remove "Bearer " prefix (7 chars)
			    username = jwtUtil.extractUsername(token);
			}
			
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			    
			    if (jwtUtil.validateToken(token, username)) {
			        // Create authentication token
			        UsernamePasswordAuthenticationToken authToken = 
			            new UsernamePasswordAuthenticationToken(
			                userDetails, 
			                null, 
			                userDetails.getAuthorities()
			            );
			        
			        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			        
			        // Set authentication in SecurityContext
			        SecurityContextHolder.getContext().setAuthentication(authToken);
			    }
			}
			
			filterChain.doFilter(request, response);
		}

	}



