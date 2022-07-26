package com.ohouse.web.controller.shop.dto.orders;

import com.ohouse.web.domain.shop.orders.User;
import com.ohouse.web.domain.shop.product.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private List<OrderedProductDto> orderList;
}
