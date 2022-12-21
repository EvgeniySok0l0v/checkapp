package com.sakalou.checkapp.mapper;

import com.sakalou.checkapp.constants.Constants;
import com.sakalou.checkapp.dto.ProductDto;
import com.sakalou.checkapp.entity.Product;
import com.sakalou.checkapp.utils.DiscountUtils;

/**
 * mapper for products
 */
public class ProductMapper {

    /**
     * method convert Product to ProductDto and do something discounts
     *
     * @param product - Product
     * @param quantity - quantity of products
     * @return ProductDto
     */
    public static ProductDto productToProductDto(Product product, int quantity){

        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setPromotional(product.isPromotional());
        productDto.setQuantity(quantity);

        Double discount = 0.0;
        if(product.isPromotional() && quantity >= Constants.MINIMAL_QUANTITY_FOR_DISCOUNT){
            discount = DiscountUtils.discount(product.getPrice() * quantity, Constants.DISCOUNT);
            productDto.setDiscount(discount);
        } else {
            productDto.setDiscount(discount);
        }

        productDto.setTotalPrice(DiscountUtils.totalPrice(product.getPrice(), quantity) - discount);
        return productDto;
    }
}
