package com.sakalou.checkapp.mapper;

import com.sakalou.checkapp.constants.Constants;
import com.sakalou.checkapp.entity.Check;
import com.sakalou.checkapp.web.response.CheckResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.sakalou.checkapp.constants.Constants.FORMAT;

/**
 * mapper for checks
 */
public class CheckMapper {

    /**
     * method convert Check to CheckResponse and add something else
     *
     * @param check - Check
     * @param level - level of discount
     * @return CheckResponse
     */
    public static CheckResponse checkToCheckResponse(Check check, String level){
        CheckResponse response = new CheckResponse();
        response.setCashier(Constants.CASHIER);
        response.setShop(Constants.SHOP);
        response.setDateTime(LocalDateTime.now().format(FORMAT));
        response.setProducts(check.getProducts());
        response.setCardLevel(level);
        response.setTotalDiscount(check.getTotalDiscount());
        response.setTotalPrice(check.getTotalPrice());
        return response;
    }
}
