package com.ohouse.web.shop.order.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedProductDto {
    private Long productSeq;
    private Integer adjustedPrice;
    private Integer amount;
}
