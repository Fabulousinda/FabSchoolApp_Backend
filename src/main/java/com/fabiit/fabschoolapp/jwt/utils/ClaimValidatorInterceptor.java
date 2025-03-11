package com.fabiit.fabschoolapp.jwt.utils;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClaimValidatorInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Get the resource or URI being requested
		String requestUri = request.getRequestURI();
		String remoteUser = request.getMethod();
		String contentType = request.getContentType();
		String parameter = request.getParameter("announceType");
		// Log or process the URI as needed
		System.out.println("Requested Resource: " + requestUri);

		// You can perform other checks or actions here.

		// Return true to allow the request to continue
		return true;
	}
}
