package com.shopwave.shoppingcartservice.service.interfaces;

import com.shopwave.shoppingcartservice.model.ItemCart;
import com.shopwave.shoppingcartservice.model.dtos.request.OperationRequest;
import com.shopwave.shoppingcartservice.model.dtos.response.OperationResponse;

public interface IItemCartService {
    public OperationResponse<ItemCart> registerNewItemCart(OperationRequest<ItemCart> request);
    public OperationResponse<ItemCart> deleteExistingItemCart(Integer ItemCartId);
}
