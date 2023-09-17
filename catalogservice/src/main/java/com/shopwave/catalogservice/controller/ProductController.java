package com.shopwave.catalogservice.controller;

import com.shopwave.catalogservice.model.Product;
import com.shopwave.catalogservice.model.dtos.request.OperationRequest;
import com.shopwave.catalogservice.model.dtos.response.OperationResponse;
import com.shopwave.catalogservice.model.enums.ProductCategory;
import com.shopwave.catalogservice.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(
            value = "/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OperationResponse<Product> updateExistingProduct(
            @PathVariable("productId") Integer productId,
            @RequestBody Product product
    ) {
        OperationRequest<Product> request = new OperationRequest<>();
        request.setIndividualEntityOperationRequest(product);
        return productService.updateExistingProduct(productId,request);
    }

    @DeleteMapping(
            value = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OperationResponse<Product> deleteExistingProduct(@PathVariable("productId") Integer productId) {
        return productService.deleteExistingProduct(productId);
    }

    @GetMapping(
            value = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OperationResponse<Product> getProductInformationByCategory(
            @PathVariable("productId") Integer productId
    ) {
        return productService.getProductInformationByProductId(productId);
    }

    @GetMapping(
            value = "/category/{category}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OperationResponse<Product> getProductInformationByCategory(
            @PathVariable("category") ProductCategory category
    ) {
        return productService.getProductInformationByCategory(category);
    }
}
