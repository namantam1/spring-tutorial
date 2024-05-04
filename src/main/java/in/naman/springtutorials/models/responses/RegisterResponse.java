package in.naman.springtutorials.models.responses;

import in.naman.springtutorials.models.entities.Role;
import in.naman.springtutorials.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private Long id;
    private String email;
    private Role role;

    public static RegisterResponse fromUser(User user) {
        return builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
