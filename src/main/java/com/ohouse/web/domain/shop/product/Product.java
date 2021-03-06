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

    @Builder
    public Product(Item item, String productName, Integer price, Integer stock, Integer rateDiscount, String size, String color
    ) {
        this.item = item;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.rateDiscount = rateDiscount;
        this.size = size;
        this.color = color;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
