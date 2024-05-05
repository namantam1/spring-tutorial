package in.naman.springtutorials.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDto {
    @Email(message = "Enter a valid email address.")
    @NotEmpty(message = "Email is required.")
    private String email;

    @Length(min = 8, message = "Password must be at least 8 character long.")
    private String password;
}
