package com.booking.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class UserAgentFilter implements Filter   {
	//implements Filter
    private static final String ALLOWED_ORIGIN = "https://teetimebgc.com";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		 // Cast ServletRequest and ServletResponse to HttpServletRequest/HttpServletResponse
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        // Check the User-Agent header
//        String userAgent = httpRequest.getHeader("User-Agent");
//        if (userAgent != null && (userAgent.contains("Postman") || userAgent.contains("PostmanRuntime"))) {
//            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            httpResponse.getWriter().write("Access Forbidden: Postman requests are not allowed.");
//            return;
//        }
//
//        // Continue with the next filter in the chain
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Initialization logic if needed
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup logic if needed
//	}
	
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;

	        // Extract the Origin header
	        String originHeader = httpRequest.getHeader("Origin");

	       

	        // Allow the request to proceed
	        chain.doFilter(request, response);
	    }

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    }

	    @Override
	    public void destroy() {
	    }
	}

