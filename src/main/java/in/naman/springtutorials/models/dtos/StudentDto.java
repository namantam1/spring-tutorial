package in.naman.springtutorials.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private String name;
    private String email;
    private LocalDate dob;
}
