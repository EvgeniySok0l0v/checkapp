package com.sakalou.checkapp.mapper;

import com.sakalou.checkapp.constants.Constants;
import com.sakalou.checkapp.dto.ProductDto;
import com.sakalou.checkapp.entity.Check;
import com.sakalou.checkapp.web.response.CheckResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class CheckMapperTest {

    @Test
    public void checkToCheckResponseTest(){
        Check check = new Check(Set.of(
                new ProductDto("name", 100D, false, 1, 0D, 100D)));
        String level = "NONE";

        CheckResponse expected = new CheckResponse(
                Constants.CASHIER,
                Constants.SHOP,
                null,
                check.getProducts(),
                0D,
                level,
                100D);

        CheckResponse actual = CheckMapper.checkToCheckResponse(check,level);
        actual.setDateTime(null);

        Assertions.assertEquals(expected, actual);
    }
}
