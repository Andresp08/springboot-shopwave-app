package com.shopwave.shoppingcartservice.service.interfaces;

import com.shopwave.shoppingcartservice.model.ItemCart;
import com.shopwave.shoppingcartservice.model.dtos.request.OperationRequest;
import com.shopwave.shoppingcartservice.model.dtos.response.OperationResponse;
import com.shopwave.shoppingcartservice.model.dtos.response.Product;

public interface IItemCartService {
    public OperationResponse<ItemCart> registerNewItemCart(OperationRequest<ItemCart> request);
    public OperationResponse<ItemCart> deleteExistingItemCart(Integer itemCartId);
    public OperationResponse<ItemCart> updateProductQuantityByItemCartId(Integer itemCartId, int quantity);
    public OperationResponse<Product> getProductFromCatalog(Integer productId);
}
