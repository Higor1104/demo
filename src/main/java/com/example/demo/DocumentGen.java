package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DocumentGen {
	
	private static final Logger log = LoggerFactory.getLogger(DocumentGen.class);
	
	private static ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public static Future<byte []> getImage() {
		RestTemplate template = new RestTemplate();
		return executor.submit(() -> {
			ResponseEntity<byte[]> object = template.getForEntity("https://thispersondoesnotexist.com", byte[].class);
			log.info("Image retrieved!");
			log.info("Http status: {} Size: {}", object.getStatusCode(), object.getBody().length);
			return object.getBody();
		});
	}
	
	
}
