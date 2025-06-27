package ma.mouad.learning.controller;


import ma.mouad.learning.model.Student;
import ma.mouad.learning.service.StudentServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student> getStudents() {
        return studentService.list();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/age={age}")
    public List<Student> getStudentsByAge(
            @PathVariable("age") int age) {
        return studentService.findStudentsByAge(age);
    }

    @PostMapping
    public Student newStudent(@RequestBody Student form) {
        Optional<Student> studentEmail = studentService.findStudentByEmail(form.getEmail());
//        if (studentEmail.isPresent()) {
//            throw new IllegalStateException("Email is already taken");
//        }

        return studentService.newStudent(form);
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody @Validated Student form) {

        return studentService.updateStudent(id, form);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        boolean hasExist = studentService.studentHasExist(id);
        if (!hasExist) {
            throw new IllegalStateException("Student ID: " + id + " does not exist!");
        }

        studentService.deleteStudent(id);
    }


}
