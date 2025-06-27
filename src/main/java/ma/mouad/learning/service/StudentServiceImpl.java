package ma.mouad.learning.service;

import ma.mouad.learning.model.Student;
import ma.mouad.learning.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    
    public List<Student> list() {
        return studentRepository.findAll();
    }

    
    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    
    public Student newStudent(Student student) {
        return studentRepository.save(student);
    }

    
    @Transactional
    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    
    public Boolean studentHasExist(Long id) {
        return studentRepository.existsById(id);
    }

    
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    
    public List<Student> findStudentsByAge(int age) {
        return studentRepository.findStudentsByAge(age);
    }

    
    public Optional<Student> findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }
}
