package com.shopwave.catalogservice.service.interfaces;

import com.shopwave.catalogservice.model.Product;
import com.shopwave.catalogservice.model.dtos.request.OperationRequest;
import com.shopwave.catalogservice.model.dtos.response.OperationResponse;
import com.shopwave.catalogservice.model.enums.ProductCategory;

public interface IProductService {
    public OperationResponse<Product> registerNewProduct(OperationRequest<Product> request);
    public OperationResponse<Product> updateExistingProduct(Integer productId, OperationRequest<Product> request);
    public OperationResponse<Product> deleteExistingProduct(Integer productId);
    public OperationResponse<Product> getProductInformationByProductId(Integer productId);
    public OperationResponse<Product> getProductInformationByCategory(ProductCategory category);
}
