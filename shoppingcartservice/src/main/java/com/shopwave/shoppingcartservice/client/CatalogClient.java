package com.shopwave.shoppingcartservice.client;

import com.shopwave.shoppingcartservice.model.dtos.response.OperationResponse;
import com.shopwave.shoppingcartservice.model.dtos.response.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog", url = "${application.config.catalog-url}")
public interface CatalogClient {
    @GetMapping("/{productId}")
    public OperationResponse<Product> getProductById(@PathVariable("productId") Integer productId);
}
