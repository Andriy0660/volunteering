package com.azn.tracking_volunteer_hours.controller;



import com.azn.tracking_volunteer_hours.dto.request.AuthenticationRequest;
import com.azn.tracking_volunteer_hours.dto.request.RegisterRequest;
import com.azn.tracking_volunteer_hours.dto.response.AuthenticationResponse;
import com.azn.tracking_volunteer_hours.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody @Valid RegisterRequest request
    ){
        authService.register(request);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}