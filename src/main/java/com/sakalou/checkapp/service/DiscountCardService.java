package com.sakalou.checkapp.service;

import com.sakalou.checkapp.entity.Check;
import com.sakalou.checkapp.entity.DiscountCard;
import com.sakalou.checkapp.exception.DiscountCardNotFoundException;
import com.sakalou.checkapp.repo.DiscountCardRepo;

public interface DiscountCardService {

    DiscountCard create(DiscountCard card);

    DiscountCard getById(Long id) throws DiscountCardNotFoundException;

    Check makeDiscount(Check check, DiscountCard card);
}
