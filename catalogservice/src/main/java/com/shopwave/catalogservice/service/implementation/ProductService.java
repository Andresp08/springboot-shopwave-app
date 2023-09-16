package com.shopwave.catalogservice.service.implementation;

import com.shopwave.catalogservice.model.Product;
import com.shopwave.catalogservice.model.dtos.request.OperationRequest;
import com.shopwave.catalogservice.model.dtos.response.OperationResponse;
import com.shopwave.catalogservice.model.enums.ProductCategory;
import com.shopwave.catalogservice.model.enums.ResponseStatus;
import com.shopwave.catalogservice.repository.ProductRepository;
import com.shopwave.catalogservice.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public OperationResponse<Product> registerNewProduct(OperationRequest<Product> request) {
        OperationResponse<Product> response = new OperationResponse<>();
        try {
            Product savedProduct = productRepository.save(request.getIndividualEntityOperationRequest());
            response.setIndividualOperationEntityResponse(savedProduct);
            response.setOperationMade("Register new product");
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setIndividualOperationMessageResponse("Product registered successfully");
        } catch (Exception e) {
            response.setOperationMade("Register new product");
            response.setIndividualOperationMessageResponse("Error while saving product");
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        return response;
    }

    @Override
    public OperationResponse<Product> updateExistingProduct(Integer productId, OperationRequest<Product> request) {
        return null;
    }

    @Override
    public OperationResponse<Product> deleteExistingProduct(Integer productId) {
        return null;
    }

    @Override
    public OperationResponse<Product> getProductInformationByProductId(Integer productId) {
        return null;
    }

    @Override
    public OperationResponse<Product> getProductInformationByCategory(ProductCategory category) {
        return null;
    }
}
