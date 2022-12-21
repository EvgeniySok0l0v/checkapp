package com.sakalou.checkapp.entity;

import com.sakalou.checkapp.dto.ProductDto;
import lombok.Data;

import java.util.Set;

/**
 * Not entity class запихнул сюда, потому-что понятия не имею где он должен лежать...
 */
@Data
public class Check {

    private Set<ProductDto> products;
    private Double totalDiscount;
    private Double totalPrice;

    public Check(Set<ProductDto> products){
        this.products = products;
        this.totalDiscount = 0.0;
        products.forEach(p -> totalDiscount = totalDiscount + p.getDiscount());
        this.totalPrice = 0.0;
        products.forEach(p -> totalPrice = totalPrice + p.getTotalPrice());
    }

}
