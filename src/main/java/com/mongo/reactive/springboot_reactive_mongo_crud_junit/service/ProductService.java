package com.mongo.reactive.springboot_reactive_mongo_crud_junit.service;

import com.mongo.reactive.springboot_reactive_mongo_crud_junit.dto.ProductDto;
import com.mongo.reactive.springboot_reactive_mongo_crud_junit.entity.Product;
import com.mongo.reactive.springboot_reactive_mongo_crud_junit.exception.ResourceNotFoundException;
import com.mongo.reactive.springboot_reactive_mongo_crud_junit.repository.ProductRepository;
import com.mongo.reactive.springboot_reactive_mongo_crud_junit.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Flux<ProductDto> getProducts(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<Product> getProduct(String id){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("product not found with Id: " + id)));
    }

    public Flux<ProductDto> getProductInRange(double min,double max){
        return repository.findByPriceBetween(Range.closed(min,max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        System.out.println("service method called ...");
        return productDtoMono
                .doOnNext(System.out::println);
//        return  productDtoMono.map(AppUtils::dtoToEntity)
//                .flatMap(repository::insert)
//                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id){
        return repository.findById(id)
                .flatMap(p->productDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);

    }

    public Mono<Void> deleteProduct(String id){

        return repository.deleteById(id);
    }
}

