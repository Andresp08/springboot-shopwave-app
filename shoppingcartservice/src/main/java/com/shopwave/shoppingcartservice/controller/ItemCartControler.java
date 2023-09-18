package com.shopwave.shoppingcartservice.controller;

import com.shopwave.shoppingcartservice.model.ItemCart;
import com.shopwave.shoppingcartservice.model.dtos.request.OperationRequest;
import com.shopwave.shoppingcartservice.model.dtos.response.OperationResponse;
import com.shopwave.shoppingcartservice.service.interfaces.IItemCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item-cart")
@RequiredArgsConstructor
public class ItemCartControler {
    
    private final IItemCartService itemCartService;

    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OperationResponse<ItemCart> registerNewItemCart(@RequestBody ItemCart ItemCart) {
        OperationRequest<ItemCart> request = new OperationRequest<>();
        request.setIndividualEntityOperationRequest(ItemCart);
        return itemCartService.registerNewItemCart(request);
    }

    @DeleteMapping(
            value = "/{itemCartId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OperationResponse<ItemCart> deleteExistingItemCart(@PathVariable("itemCartId") Integer itemCartId) {
        return itemCartService.deleteExistingItemCart(itemCartId);
    }
}
