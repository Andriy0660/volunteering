package com.azn.tracking_volunteer_hours.service;



import com.azn.tracking_volunteer_hours.dto.request.AuthenticationRequest;
import com.azn.tracking_volunteer_hours.dto.request.RegisterRequest;
import com.azn.tracking_volunteer_hours.dto.response.AuthenticationResponse;
import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.exception.BadRequestException;
import com.azn.tracking_volunteer_hours.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDetailServiceImpl userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    public void register(RegisterRequest request)
    {
        String email = request.getEmail();

        if(userService.existsUserByEmail(email)){
            throw new BadRequestException("The email is already used");
        }


        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();
        userService.save(user);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            if(!checkPassword(request.getPassword(),
                    userService.findUserByEmail(request.getEmail()).getPassword())){
                throw new UnauthorizedException("Email or password is wrong");
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            throw new UnauthorizedException("Email or password is wrong");
        }

        var user = userDetailService.loadUserByUsername(request.getEmail());

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public boolean checkPassword(String enteredPassword, String hashedPassword) {
        return passwordEncoder.matches(enteredPassword, hashedPassword);
    }
}