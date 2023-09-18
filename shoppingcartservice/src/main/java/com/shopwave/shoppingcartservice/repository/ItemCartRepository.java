package com.shopwave.shoppingcartservice.repository;

import com.shopwave.shoppingcartservice.model.ItemCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCartRepository extends JpaRepository<ItemCart, Integer> {
}
