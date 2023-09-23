package com.shopwave.shoppingcartservice.service.implementation;

import com.shopwave.shoppingcartservice.client.CatalogClient;
import com.shopwave.shoppingcartservice.exception.ResourceNotFoundException;
import com.shopwave.shoppingcartservice.model.ItemCart;
import com.shopwave.shoppingcartservice.model.dtos.response.Product;
import com.shopwave.shoppingcartservice.model.dtos.request.OperationRequest;
import com.shopwave.shoppingcartservice.model.dtos.response.OperationResponse;
import com.shopwave.shoppingcartservice.model.enums.ResponseStatus;
import com.shopwave.shoppingcartservice.repository.ItemCartRepository;
import com.shopwave.shoppingcartservice.service.interfaces.IItemCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ItemCartService implements IItemCartService {

    private final ItemCartRepository itemCartRepository;
    private final CatalogClient catalogClient;

    @Override
    public OperationResponse<ItemCart> registerNewItemCart(OperationRequest<ItemCart> request) {
        OperationResponse<ItemCart> response = new OperationResponse<>();
        try {

            if(getProductFromCatalog(request.getIndividualEntityOperationRequest().getProductId()) != null) {
                ItemCart savedItemCart = itemCartRepository.save(request.getIndividualEntityOperationRequest());
                response.setIndividualOperationEntityResponse(savedItemCart);
                response.setResponseStatus(ResponseStatus.SUCCESS);
                response.setIndividualOperationMessageResponse("ItemCart registered successfully");
            } else {
                response.setResponseStatus(ResponseStatus.FAILED);
                response.setIndividualOperationMessageResponse("Product with ID " + request.getIndividualEntityOperationRequest().getProductId() + " was not found");
            }
        } catch (Exception e) {
            response.setIndividualOperationMessageResponse("Error while saving ItemCart");
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        response.setOperationMade("Register new ItemCart");
        return response;
    }

    @Override
    public OperationResponse<ItemCart> deleteExistingItemCart(Integer itemCartId) {
        OperationResponse<ItemCart> response = new OperationResponse<>();
        try {
            ItemCart foundItemCart = getItemCartById(itemCartId);
            itemCartRepository.deleteById(itemCartId);
            response.setIndividualOperationEntityResponse(foundItemCart);
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setIndividualOperationMessageResponse("ItemCart deleted successfully");
        } catch (Exception e) {
            response.setIndividualOperationMessageResponse("Error while deleting ItemCart " + e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        response.setOperationMade("Delete existing ItemCart");
        return response;
    }

    @Override
    public OperationResponse<ItemCart> updateProductQuantityByItemCartId(Integer itemCartId, int quantity) {
        OperationResponse<ItemCart> response = new OperationResponse<>();
        try {
            ItemCart foundItemCart = getItemCartById(itemCartId);
            foundItemCart.setQuantity(quantity);
            Double totalToPayWithQuantity = calculateTotalToPayBySelectedProduct(foundItemCart.getProductId(), quantity);
            foundItemCart.setTotalPrice(totalToPayWithQuantity);

            this.itemCartRepository.save(foundItemCart);

            response.setIndividualOperationMessageResponse("Item cart quantity updated succesfully");
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setIndividualOperationEntityResponse(foundItemCart);
        } catch (Exception e) {
            e.printStackTrace();
            response.setIndividualOperationMessageResponse("Error while updating item cart" + e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        response.setOperationMade("Update item cart quantity");
        return response;
    }

    private ItemCart getItemCartById(Integer ItemCartId) {
        return itemCartRepository.findById(ItemCartId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemCart not found with ID: " + ItemCartId));
    }

    @Override
    public OperationResponse<Product> getProductFromCatalog(Integer productId) {
        return catalogClient.getProductById(productId);
    }

    private Double calculateTotalToPayBySelectedProduct(Integer productId, int quantity) {
        Double totalToPayBySelectedProduct = 0.0;
        OperationResponse<Product> product = getProductFromCatalog(productId);
        if(product != null) {
            totalToPayBySelectedProduct = product.getIndividualOperationEntityResponse().getPrice() * quantity;
        }
        return totalToPayBySelectedProduct;
    }
}
