package ru.tacocloud.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;
}
