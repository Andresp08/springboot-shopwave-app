package com.shopwave.shoppingcartservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "item_cart")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class ItemCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer productId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double totalPrice;
}
