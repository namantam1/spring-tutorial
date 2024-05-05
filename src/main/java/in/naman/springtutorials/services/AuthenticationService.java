package in.naman.springtutorials.services;

import in.naman.springtutorials.enums.RoleEnum;
import in.naman.springtutorials.models.dtos.UserLoginDto;
import in.naman.springtutorials.models.dtos.UserRegisterDto;
import in.naman.springtutorials.models.entities.Role;
import in.naman.springtutorials.models.entities.User;
import in.naman.springtutorials.repositories.RoleRepository;
import in.naman.springtutorials.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User registerUser(UserRegisterDto input) {
        Optional<Role> role = roleRepository.findByName(RoleEnum.USER);

        if (role.isEmpty())
            return null;

        User user = User.builder()
                .name(input.getName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(role.get())
                .build();
        return userRepository.save(user);
    }

    public User loginUser(UserLoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
