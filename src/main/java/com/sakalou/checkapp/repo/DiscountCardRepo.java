package com.sakalou.checkapp.repo;

import com.sakalou.checkapp.entity.DiscountCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCardRepo extends CrudRepository<DiscountCard, Long> {
}
