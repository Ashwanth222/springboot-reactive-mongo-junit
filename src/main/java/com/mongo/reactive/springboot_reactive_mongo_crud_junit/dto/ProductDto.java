package com.mongo.reactive.springboot_reactive_mongo_crud_junit.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class ProductDto {

    private String id;

    @NotBlank(message = "Please enter proper product name")
    @Size(min=5, message = "Name should be atleast 5 characters")
    @Size(max=12, message = "Name should not be greater than 12 characters")
    private String name;

    @Min(value = 10, message = "{qty.min.requirement}")
    @Max(value = 50, message = "{qty.max.requirement}")
    private int qty;

    @Pattern(regexp = "([a-z])+@([a-z])+\\.com", message = "{email.pattern.mismatch}")
    private String email;

    @NotNull(message = "{price.not.null}")
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductDto(String id, String name, int qty, String email, double price) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.email = email;
        this.price = price;
    }

    public ProductDto() {
    }
}
