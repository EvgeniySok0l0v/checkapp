package com.sakalou.checkapp.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

/**
 * ProductNotFoundException class for trow exc if product not found by id
 */
public class ProductNotFoundException extends ChangeSetPersister.NotFoundException {

    private final String message;
    public ProductNotFoundException(Long id){
        super();
        message = "\nProduct with id:" + id + " Not found!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
