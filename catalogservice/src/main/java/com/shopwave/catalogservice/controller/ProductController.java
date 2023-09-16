package com.shopwave.catalogservice.controller;

import com.shopwave.catalogservice.model.Product;
import com.shopwave.catalogservice.model.dtos.request.OperationRequest;
import com.shopwave.catalogservice.model.dtos.response.OperationResponse;
import com.shopwave.catalogservice.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OperationResponse<Product> registerNewProduct(@RequestBody Product product) {
        OperationRequest<Product> request = new OperationRequest<>();
        request.setIndividualEntityOperationRequest(product);
        return productService.registerNewProduct(request);
    }
}
