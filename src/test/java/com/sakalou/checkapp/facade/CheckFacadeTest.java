package com.sakalou.checkapp.facade;

import com.sakalou.checkapp.constants.Constants;
import com.sakalou.checkapp.dto.ProductDto;
import com.sakalou.checkapp.entity.Check;
import com.sakalou.checkapp.entity.DiscountCard;
import com.sakalou.checkapp.entity.Product;
import com.sakalou.checkapp.entity.level.Level;
import com.sakalou.checkapp.exception.DiscountCardNotFoundException;
import com.sakalou.checkapp.exception.ProductNotFoundException;
import com.sakalou.checkapp.service.DiscountCardService;
import com.sakalou.checkapp.service.ProductService;
import com.sakalou.checkapp.web.request.ProductRequest;
import com.sakalou.checkapp.web.response.CheckResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.mockito.Mockito.*;

public class CheckFacadeTest {

    private final ProductService productService = Mockito.mock(ProductService.class);
    private final DiscountCardService discountCardService = Mockito.mock(DiscountCardService.class);
    private final CheckFacade facade = new CheckFacade(productService, discountCardService);

    @Test
    public void createCheckResponseTest1() throws ProductNotFoundException, DiscountCardNotFoundException {

        ProductRequest[] requests = new ProductRequest[]{
          new ProductRequest(1L,1),
          new ProductRequest(1L, 5),
          new ProductRequest(2L, 3)
        };

        Product product1 = new Product(1L,"name1", 100D, false);
        Product product2 = new Product(2L,"name2", 100D, false);

        when(productService.getById(1L)).thenReturn(product1);
        when(productService.getById(2L)).thenReturn(product2);


        Check check = new Check(Set.of(
                new ProductDto("name1", 100D, false, 6, 0D, 600D),
                new ProductDto("name2", 100D, false, 3, 0D, 300D))
        );


        //when(discountCardService.getById(any())).thenReturn(null);
        when(discountCardService.makeDiscount(check, null)).thenReturn(check);

        CheckResponse excepted = new CheckResponse(
             Constants.CASHIER,
             Constants.SHOP,
             null,
             check.getProducts(),
             0D,
             "NONE",
             900D);


        CheckResponse actual = facade.createCheckResponse(requests, 1L);
        actual.setDateTime(null);

        Assertions.assertEquals(excepted, actual);
        verify(productService, times(2)).getById(any());
        verify(discountCardService).makeDiscount(any(),any());
    }

    @Test
    public void createCheckResponseTest2() throws ProductNotFoundException, DiscountCardNotFoundException {

        ProductRequest[] requests = new ProductRequest[]{
                new ProductRequest(1L,1),
                new ProductRequest(1L, 5),
                new ProductRequest(2L, 3)
        };

        Check check = new Check(Set.of(
                new ProductDto("name1", 100D, false, 6, 0D, 600D),
                new ProductDto("name2", 100D, false, 3, 0D, 300D))
        );

        Product product1 = new Product(1L,"name1", 100D, false);
        Product product2 = new Product(2L,"name2", 100D, false);

        when(productService.getById(1L)).thenReturn(product1);
        when(productService.getById(2L)).thenReturn(product2);

        DiscountCard card = new DiscountCard(1L, Level.GOLD);
        Check checkAfterDiscount = new Check(check.getProducts());
        checkAfterDiscount.setTotalDiscount(90D);
        checkAfterDiscount.setTotalPrice(810D);

        when(discountCardService.getById(1L)).thenReturn(card);
        when(discountCardService.makeDiscount(check, card)).thenReturn(checkAfterDiscount);



        CheckResponse excepted = new CheckResponse(
                Constants.CASHIER,
                Constants.SHOP,
                null,
                check.getProducts(),
                90D,
                "GOLD",
                810D);


        CheckResponse actual = facade.createCheckResponse(requests, 1L);
        actual.setDateTime(null);

        Assertions.assertEquals(excepted, actual);
        verify(productService, times(2)).getById(any());
        verify(discountCardService).getById(any());
        verify(discountCardService).makeDiscount(any(),any());
    }
}
