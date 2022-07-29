package com.ohouse.web.shop.order.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Temperary Mockup DeliveryInformation class
@Getter
@NoArgsConstructor
@Entity
public class DeliveryInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryInfomationSeq;
}
