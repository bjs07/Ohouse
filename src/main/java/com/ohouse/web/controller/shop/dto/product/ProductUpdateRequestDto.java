package com.ohouse.web.controller.shop.dto.product;

import com.ohouse.web.domain.shop.item.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductUpdateRequestDto {
    private Item item;
    private String productName;
    private Integer price;
    private Integer stock;
    private Integer rateDiscount;
    private String size;
    private String color;

    @Builder
    public ProductUpdateRequestDto(Item item, String productName, Integer price, Integer stock, Integer rateDiscount, String size, String color) {
        this.item = item;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.rateDiscount = rateDiscount;
        this.size = size;
        this.color = color;
    }
}
