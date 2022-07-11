package com.ohouse.web.domain.items.item;

import com.ohouse.web.domain.items.codes.ItemCategoryCode;
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

    @Builder
    public Item(ItemCategoryCode categoryCode, String name) {
        this.categoryCode = categoryCode;
        this.name = name;
    }

    public void setCategoryCode(ItemCategoryCode categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setName(String name) {
        this.name = name;
    }
}
