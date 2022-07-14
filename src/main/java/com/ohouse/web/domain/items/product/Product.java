package com.ohouse.web.domain.items.product;

import com.ohouse.web.domain.items.item.Item;
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
    private Character freeDeliveryYn;
    private Character specialOrder;

    @Builder
    public Product(Item item, String productName, Integer price, Integer stock, Integer rateDiscount, Character freeDeliveryYn, Character specialOrder) {
        this.item = item;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.rateDiscount = rateDiscount;
        this.freeDeliveryYn = freeDeliveryYn;
        this.specialOrder = specialOrder;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setRateDiscount(Integer rateDiscount) {
        this.rateDiscount = rateDiscount;
    }

    public void setFreeDeliveryYn(Character freeDeliveryYn) {
        this.freeDeliveryYn = freeDeliveryYn;
    }

    public void setSpecialOrder(Character specialOrder) {
        this.specialOrder = specialOrder;
    }
}
