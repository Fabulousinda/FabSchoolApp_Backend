package com.fabiit.fabschoolapp.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fabiit.fabschoolapp.jwt.utils.ClaimValidatorInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // Allow CORS requests from Angular running on localhost:4200
//        registry.addMapping("/**")  // This applies to all paths
//                .allowedOrigins("http://localhost:4200")  // Specify Angular frontend URL
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow necessary HTTP methods
//                .allowedHeaders("*"); // Allow all headers
////                .allowCredentials(true)
////                ;  // If you need to send cookies or authentication tokens
//    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Register the interceptor and specify the URL patterns it should apply to
		registry.addInterceptor(new ClaimValidatorInterceptor()).addPathPatterns("/**"); // Apply to all paths (adjust
																							// as needed)
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration();

		// Allow specific origins (your Angular app, for example)
		corsConfig.addAllowedOrigin("http://localhost:4200"); // Replace with your frontend URL

		// Allow all methods
		corsConfig.addAllowedMethod("*"); // Allow GET, POST, PUT, DELETE, etc.

		// Allow all headers
		corsConfig.addAllowedHeader("*");

		// Allow credentials (cookies, JWT tokens, etc.)
		corsConfig.setAllowCredentials(true);

		// Set preflight cache duration (how long the browser can cache the CORS
		// preflight response)
		corsConfig.setMaxAge(3600L); // 1 hour

		// Create UrlBasedCorsConfigurationSource and add the CORS config
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig); // Apply CORS config globally

		return new CorsFilter(source);
	}
}