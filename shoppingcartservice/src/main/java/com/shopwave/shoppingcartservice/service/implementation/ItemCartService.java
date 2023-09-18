package com.shopwave.shoppingcartservice.service.implementation;

import com.shopwave.shoppingcartservice.exception.ResourceNotFoundException;
import com.shopwave.shoppingcartservice.model.ItemCart;
import com.shopwave.shoppingcartservice.model.dtos.request.OperationRequest;
import com.shopwave.shoppingcartservice.model.dtos.response.OperationResponse;
import com.shopwave.shoppingcartservice.model.enums.ResponseStatus;
import com.shopwave.shoppingcartservice.repository.ItemCartRepository;
import com.shopwave.shoppingcartservice.service.interfaces.IItemCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemCartService implements IItemCartService {

    private final ItemCartRepository itemCartRepository;
    @Override
    public OperationResponse<ItemCart> registerNewItemCart(OperationRequest<ItemCart> request) {
        OperationResponse<ItemCart> response = new OperationResponse<>();
        try {
            ItemCart savedItemCart = itemCartRepository.save(request.getIndividualEntityOperationRequest());
            response.setIndividualOperationEntityResponse(savedItemCart);
            response.setOperationMade("Register new ItemCart");
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setIndividualOperationMessageResponse("ItemCart registered successfully");
        } catch (Exception e) {
            response.setOperationMade("Register new ItemCart");
            response.setIndividualOperationMessageResponse("Error while saving ItemCart");
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        return response;
    }

    @Override
    public OperationResponse<ItemCart> deleteExistingItemCart(Integer ItemCartId) {
        OperationResponse<ItemCart> response = new OperationResponse<>();
        try {
            ItemCart foundItemCart = getItemCartById(ItemCartId);
            itemCartRepository.deleteById(ItemCartId);
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

    private ItemCart getItemCartById(Integer ItemCartId) {
        return itemCartRepository.findById(ItemCartId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemCart not found with ID: " + ItemCartId));
    }
}
