package in.naman.springtutorials.services;

import in.naman.springtutorials.models.dtos.StudentDto;
import in.naman.springtutorials.models.entities.Student;
import in.naman.springtutorials.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(StudentDto input) {
        Student student = input.toStudent();
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Long deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return id;
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email).orElse(null);
    }

}
