package com.venu.order_management_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Integer productQuantity;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders order;

}
