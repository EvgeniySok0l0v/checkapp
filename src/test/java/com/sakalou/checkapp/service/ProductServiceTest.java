package com.sakalou.checkapp.service;

import com.sakalou.checkapp.entity.Product;
import com.sakalou.checkapp.exception.ProductNotFoundException;
import com.sakalou.checkapp.repo.ProductRepo;
import com.sakalou.checkapp.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private final ProductRepo productRepo = Mockito.mock(ProductRepo.class);
    private final ProductService service = new ProductServiceImpl(productRepo);

    @Test
    public void getByIdTest() throws ProductNotFoundException {
        Product excepted = new Product(1L, "name", 100D, false);

        when(productRepo.findById(1L)).thenReturn(Optional.of(excepted));

        Product actual = service.getById(1L);

        Assertions.assertEquals(excepted,actual);
        verify(productRepo, times(2)).findById(1L);
    }
}
