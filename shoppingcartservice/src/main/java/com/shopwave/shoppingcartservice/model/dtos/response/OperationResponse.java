package com.shopwave.shoppingcartservice.model.dtos.response;

import com.shopwave.shoppingcartservice.model.enums.ResponseStatus;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class OperationResponse<Entity> {
    private ResponseStatus responseStatus;
    private String operationMade;
    private String individualOperationMessageResponse;
    private Entity  individualOperationEntityResponse;
}
