package com.sakalou.checkapp.service.impl;

import com.sakalou.checkapp.entity.Product;
import com.sakalou.checkapp.exception.ProductNotFoundException;
import com.sakalou.checkapp.repo.ProductRepo;
import com.sakalou.checkapp.service.ProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * simple service for product
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo repo;

    public ProductServiceImpl(ProductRepo repo) {
        this.repo = repo;
    }

    @Cacheable("products")
    @Override
    public Product getById(Long id) throws ProductNotFoundException {
        if(repo.findById(id).isPresent()){
            return repo.findById(id).get();
        }
        throw new ProductNotFoundException(id);
    }
}
