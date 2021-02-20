package com.jim.katunguka.reactiverestwebflux.controller;

import com.jim.katunguka.reactiverestwebflux.model.Customer;
import com.jim.katunguka.reactiverestwebflux.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("call")
public class CustomerController {
    @Autowired
    private WidgetService widgetService;

    @GetMapping(value = "/customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomers(){
        Flux<Customer> customerFlux = Flux.just(
                new Customer(1L, "Walter", "White", 29),
                new Customer(2L, "Skyler", "White", 24),
                new Customer(3L, "Saul", "Goodman", 27),
                new Customer(4L, "Jesse", "Pinkman", 20)
        ).delayElements(Duration.ofSeconds(3));
        return  customerFlux;
    }

    @GetMapping("widgetDesc")
    public  String getString(){
        return widgetService.getDescription();
    }
}
