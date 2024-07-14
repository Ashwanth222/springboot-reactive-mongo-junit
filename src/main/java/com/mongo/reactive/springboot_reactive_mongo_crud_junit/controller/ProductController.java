package com.mongo.reactive.springboot_reactive_mongo_crud_junit.controller;

import com.mongo.reactive.springboot_reactive_mongo_crud_junit.dto.ProductDto;
import com.mongo.reactive.springboot_reactive_mongo_crud_junit.entity.Product;
import com.mongo.reactive.springboot_reactive_mongo_crud_junit.exception.ResourceNotFoundException;
import com.mongo.reactive.springboot_reactive_mongo_crud_junit.repository.ProductRepository;
import com.mongo.reactive.springboot_reactive_mongo_crud_junit.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;
    @GetMapping
    public Flux<ProductDto> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Product>> getProduct(@PathVariable String id){
        Mono<Product> product = repository.findById(id).switchIfEmpty(
               Mono.error(new ResourceNotFoundException("product not found with Id: " + id)));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product-range")
    public Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max")double max){
        return service.getProductInRange(min,max);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@Valid  @RequestBody Mono<ProductDto> productDtoMono){
        System.out.println("controller method called ...");
        return service.saveProduct(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono,@PathVariable String id){
        return service.updateProduct(productDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return service.deleteProduct(id);
    }

}

