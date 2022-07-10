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
    @Column(nullable = false, updatable = false, length = 7)
    String itemCode;

    @Column(nullable = false, updatable = false, length = 10)
    String category;


    public ItemCategoryCode(String itemCode, String category){
        this.itemCode = itemCode;
        this.category = category;
    }
}
