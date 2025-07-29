package com.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class ProductTest {

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product(10000L, "Test Product", 10, 99.99);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {
        assertEquals(testProduct.getId(), 10000L);
    }

    @Test
    void getName() {
        assertEquals(testProduct.getName(), "Test Product");
    }

    @Test
    void getQuantity() {
        assertEquals(testProduct.getQuantity(), 10);
    }

    @Test
    void getPrice() {
        assertEquals(testProduct.getPrice(), 99.99);
    }

    @Test
    void setId() {
        testProduct.setId(20000L);
        assertEquals(testProduct.getId(), 20000L);
    }

    @Test
    void setName() {
        testProduct.setName("Updated Product");
        assertEquals(testProduct.getName(), "Updated Product");
    }

    @Test
    void setQuantity() {
        testProduct.setQuantity(20);
        assertEquals(testProduct.getQuantity(), 20);
    }

    @Test
    void setPrice() {
        testProduct.setPrice(199.99);
        assertEquals(testProduct.getPrice(), 199.99);
    }
}