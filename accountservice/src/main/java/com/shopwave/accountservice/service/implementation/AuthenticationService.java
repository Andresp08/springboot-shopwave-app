package com.shopwave.accountservice.service.implementation;

import com.shopwave.accountservice.model.dtos.request.AuthenticationRequest;
import com.shopwave.accountservice.model.dtos.request.RegisterRequest;
import com.shopwave.accountservice.model.dtos.response.AuthenticationResponse;
import com.shopwave.accountservice.model.entities.Role;
import com.shopwave.accountservice.model.entities.User;
import com.shopwave.accountservice.repository.user.UserRepository;
import com.shopwave.accountservice.service.interfaces.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken =
        return null;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
