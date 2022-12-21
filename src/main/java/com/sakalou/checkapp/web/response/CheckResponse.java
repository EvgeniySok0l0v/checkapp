package com.sakalou.checkapp.web.response;

import com.sakalou.checkapp.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CheckResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckResponse {

    private String cashier;
    private String shop;
    private LocalDateTime dateTime;
    private Set<ProductDto> products;
    private Double totalDiscount;
    private String cardLevel;
    private Double totalPrice;

    private String productsToString(){
        AtomicReference<String> result = new AtomicReference<>("");
        products.forEach(p -> result.set(result + p.toString()));
        return result.get();
    }

    @Override
    public String toString() {
        return "\nCheckResponse{" +
                "\n\tcashier='" + cashier + '\'' +
                "\n\tshop='" + shop + '\'' +
                "\n\tdateTime=" + dateTime +
                "\n\tQTY\tNAME\tPRICE\tDISCOUNT\tTOTAL" +
                 productsToString() +
                "\n\ttotalDiscount=" + totalDiscount +
                "\n\tcardLevel='" + cardLevel + '\'' +
                "\n\ttotalPrice=" + totalPrice + "\n" +
                '}';
    }
}
