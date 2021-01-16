package com.jim.katunguka.reactiverestwebflux;

import com.jim.katunguka.reactiverestwebflux.model.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@SpringBootApplication
public class ReactiveRestWebfluxApplication {

	public static void main(String[] args)  {
		SpringApplication.run(ReactiveRestWebfluxApplication.class, args);
	}
}
