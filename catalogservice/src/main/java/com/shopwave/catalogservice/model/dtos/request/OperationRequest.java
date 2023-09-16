package com.shopwave.catalogservice.model.dtos.request;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class OperationRequest<Entity> {
    private Entity individualEntityOperationRequest;
}
