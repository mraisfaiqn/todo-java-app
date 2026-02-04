package dev.morafa.todo_app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors(cors -> cors.configure(http)) // Enable CORS support
			.csrf(csrf -> csrf.disable()) // Disable CSRF for testing with Postman/curl
			.authorizeHttpRequests(auth -> auth
					.anyRequest().permitAll() // Allow all requests without login
			);
		return http.build();
	}
}