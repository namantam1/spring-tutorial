package in.naman.springtutorials.bootstrap;

import in.naman.springtutorials.enums.RoleEnum;
import in.naman.springtutorials.models.entities.Role;
import in.naman.springtutorials.models.entities.User;
import in.naman.springtutorials.repositories.RoleRepository;
import in.naman.springtutorials.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class DataSeeder {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataSeeder(
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @EventListener
    @Order(0)
    private void loadRoles(ContextRefreshedEvent ignored) {
        RoleEnum[] roleNames = {
                RoleEnum.SUPER_ADMIN,
                RoleEnum.ADMIN,
                RoleEnum.USER,
        };
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.ADMIN, "Admin user role",
                RoleEnum.SUPER_ADMIN, "Super admin user role"
        );

        Arrays.stream(roleNames).forEach(roleName -> {
            Optional<Role> role = roleRepository.findByName(roleName);
            role.ifPresentOrElse(
                    r -> log.info("Role = {}", r),
                    () -> {
                        Role roleToCreate = Role.builder()
                                .name(roleName)
                                .description(roleDescriptionMap.get(roleName))
                                .build();
                        roleRepository.save(roleToCreate);
                    });
        });

        log.info("loadRoles::Roles created");
    }

    @EventListener
    @Order(1)
    public void createSuperAdmin(ContextRefreshedEvent ignored) {
        User user = User.builder()
                .email("super.admin@gmail.com")
                .password(passwordEncoder.encode("password"))
                .build();

        Optional<Role> role = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> userPresent = userRepository.findByEmail(user.getEmail());

        log.info("createSuperAdmin::Role: {}", role);

        if (role.isEmpty() || userPresent.isPresent())
            return;

        user.setRole(role.get());
        userRepository.save(user);
        log.info("createSuperAdmin::super admin created!!");
    }
}
