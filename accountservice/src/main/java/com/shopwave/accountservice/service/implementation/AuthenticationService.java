package com.shopwave.accountservice.service.implementation;

import com.shopwave.accountservice.model.dtos.request.AuthenticationRequest;
import com.shopwave.accountservice.model.dtos.request.RegisterRequest;
import com.shopwave.accountservice.model.dtos.response.AuthenticationResponse;
import com.shopwave.accountservice.model.entities.Role;
import com.shopwave.accountservice.model.entities.User;
import com.shopwave.accountservice.repository.UserRepository;
import com.shopwave.accountservice.security.JwtService;
import com.shopwave.accountservice.service.interfaces.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public User register(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
