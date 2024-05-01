package in.naman.springtutorials.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.naman.springtutorials.models.Student;
import in.naman.springtutorials.services.StudentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        log.info("Student {}", student);
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public Long deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/email/{email}")
    public Student getStudentByEmail(@PathVariable("email") String email) {
        return studentService.getStudentByEmail(email);
    }
}
