package com.ohouse.web.domain.items.codes;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class ItemCategoryCode {

    @Id
    @Column(name = "category_code", nullable = false, updatable = false)
    private Long categoryCode;

    @Column(nullable = false, updatable = false, length = 10)
    private String category;

    public ItemCategoryCode(Long categoryCode, String category){
        this.categoryCode = categoryCode;
        this.category = category;
    }

}
