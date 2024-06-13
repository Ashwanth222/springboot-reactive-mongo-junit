package com.mongo.reactive.springboot_reactive_mongo_crud_junit.repository;

import com.mongo.reactive.springboot_reactive_mongo_crud_junit.dto.ProductDto;
import com.mongo.reactive.springboot_reactive_mongo_crud_junit.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
