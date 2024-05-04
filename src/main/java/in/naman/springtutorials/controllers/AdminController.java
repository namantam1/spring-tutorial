package in.naman.springtutorials.controllers;

import in.naman.springtutorials.models.dtos.UserRegisterDto;
import in.naman.springtutorials.models.entities.User;
import in.naman.springtutorials.models.responses.UserResponse;
import in.naman.springtutorials.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<UserResponse> createAdminUser(@Valid @RequestBody UserRegisterDto input) {
        User user = userService.createAdminUser(input);
        UserResponse response = UserResponse.fromUser(user);
        return ResponseEntity.ok(response);
    }
}
