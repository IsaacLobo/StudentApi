
package com.example.mongo.controller;

import com.example.mongo.model.Student;
import com.example.mongo.service.StudentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public List<Student> fetchAllStudents(){
        return studentService.getAllStudents();
    }
    @PostMapping
    public void postStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    
}
