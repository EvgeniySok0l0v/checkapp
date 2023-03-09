package com.sakalou.checkapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private boolean promotional;
    @NotNull
    private int quantity;
    @NotNull
    private Double discount;
    @NotNull
    private Double totalPrice;

    @Override
    public String toString(){
        return "\n\t" + quantity + "\t" + name + "\t" +  price + "\t" + discount + "\t\t\t" + totalPrice;
    }
}
