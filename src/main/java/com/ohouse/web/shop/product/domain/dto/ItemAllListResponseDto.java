package com.ohouse.web.shop.product.domain.dto;

import com.ohouse.web.shop.product.domain.entity.ItemCategoryCode;
import com.ohouse.web.shop.product.domain.entity.Item;
import lombok.Getter;

@Getter
public class ItemAllListResponseDto {
    private final Long itemSeq;
    private final ItemCategoryCode categoryCode;
    private final String name;
    private final String modelName;
    private final String brandName;

    public ItemAllListResponseDto(Item entity) {
        this.itemSeq = entity.getItemSeq();
        this.categoryCode = entity.getCategoryCode();
        this.name = entity.getName();
        this.modelName = entity.getModelName();
        this.brandName = entity.getBrandName();
    }
}
