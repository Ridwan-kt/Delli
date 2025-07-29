package com.service;

import com.dtos.RequestProductDto;
import com.dtos.ResponseProductDto;
import com.entity.Product;
import com.exceptions.ProductAlreadyExistException;
import com.exceptions.ProductNotFoundException;
import com.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ResponseProductDto saveProduct(RequestProductDto productDto) {
        if(productRepository.existsByName(productDto.name())) {
            throw new ProductAlreadyExistException("Product already exists with name: " + productDto.name());
        }
        Product product = Product
                .builder()
                .name(productDto.name())
                .price(productDto.price())
                .quantity(productDto.quantity())
                .build();
        productRepository.save(product);
        return new ResponseProductDto(product.getName(), product.getQuantity(), product.getPrice());
    }

    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public List<Product> getProducts() {
        try {
            return productRepository.findAll();
        }catch (ProductNotFoundException e) {
            throw new ProductNotFoundException("Products not found");
        }
    }

    public ResponseProductDto getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return new ResponseProductDto(
                product.getName(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    public ResponseProductDto getProductByName(String name) {
        Product product =  productRepository.findByName(name).orElseThrow(()-> new ProductNotFoundException("Product not found with name: " + name));
        return new ResponseProductDto(
                product.getName(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    public String deleteProduct(long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return "product removed !! " + id;
        }
        return "product not found with id: " + id;

    }

    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }


}
