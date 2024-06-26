package in.naman.springtutorials.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.naman.springtutorials.models.Student;
import in.naman.springtutorials.repositories.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        Optional<Student> check = studentRepository.findByEmail(student.getEmail());
        if (check.isPresent()) {
            throw new IllegalStateException("student already exists with this email.");
        }
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
