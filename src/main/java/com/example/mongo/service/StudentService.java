
package com.example.mongo.service;

import com.example.mongo.model.Student;
import com.example.mongo.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent())
           {
               throw new IllegalStateException("Email already exists");
           }
       studentRepository.save(student);
        System.out.println(student);
    }
    
}
