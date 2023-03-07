package com.sakalou.checkapp.mapper;

import com.sakalou.checkapp.dto.ProductDto;
import com.sakalou.checkapp.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductMapperTest {

    @Test
    public void productToProductDtoTest(){
        Product product = new Product(1L, "name", 100D, false);
        int quantity = 1;

        ProductDto expected = new ProductDto(
                "name",
                100D,
                false,
                quantity,
                0D,
                100D);

        ProductDto actual = ProductMapper.productToProductDto(product, quantity);

        Assertions.assertEquals(expected, actual);
    }
}
