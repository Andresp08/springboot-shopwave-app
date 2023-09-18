package com.shopwave.shoppingcartservice.model.dtos.request;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class OperationRequest<Entity> {
    private Entity individualEntityOperationRequest;
}
