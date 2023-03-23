package com.sakalou.checkapp.facade;

import com.sakalou.checkapp.dto.ProductDto;
import com.sakalou.checkapp.entity.Check;
import com.sakalou.checkapp.entity.DiscountCard;
import com.sakalou.checkapp.entity.Product;
import com.sakalou.checkapp.exception.DiscountCardNotFoundException;
import com.sakalou.checkapp.exception.ProductNotFoundException;
import com.sakalou.checkapp.mapper.CheckMapper;
import com.sakalou.checkapp.mapper.ProductMapper;
import com.sakalou.checkapp.service.DiscountCardService;
import com.sakalou.checkapp.service.ProductService;
import com.sakalou.checkapp.web.request.ProductRequest;
import com.sakalou.checkapp.web.response.CheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.sakalou.checkapp.utils.PdfUtils.generateCheckPDF;

/**
 * Facade class for working with data of check
 */
@Slf4j
@Component
public class CheckFacade {

    private final ProductService productService;
    private final DiscountCardService discountCardService;
    private static int counterId = 1;

    public CheckFacade(ProductService productService, DiscountCardService discountCardService) {
        this.productService = productService;
        this.discountCardService = discountCardService;
    }

    /**
     * method for create check response
     *
     * @param productRequestArray - array of id and quantity of product
     * @param cardId - id of card
     * @return - check response
     */
    public CheckResponse createCheckResponse(ProductRequest[] productRequestArray, Long cardId){

        Check check = new Check(generateSetOfProductDto(productRequestArray));
        DiscountCard card;

        try {
            card = discountCardService.getById(cardId);
            check = discountCardService.makeDiscount(check, card);
            log.info("Discount by card was done.\n");
        } catch (DiscountCardNotFoundException e) {
            log.warn(e.getMessage(), new DiscountCardNotFoundException(cardId));
            card = null;
        }

        CheckResponse response;

        if(card == null){
            response = CheckMapper.checkToCheckResponse(check,"NONE");
        } else {
            response = CheckMapper.checkToCheckResponse(check, card.getLevel().name());
        }


        generateCheckPDF(response, counterId++);

        return response;
    }

    /**
     * method for create set of products
     *
     * @param requests - array of ProductRequest
     * @return - set of ProductDtos
     */
    private Set<ProductDto> generateSetOfProductDto(ProductRequest[] requests) {
        Set<ProductDto> productDtoSet = new HashSet<>();
        for(ProductRequest request : magic(requests)){
            try {
                Product product = productService.getById(request.getId());
                ProductDto productDto = ProductMapper.productToProductDto(product, request.getQuantity());
                productDtoSet.add(productDto);
                log.info("Product with id" + request.getId() + " was add to set.");
            } catch (ProductNotFoundException e) {
                log.warn(e.getMessage(), new ProductNotFoundException(request.getId()));
            }
        }
        return productDtoSet;
    }

    /**
     * MAGIC
     * не придумал как лучше избежать ситуации типа:
     * [{id=1,quantity=3},{id=1,quantity=4}] получается 7 продуктов по 1 и тому же id
     * без этого метода скидка не делается, т.к объекты пришли раздельно
     * @param requests - array of request
     * @return - list of request
     */
    private List<ProductRequest> magic(ProductRequest[] requests){
        Map<Long, Integer> map = new HashMap<>();
        for (ProductRequest request : requests){
            if(!map.containsKey(request.getId())){
                map.put(request.getId(),request.getQuantity());
            } else {
                map.replace(request.getId(), map.get(request.getId()) + request.getQuantity());
            }
        }
        List<ProductRequest> list = new ArrayList<>();
        map.forEach((k,v) -> list.add(new ProductRequest(k, v)));
        return list;
    }
}
