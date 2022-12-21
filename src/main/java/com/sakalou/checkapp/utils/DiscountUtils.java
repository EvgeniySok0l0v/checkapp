package com.sakalou.checkapp.utils;

/**
 * class for discount
 */
public class DiscountUtils {

    /**
     * method count discount
     *
     * @param price - price
     * @param discount - discount in %
     * @return Double
     */
    public static Double discount(Double price, int discount){
        return (price / 100) * discount;
    }

    /**
     * method for count total price
     *
     * @param price - price
     * @param quantity - quantity
     * @return Double result
     */
    public static Double totalPrice(Double price, int quantity){
        return price * quantity;
    }

}
