package com.mongo.reactive.springboot_reactive_mongo_crud_junit.utils;

import com.mongo.reactive.springboot_reactive_mongo_crud_junit.dto.ProductDto;
import com.mongo.reactive.springboot_reactive_mongo_crud_junit.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {


    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}

