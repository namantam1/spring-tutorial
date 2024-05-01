package in.naman.springtutorials.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.naman.springtutorials.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // @Query("SELECT s FROM Student s WHERE s.email = ?1")
    public Optional<Student> findByEmail(String email);
}
