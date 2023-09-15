package com.shopwave.accountservice.service.interfaces;

import com.shopwave.accountservice.model.dtos.request.AuthenticationRequest;
import com.shopwave.accountservice.model.dtos.request.RegisterRequest;
import com.shopwave.accountservice.model.dtos.response.AuthenticationResponse;

public interface IAuthenticationService {
    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
