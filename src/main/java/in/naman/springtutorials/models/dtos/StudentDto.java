package in.naman.springtutorials.models.dtos;

import in.naman.springtutorials.models.entities.Student;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    @NotEmpty(message = "Name is required.")
    private String name;

    @NotEmpty(message = "Email is required.")
    private String email;

    @NotNull(message = "DOB is required.")
    @Past(message = "DOB is invalid.")
    private LocalDate dob;

    public Student toStudent() {
        return Student.builder()
                .name(name)
                .email(email)
                .dob(dob)
                .build();
    }
}
