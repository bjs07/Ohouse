package com.ohouse.web.domain.shop.product;

import com.ohouse.web.domain.shop.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSeq;

    @ManyToOne
    @JoinColumn(name = "item_seq")
    private Item item;

    @Column(length = 45)
    private String productName;
    private Integer price;
    private Integer stock;
    private Integer rateDiscount;

    @Column(length = 30)
    private String size;
    @Column(length = 30)
    private String color;

    private Character optionalYn;

    @Builder
    public Product(Item item, String productName, Integer price, Integer stock, Integer rateDiscount, String size, String color, Character optionalYn) {
        this.item = item;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.rateDiscount = rateDiscount;
        this.size = size;
        this.color = color;
        this.optionalYn = optionalYn;
    }


    public void update(Item item, String productName, Integer stock, Integer price, Integer rateDiscount, String size, String color, Character optional_yn) {
        if (item != null) this.item = item;
        if (productName != null) this.productName = productName;
        if (price != null) this.price = price;
        if (stock != null) this.stock = stock;
        if (rateDiscount != null) this.rateDiscount = rateDiscount;
        if (size != null) this.size = size;
        if (color != null) this.color = color;
        if (optional_yn != null) this.optionalYn = optional_yn;
    }

}
