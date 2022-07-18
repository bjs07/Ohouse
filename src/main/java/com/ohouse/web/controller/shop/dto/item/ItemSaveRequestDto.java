package com.ohouse.web.controller.shop.dto.item;

import com.ohouse.web.domain.shop.codes.ItemCategoryCode;
import com.ohouse.web.domain.shop.item.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemSaveRequestDto {
    private ItemCategoryCode categoryCode;
    private String name;
    private String modelName;
    private String brandName;

    @Builder
    public ItemSaveRequestDto(ItemCategoryCode categoryCode, String name, String modelName, String brandName) {
        this.categoryCode = categoryCode;
        this.name = name;
        this.modelName = modelName;
        this.brandName = brandName;
    }


    public Item toEntity() {
        return Item.builder()
                .categoryCode(categoryCode)
                .name(name)
                .modelName(modelName)
                .brandName(brandName)
                .build();
    }
}
