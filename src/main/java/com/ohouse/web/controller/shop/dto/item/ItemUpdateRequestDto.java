package com.ohouse.web.controller.shop.dto.item;

import com.ohouse.web.domain.shop.codes.ItemCategoryCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemUpdateRequestDto {
    private ItemCategoryCode categoryCode;
    private String name;
    private String modelName;
    private String brandName;

    @Builder
    public ItemUpdateRequestDto(ItemCategoryCode categoryCode, String name, String modelName, String brandName) {
        this.categoryCode = categoryCode;
        this.name = name;
        this.modelName = modelName;
        this.brandName = brandName;
    }
}
