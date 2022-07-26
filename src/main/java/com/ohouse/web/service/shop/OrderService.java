package com.ohouse.web.service.shop;

import com.ohouse.web.controller.shop.dto.orders.OrderRequestDto;
import com.ohouse.web.controller.shop.dto.orders.OrderedProductDto;
import com.ohouse.web.domain.shop.orders.*;
import com.ohouse.web.domain.shop.product.Product;
import com.ohouse.web.domain.shop.product.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Transactional
@Service
public class OrderService {
    private OrderedProductRepository orderedProductRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Transactional
    public Long order(Long userSeq, OrderRequestDto orderRequestDto, DeliveryInformation deliveryInformation) throws Exception{
        //userRepository.find code 삽입 예정
        User user = new User();//임시 User 객체 (userRepository 에서 find했다고 가정)
        //DeliveryInformation.find code 삽입 예정
        DeliveryInformation delivery = new DeliveryInformation(); //임시 Delivery 객체


        List<Pair<Product, OrderedProductDto>> list = new ArrayList<>();
        for(var obj : orderRequestDto.getOrderList()){
            Optional<Product> product = productRepository.findById(obj.getProductSeq());

            Product findProduct = product.orElseThrow(() -> new RuntimeException("존재하지 않는 상품을 주문합니다."));
            list.add(Pair.of(findProduct, obj));
        }

        //DeliveryInformation Repo

        //create order
        Order order = Order.makeOrder(user, delivery, list);
        Long orderSeq = orderRepository.save(order).getOrderSeq();

        //create orderedProduct
        for(var obj : order.getOrderedProducts()){
            orderedProductRepository.save(obj);
        }

        return orderSeq;
    }

    @Transactional
    public void cancel(Long orderSeq) throws Exception {
        Order findOrder = orderRepository.findById(orderSeq).orElseThrow(() -> new NoSuchElementException("잘못된 주문 번호입니다."));

        findOrder.cancel();

        List<OrderedProduct> orderedProducts = findOrder.getOrderedProducts();
        for (OrderedProduct orderedProduct : orderedProducts) {
            orderedProductRepository.delete(orderedProduct);
        }
    }

    @Transactional
    public List<Order> findAllOrders(User user){
        return orderRepository.findByUser(user);
    }

    @Transactional
    public List<OrderedProduct> findAllOrderedProduct(User user, Long orderSeq){
        return orderRepository.findById(orderSeq)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 주문번호 입니다."))
                .getOrderedProducts();
    }

}
