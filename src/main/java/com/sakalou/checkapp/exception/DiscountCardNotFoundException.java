package com.sakalou.checkapp.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

/**
 * DiscountCardNotFoundException class for trow exc if card not found in db
 */
public class DiscountCardNotFoundException extends ChangeSetPersister.NotFoundException {

    private final String message;
    public DiscountCardNotFoundException(Long id){
        super();
        message = "\nCard with id:" + id + " Not found!";
        }

    @Override
    public String getMessage() {
        return message;
    }
}
