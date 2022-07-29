package com.ohouse.web.shop.order.domain.access;

import com.ohouse.web.shop.order.domain.entity.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {
}
