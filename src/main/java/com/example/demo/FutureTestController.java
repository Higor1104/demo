package com.example.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testImage")
public class FutureTestController {

	private static final Logger log = LoggerFactory.getLogger(FutureTestController.class);

	@GetMapping
	public ResponseEntity<byte []> getImage() throws InterruptedException, ExecutionException {
		log.info("Endpoint Test called...");
		
		Future<byte []> doc = DocumentGen.getImage();
		
		while (!doc.isDone()) {
			log.warn("Retrieving image...");
			Thread.sleep(2000);
		}

		byte[] byteArray = doc.get();

		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", "image/jpeg");
	    //headers.add("Cache-Control", CacheControl.noCache().getHeaderValue());
		return new ResponseEntity<byte []>(byteArray, headers, HttpStatus.OK);
	}
	
}
