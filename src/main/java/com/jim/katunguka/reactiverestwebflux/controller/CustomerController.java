package com.jim.katunguka.reactiverestwebflux.controller;

import com.jim.katunguka.reactiverestwebflux.model.Customer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class CustomerController {
    @GetMapping(value = "/customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomers(){
        Flux<Customer> customerFlux = Flux.just(
                new Customer(1L, "Walter", "White", 29),
                new Customer(1L, "Skyler", "White", 24),
                new Customer(1L, "Saul", "Goodman", 27),
                new Customer(1L, "Jesse", "Pinkman", 20)
        ).delayElements(Duration.ofSeconds(3));
        return  customerFlux;
    }
}
