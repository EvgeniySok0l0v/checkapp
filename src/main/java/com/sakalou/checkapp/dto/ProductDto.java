package com.sakalou.checkapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto class for products
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;
    private Double price;
    private boolean promotional;
    private int quantity;
    private Double discount;
    private Double totalPrice;

    @Override
    public String toString(){
        return "\n\t" + quantity + "\t" + name + "\t" +  price + "\t" + discount + "\t\t\t" + totalPrice;
    }
}
