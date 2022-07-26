package com.ohouse.web.controller.shop.dto.orders;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedProductDto {
    private Long productSeq;
    private Integer adjustedPrice;
    private Integer amount;
}
