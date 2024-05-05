package in.naman.springtutorials.models.dtos;

import in.naman.springtutorials.models.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegisterDto {
    @Email(message = "Enter a valid email address.")
    @NotEmpty(message = "Email is required.")
    private String email;

    @NotEmpty(message = "Name is required")
    private String name;

    @Length(min = 8, message = "Password must be at least 8 character long.")
    private String password;
}
