package com.sakalou.checkapp.service;

import com.sakalou.checkapp.entity.Product;
import com.sakalou.checkapp.exception.ProductNotFoundException;

public interface ProductService {

    Product getById(Long id) throws ProductNotFoundException;

}
