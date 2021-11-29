
package com.example.mongo.controller;

import com.example.mongo.model.Student;
import com.example.mongo.service.StudentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    
    @GetMapping
    public List<Student> fetchAllStudents(){
        System.out.println("get");
        return studentService.getAllStudents();
    }
    @PostMapping
    public void postStudent(@RequestBody Student student){
        
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") String studentId){
        studentService.deleteStudent(studentId);
    }
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") String studentId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false)List<String>favouriteSubjects){
        
        studentService.updateStudent(studentId,firstName,lastName,email,gender,favouriteSubjects);
    }
    
}
