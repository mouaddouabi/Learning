package ma.mouad.learning.repository;


import ma.mouad.learning.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByAge(int age);

    Optional<Student> findStudentByEmail(String email);
}
