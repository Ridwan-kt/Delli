package com.dtos;

public record RequestProductDto (
        String name,
        int quantity,
        double price
){}