package com.sakalou.checkapp.service.impl;

import com.sakalou.checkapp.entity.Check;
import com.sakalou.checkapp.entity.DiscountCard;
import com.sakalou.checkapp.entity.level.Level;
import com.sakalou.checkapp.exception.DiscountCardNotFoundException;
import com.sakalou.checkapp.repo.DiscountCardRepo;
import com.sakalou.checkapp.service.DiscountCardService;
import com.sakalou.checkapp.utils.DiscountUtils;
import org.springframework.stereotype.Service;

/**
 * Service for discount card
 */
@Service
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepo repo;

    public DiscountCardServiceImpl(DiscountCardRepo repo) {
        this.repo = repo;
    }

    @Override
    public DiscountCard create(DiscountCard card) {
        return repo.save(card);
    }

    @Override
    public DiscountCard getById(Long id) throws DiscountCardNotFoundException {
        if(repo.findById(id).isPresent()){
            return repo.findById(id).get();
        }
        throw new DiscountCardNotFoundException(id);
    }

    /**
     * method make discount by card
     * @param check - Check
     * @param card - DiscountCard
     * @return Check
     */
    @Override
    public Check makeDiscount(Check check, DiscountCard card) {
        Double currentDiscount = check.getTotalDiscount();
        Double currentTotalPrice = check.getTotalPrice();
        switch (card.getLevel()){
            case BRONZE -> {
                check.setTotalDiscount(
                        currentDiscount + DiscountUtils.discount(currentTotalPrice, Level.BRONZE.getDiscountValue()));
                check.setTotalPrice(currentTotalPrice - check.getTotalDiscount());
            }
            case SILVER -> {
                check.setTotalDiscount(
                        currentDiscount + DiscountUtils.discount(currentTotalPrice, Level.SILVER.getDiscountValue()));
                check.setTotalPrice(currentTotalPrice - check.getTotalDiscount());
            }
            case GOLD -> {
                check.setTotalDiscount(
                        currentDiscount + DiscountUtils.discount(currentTotalPrice, Level.GOLD.getDiscountValue()));
                check.setTotalPrice(currentTotalPrice - check.getTotalDiscount());
            }
            case PLATINUM -> {
                check.setTotalDiscount(
                        currentDiscount + DiscountUtils.discount(currentTotalPrice, Level.PLATINUM.getDiscountValue()));
                check.setTotalPrice(currentTotalPrice - check.getTotalDiscount());
            }
        }
        return check;
    }
}
