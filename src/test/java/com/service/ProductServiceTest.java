package com.service;

import com.dtos.RequestProductDto;
import com.dtos.ResponseProductDto;
import com.entity.Product;
import com.exceptions.ProductNotFoundException;
import com.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    private Product testProduct1;
    private Product testProduct2;
    private List<Product> testProducts;

    @BeforeEach
    void setUp() {
        testProduct1 = new Product(1L, "Yam", 10, 5000.99);
        testProduct2 = new Product(2L, "Bags of rice", 30, 100202.99);
        testProducts = List.of(testProduct1, testProduct2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("test_to_confirm_product_can_be_saved")
    void saveProduct(){
        when(productRepository.save(any(Product.class))).thenReturn(testProduct1);
        var response = productService.saveProduct(new RequestProductDto("Yam", 10, 5000.99));

        assertEquals("Yam", response.name(), () -> new ProductNotFoundException("Product not found with name: Yam").getMessage());
        assertEquals(10, response.quantity(), () -> new ProductNotFoundException("Product not found with name: Yam").getMessage());
        assertEquals(5000.99, response.price(), () -> new ProductNotFoundException("Product not found with name: Yam").getMessage());

    }

    @Test
    void saveProducts() {
    }

    @Test
    @DisplayName("test_to_confirm_all_products_can_be_retrieved")
    void getProducts() {
        when(productRepository.findAll()).thenReturn(testProducts);

        var products = productService.getProducts();
        assertEquals(products.getFirst().getName(), "Yam", ()-> new ProductNotFoundException("bush not found").getMessage());
        assertEquals(products.get(1).getQuantity(), 30, ()-> new ProductNotFoundException("goods not found").getMessage());
    }

    @Test
    @DisplayName("test_to_confirm_product_can_be_retrieved_by_id")
    void getProductById() {
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(testProduct1));
        var product = productService.getProductById(1L);
        assertEquals("Yam", product.name(), () -> new ProductNotFoundException("Product not found with id: 1").getMessage());
        assertEquals(10, product.quantity(), () -> new ProductNotFoundException("Product not found with id: 1").getMessage());
        assertEquals(5000.99, product.price(), () -> new ProductNotFoundException("Product not found with id: 1").getMessage());
    }

    @Test
    @DisplayName("test_to_confirm_product_can_be_retrieved_by_name")
    void getProductByName() {
        when(productRepository.findByName("Yam")).thenReturn(java.util.Optional.of(testProduct1));
        var product = productService.getProductByName("Yam");
        assertEquals(product, productService.getProductByName("Yam"));
        assertEquals("Yam", product.name(), () -> new ProductNotFoundException("Product not found with name: Yam").getMessage());
        assertEquals(10, product.quantity(), () -> new ProductNotFoundException("Product not found with name: Yam").getMessage());
        assertEquals(5000.99, product.price(), () -> new ProductNotFoundException("Product not found with name: Yam").getMessage());
    }

    @Test
    @DisplayName("test_to_confirm_product_can_be_deleted_by_id")
    void deleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);
        String response = productService.deleteProduct(1L);
        assertEquals("product removed !! 1", response, () -> new ProductNotFoundException("Product not found with id: 1").getMessage());
    }

    @Test
    @DisplayName("test_to_confirm_product_can_be_updated")
    void updateProduct() {
        when(productRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(testProduct1));
        Product updatedProduct = new Product(1L, "Yam", 20, 6000.99);
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        var response = productService.updateProduct(updatedProduct);
        assertEquals("Yam", response.getName(), () -> new ProductNotFoundException("Product not found with id: 1").getMessage());
        assertEquals(20, response.getQuantity(), () -> new ProductNotFoundException("Product not found with id: 1").getMessage());
        assertEquals(6000.99, response.getPrice(), () -> new ProductNotFoundException("Product not found with id: 1").getMessage());
    } 
}