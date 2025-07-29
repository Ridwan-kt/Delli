package com.controller;

import com.dtos.RequestProductDto;
import com.dtos.ResponseProductDto;
import com.entity.Product;
import com.service.ProductService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ResponseProductDto> addProduct(@RequestBody RequestProductDto product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {
        return new ResponseEntity<>(productService.saveProducts(products), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findAllProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<ResponseProductDto> findProductById(@PathVariable int id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<ResponseProductDto> findProductByName(@PathVariable String name) {
        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
