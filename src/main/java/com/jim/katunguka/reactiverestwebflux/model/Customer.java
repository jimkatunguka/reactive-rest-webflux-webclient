package com.jim.katunguka.reactiverestwebflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private long custId;
    private String firstname;
    private String lastname;
    private int age;

}
