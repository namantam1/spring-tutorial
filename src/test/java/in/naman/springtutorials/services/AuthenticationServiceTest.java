package in.naman.springtutorials.services;

import in.naman.springtutorials.enums.RoleEnum;
import in.naman.springtutorials.models.dtos.UserLoginDto;
import in.naman.springtutorials.models.dtos.UserRegisterDto;
import in.naman.springtutorials.models.entities.Role;
import in.naman.springtutorials.models.entities.User;
import in.naman.springtutorials.repositories.RoleRepository;
import in.naman.springtutorials.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void registerUser() {
        Optional<Role> role = Optional.of(new Role(1L, RoleEnum.USER, "User role"));
        when(roleRepository.findByName(RoleEnum.USER)).thenReturn(role);
        when(userRepository.save(any(User.class))).thenReturn(new User());

        User user = authenticationService.registerUser(new UserRegisterDto());
        assertNotNull(user);
    }

    @Test
    void loginUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));

        User user = authenticationService.loginUser(new UserLoginDto("email", "pass"));
        assertNotNull(user);
    }
}