package com.ohouse.web.domain.shop.item;

import com.ohouse.web.domain.shop.codes.ItemCategoryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemSeq;

    @ManyToOne
    @JoinColumn(name = "category_code")
    private ItemCategoryCode categoryCode;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 50, unique = true)
    private String modelName;

    @Column(length = 45)
    private String brandName;


    @Builder
    public Item(ItemCategoryCode categoryCode, String name, String modelName, String brandName) {
        this.categoryCode = categoryCode;
        this.name = name;
        this.modelName = modelName;
        this.brandName = brandName;
    }




    public void setCategoryCode(ItemCategoryCode categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setName(String name) {
        this.name = name;
    }
}
