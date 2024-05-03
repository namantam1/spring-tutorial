package in.naman.springtutorials.models.dtos;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String email;
    private String password;
}
