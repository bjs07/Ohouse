package com.ohouse.web.controller.shop.dto.product;

import com.ohouse.web.domain.shop.codes.ItemCategoryCode;
import com.ohouse.web.domain.shop.item.Item;
import com.ohouse.web.domain.shop.product.Product;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
public class ProductSaveRequestDto {

    private Item item;
    private String productName;
    private Integer price;
    private Integer stock;
    private Integer rateDiscount;
    private String size;
    private String color;

    @Builder
    public ProductSaveRequestDto(Item item, String productName, Integer price, Integer stock, Integer rateDiscount, String size, String color) {
        this.item = item;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.rateDiscount = rateDiscount;
        this.size = size;
        this.color = color;
    }

    public Product toEntity() {
        return Product.builder()
                .item(item)
                .productName(productName)
                .price(price)
                .stock(stock)
                .rateDiscount(rateDiscount)
                .size(size)
                .color(color)
                .build();
    }
}
