package com.shopwave.catalogservice.service.implementation;

import com.shopwave.catalogservice.exception.ResourceNotFoundException;
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
        OperationResponse<Product> response = new OperationResponse<>();
        try {
            Product foundProduct = getProductById(productId);

            Product requestProduct = request.getIndividualEntityOperationRequest();

            foundProduct.setProductName(requestProduct.getProductName());
            foundProduct.setDescription(requestProduct.getDescription());
            foundProduct.setPrice(requestProduct.getPrice());
            foundProduct.setStock(requestProduct.getStock());
            foundProduct.setCategory(requestProduct.getCategory());

            productRepository.save(foundProduct);
            response.setIndividualOperationEntityResponse(foundProduct);
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setIndividualOperationMessageResponse("Product updated successfully");

        }  catch (Exception e) {
            response.setIndividualOperationMessageResponse("Error while updating product " + e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        response.setOperationMade("Update existing product");
        return response;
    }

    @Override
    public OperationResponse<Product> deleteExistingProduct(Integer productId) {
        OperationResponse<Product> response = new OperationResponse<>();
        try {
            Product foundProduct = getProductById(productId);
            productRepository.deleteById(productId);
            response.setIndividualOperationEntityResponse(foundProduct);
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setIndividualOperationMessageResponse("Product deleted successfully");
        } catch (Exception e) {
            response.setIndividualOperationMessageResponse("Error while deleting product " + e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        response.setOperationMade("Delete existing product");
        return response;
    }

    @Override
    public OperationResponse<Product> getProductInformationByProductId(Integer productId) {
        OperationResponse<Product> response = new OperationResponse<>();
        try {
            Product foundProduct = getProductById(productId);
            response.setIndividualOperationEntityResponse(foundProduct);
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setIndividualOperationMessageResponse("Product found successfully");
        } catch (Exception e) {
            response.setIndividualOperationMessageResponse("Error while found product " + e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        response.setOperationMade("Found existing product information");
        return response;
    }

    @Override
    public OperationResponse<Product> getProductInformationByCategory(ProductCategory category) {
        OperationResponse<Product> response = new OperationResponse<>();
        try {
            Product foundProduct = productRepository.findByCategory(category)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with Category: " + category) );
            response.setIndividualOperationEntityResponse(foundProduct);
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setIndividualOperationMessageResponse("Product found successfully");
        } catch (IllegalArgumentException e) {
            response.setIndividualOperationMessageResponse("Error, Invalid argument: " + e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        catch (Exception e) {
            response.setIndividualOperationMessageResponse("Error while found product " + e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        response.setOperationMade("Found existing product information");
        return response;
    }

    private Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
    }
}
