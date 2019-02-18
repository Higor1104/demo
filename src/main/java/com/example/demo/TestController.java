package com.example.demo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	
	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		log.info("Endpoint Test called...");
		
		List<LogFuture> list = new ArrayList<>();
		
		list.add(new LogFuture("t1", 1));
		list.add(new LogFuture("t2", 1));
		list.add(new LogFuture("t3", 1));
		list.add(new LogFuture("t1", 2));
		list.add(new LogFuture("t2", 2));
		list.add(new LogFuture("t3", 2));
		list.add(new LogFuture("t4", 5));
		
		Map<String, List<LogFuture>> map = list.stream().collect(Collectors.groupingBy(LogFuture::getType));
		
		//System.out.println(map);
		
		Integer reduce = list.stream().map(LogFuture::getAge).reduce(0, (x,y) -> x-y);
		//System.out.println(reduce);
		
		String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        //Stream<String[]>
       	Arrays.stream(data).forEach(System.out::println);
        //System.out.println("\n");
        //Stream<String>, GOOD!
        Arrays.stream(data).flatMap(x -> Arrays.stream(x)).forEach(System.out::print);
        
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
        completableFuture.complete("Future's Result");
        //System.out.println(completableFuture.get());
        
        /*CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // Simulate a long-running Job
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                System.out.println("I'll run in a separate thread than the main thread.");
            }
        });*/

        // Block and wait for the future to complete
        //System.out.println(future.get());
        
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                System.out.println("Assyncronous");
                System.out.println(LocalTime.now().getNano());
                return "Result of the asynchronous computation";
            }
        }).thenApply(item -> "FUNCIONOU!")
        		.thenApply(item -> "DE NOVO!");

        // Block and get the result of the Future
        String result = future.get();
        System.out.println(result);
        System.out.println(LocalTime.now().getNano());
        
	}
	
}
