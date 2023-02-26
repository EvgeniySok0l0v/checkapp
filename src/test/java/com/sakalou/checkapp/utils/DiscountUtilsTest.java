package com.sakalou.checkapp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiscountUtilsTest {

    @Test
    public void discountTest(){
        Double price = 100D;
        int discount = 10;

        Double expected = 10D;

        Double actual = DiscountUtils.discount(price, discount);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void totalPriceTest(){
        Double price = 100D;
        int quantity = 10;

        Double expected = 1000D;

        Double actual = DiscountUtils.totalPrice(price, quantity);

        Assertions.assertEquals(expected,actual);
        //org.assertj.core.api.Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void checkNPEForTotalPrice(){
        Assertions.assertThrows(NullPointerException.class, () -> DiscountUtils.totalPrice(null, 1));
    }

    @Test
    public void checkNPEForDiscount(){
        Assertions.assertThrows(NullPointerException.class, () -> DiscountUtils.discount(null, 10));
    }

}
