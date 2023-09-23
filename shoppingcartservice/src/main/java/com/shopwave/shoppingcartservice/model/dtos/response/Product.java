package com.shopwave.shoppingcartservice.model.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private String productName;
    private Double price;
}
