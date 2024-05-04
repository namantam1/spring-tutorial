package in.naman.springtutorials.services;

import in.naman.springtutorials.enums.RoleEnum;
import in.naman.springtutorials.models.dtos.UserRegisterDto;
import in.naman.springtutorials.models.entities.Role;
import in.naman.springtutorials.models.entities.User;
import in.naman.springtutorials.repositories.RoleRepository;
import in.naman.springtutorials.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createAdminUser(UserRegisterDto input) {
        Optional<Role> role = roleRepository.findByName(RoleEnum.ADMIN);

        if (role.isEmpty())
            return null;

        User user = User.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(role.get())
                .build();
        return userRepository.save(user);
    }
}
