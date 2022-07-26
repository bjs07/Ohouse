package com.ohouse.web.domain.shop.orders;

import com.ohouse.web.domain.shop.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOrderSeq;

    @ManyToOne
    @JoinColumn(name = "product_seq")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_seq")
    private Order order;

    private Integer price;
    private Integer amount;

    public OrderedProduct(Product product, Order order, Integer price, Integer amount) {
        this.product = product;
        this.order = order;
        this.price = price;
        this.amount = amount;
    }

    public void cancel() throws Exception{
        product.returnProduct(amount);
        amount = 0;
    }
}
