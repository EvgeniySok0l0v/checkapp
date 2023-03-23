package com.sakalou.checkapp.config;

import com.sakalou.checkapp.entity.DiscountCard;
import com.sakalou.checkapp.entity.Product;
import com.sakalou.checkapp.entity.level.Level;
import com.sakalou.checkapp.repo.DiscountCardRepo;
import com.sakalou.checkapp.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * config class for preloading data to db
 */
@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ProductRepo productRepo, DiscountCardRepo discountCardRepo) {
        return args -> {
            log.info("Add products:\n");
            log.info("Preloading:" + productRepo.save(new Product(
                    1L,
                    "Hleb",
                    1.70,
                    false))
            );
            log.info("Preloading:" + productRepo.save(new Product(
                    2L,
                    "Moloko",
                    2.05,
                    true))
            );
            log.info("Preloading:" + productRepo.save(new Product(
                    3L,
                    "Baton",
                    2.20,
                    false))
            );
            log.info("Preloading:" + productRepo.save(new Product(
                    7L,
                    "Pivo",
                    1.45,
                    false))
            );
            log.info("Preloading:" + productRepo.save(new Product(
                    5L,
                    "Yogurt",
                    1.00,
                    true))
            );

            log.info("Add discount cards:\n");
            log.info("Preloading:" + discountCardRepo.save(new DiscountCard(
                    1234L,
                    Level.BRONZE))
            );
            log.info("Preloading:" + discountCardRepo.save(new DiscountCard(
                    2345L,
                    Level.SILVER))
            );
            log.info("Preloading:" + discountCardRepo.save(new DiscountCard(
                    3456L,
                    Level.GOLD))
            );
            log.info("Preloading:" + discountCardRepo.save(new DiscountCard(
                    4567L,
                    Level.PLATINUM))
            );
        };
    }
}
