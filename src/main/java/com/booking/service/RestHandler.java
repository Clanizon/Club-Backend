package com.booking.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;


@Service
public class RestHandler {

	@Value( "${razorpay.url}" )
	private String razorPayurl;
	@Value( "${razorpay.razorPayKey}" )
	private String razorPayKey;
	@Value( "${razorpay.razorPayAuth}" )
	private String razorPayAuth;
	
	

   
	public Object callServicePost(String Url, Object request, Object response)
			throws IllegalArgumentException, IllegalAccessException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth(razorPayKey, razorPayAuth);
		//headers.setBasicAuth("rzp_test_GSWPMCWmuwtiQd", "BvMVoRteRFtydBXWYqvMslhT");
        String requestUrl= razorPayurl+Url;
		HttpEntity<Object> httprequest = new HttpEntity<>(request, headers);
		System.out.println("razorPayurl");
		System.out.println(requestUrl);
		return restTemplate.postForEntity(requestUrl, httprequest, Object.class).getBody();
		
	}

}