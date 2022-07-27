package com.ohouse.web.shop.order.domain.entity;

import com.ohouse.web.shop.order.domain.dto.OrderedProductDto;
import com.ohouse.web.shop.order.domain.OrderStatus;
import com.ohouse.web.shop.product.domain.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSeq;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne
    @JoinColumn(name = "delivery_information_seq")
    private DeliveryInformation deliveryInformation;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "product")
    List<OrderedProduct> orderedProducts = new ArrayList<>();

    public static Order makeOrder(User user, DeliveryInformation delivery, List<Pair<Product, OrderedProductDto>> orderedProducts) throws Exception {
        Order order = new Order();

        order.user = user;
        order.deliveryInformation = delivery;
        order.status = OrderStatus.CHARGED;

        for(var obj : orderedProducts){
            int adjustedPrice = obj.getSecond().getAdjustedPrice();
            int amount = obj.getSecond().getAmount();

            OrderedProduct orderedProduct = obj.getFirst().makeOrderedProduct(order, adjustedPrice, amount);
            order.orderedProducts.add(orderedProduct);
        }

        return order;
    }

    public void cancel() throws Exception {
        if(this.status.equals(OrderStatus.DELIVERY)) throw new RuntimeException("Fail to cancel order, It's middle on delivery");

        this.status = OrderStatus.CANCEL;
        for(OrderedProduct orderedProduct : orderedProducts) orderedProduct.cancel();
    }

    public Integer getTotalPrice(){
        Integer total = 0;
        for(var orderedProduct: orderedProducts){
            total += orderedProduct.getPrice();
        }

        return total;
    }
}
