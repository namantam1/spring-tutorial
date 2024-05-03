package in.naman.springtutorials.controllers;

import in.naman.springtutorials.models.User;
import in.naman.springtutorials.models.dtos.UserLoginDto;
import in.naman.springtutorials.models.dtos.UserRegisterDto;
import in.naman.springtutorials.models.responses.LoginResponse;
import in.naman.springtutorials.models.responses.RegisterResponse;
import in.naman.springtutorials.services.AuthenticationService;
import in.naman.springtutorials.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody UserRegisterDto input) {
        User user = authenticationService.registerUser(input);
        RegisterResponse response = RegisterResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserLoginDto input) {
        User user = authenticationService.loginUser(input);

        String jwtToken = jwtService.generateToken(user);

        LoginResponse response = LoginResponse.builder()
                .token(jwtToken)
                .build();
        return ResponseEntity.ok(response);
    }
}
