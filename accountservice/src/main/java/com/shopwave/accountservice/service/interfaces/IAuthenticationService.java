package com.shopwave.accountservice.service.interfaces;

import com.shopwave.accountservice.model.dtos.request.AuthenticationRequest;
import com.shopwave.accountservice.model.dtos.request.RegisterRequest;
import com.shopwave.accountservice.model.dtos.response.AuthenticationResponse;
import com.shopwave.accountservice.model.entities.User;

public interface IAuthenticationService {
    public User register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
