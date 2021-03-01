package com.jim.katunguka.reactiverestwebflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Widget {
    private int id;
    private String name;
    private String description;
    private int version_number;
}
