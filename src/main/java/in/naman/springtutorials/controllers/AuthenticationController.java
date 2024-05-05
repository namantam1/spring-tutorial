package in.naman.springtutorials.controllers;

import in.naman.springtutorials.models.dtos.UserLoginDto;
import in.naman.springtutorials.models.dtos.UserRegisterDto;
import in.naman.springtutorials.models.entities.User;
import in.naman.springtutorials.models.responses.LoginResponse;
import in.naman.springtutorials.models.responses.RegisterResponse;
import in.naman.springtutorials.services.AuthenticationService;
import in.naman.springtutorials.services.JwtService;
import in.naman.springtutorials.services.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;


@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final MailService mailService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService, MailService mailService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.mailService = mailService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody UserRegisterDto input)
            throws MessagingException, UnsupportedEncodingException {
        User user = authenticationService.registerUser(input);
//        mailService.send(user);
        RegisterResponse response = RegisterResponse.fromUser(user);
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
