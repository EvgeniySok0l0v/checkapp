package com.sakalou.checkapp.service;

import com.sakalou.checkapp.dto.ProductDto;
import com.sakalou.checkapp.entity.Check;
import com.sakalou.checkapp.entity.DiscountCard;
import com.sakalou.checkapp.entity.level.Level;
import com.sakalou.checkapp.exception.DiscountCardNotFoundException;
import com.sakalou.checkapp.repo.DiscountCardRepo;
import com.sakalou.checkapp.service.impl.DiscountCardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DiscountCardServiceTest {

    private final DiscountCardRepo discountCardRepo = Mockito.mock(DiscountCardRepo.class);
    private final DiscountCardService service = new DiscountCardServiceImpl(discountCardRepo);

    private Set<ProductDto> products = new HashSet<>(List.of(
            new ProductDto("name1", 100D, false, 2, 0.0, 200D),
            new ProductDto("name2", 200D, true, 5, 100D, 900D),
            new ProductDto("name3", 300D, false, 3, 0.0, 900D)
    ));

    private Check check = new Check(products);

    @Test
    public void makeDiscountBronzeTest(){
        DiscountCard card = new DiscountCard(1L, Level.BRONZE);

        Check excepted = new Check(products);
        excepted.setTotalDiscount(160D);
        excepted.setTotalPrice(1840D);

        Check actual = service.makeDiscount(check, card);

        Assertions.assertEquals(excepted,actual);
    }

    @Test
    public void makeDiscountSilverTest(){
        DiscountCard card = new DiscountCard(1L, Level.SILVER);

        Check excepted = new Check(products);
        excepted.setTotalDiscount(200D);
        excepted.setTotalPrice(1800D);

        Check actual = service.makeDiscount(check, card);

        Assertions.assertEquals(excepted,actual);
    }

    @Test
    public void makeDiscountGoldTest(){
        DiscountCard card = new DiscountCard(1L, Level.GOLD);

        Check excepted = new Check(products);
        excepted.setTotalDiscount(300D);
        excepted.setTotalPrice(1700D);

        Check actual = service.makeDiscount(check, card);

        Assertions.assertEquals(excepted,actual);
    }

    @Test
    public void makeDiscountPlatinumTest(){
        DiscountCard card = new DiscountCard(1L, Level.PLATINUM);

        Check excepted = new Check(products);
        excepted.setTotalDiscount(360D);
        excepted.setTotalPrice(1640D);

        Check actual = service.makeDiscount(check, card);

        Assertions.assertEquals(excepted,actual);
    }

    @Test
    public void getByIdTest() throws DiscountCardNotFoundException {
        DiscountCard excepted = new DiscountCard(1L, Level.PLATINUM);

        when(discountCardRepo.findById(1L)).thenReturn(Optional.of(excepted));

        DiscountCard actual = service.getById(1L);

        Assertions.assertEquals(excepted,actual);
        verify(discountCardRepo, times(2)).findById(any());
    }

    @Test
    public void createTest(){
        DiscountCard excepted = new DiscountCard(1L, Level.PLATINUM);

        when(discountCardRepo.save(excepted)).thenReturn(excepted);

        DiscountCard actual = service.create(excepted);

        Assertions.assertEquals(excepted, actual);
        verify(discountCardRepo, times(1)).save(any());
    }

   /* @Test
    public void getByIdExceptionTest() throws DiscountCardNotFoundException {
        DiscountCardNotFoundException excepted = new DiscountCardNotFoundException(1L);

        //when(discountCardRepo.findById(1L)).thenReturn(Optional.of(excepted));

        DiscountCard actual = service.getById(1L);

        Assertions.assertThrows(DiscountCardNotFoundException.class,);
        //verify(discountCardRepo, times(2)).findById(any());
    }*/
}
