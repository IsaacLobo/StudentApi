
package com.example.mongo.service;

import com.example.mongo.model.Gender;
import com.example.mongo.model.Student;
import com.example.mongo.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        student.setCreated(LocalDateTime.now());
       studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(String studentId) {
        boolean existisStudent = studentRepository.existsById(studentId);
        if(!existisStudent){
            throw new IllegalStateException("Student doesn't exist "+ studentId);
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(String studentId, String firstName, 
            String lastName, String email, String gender, List<String> favouriteSubjects) {
        boolean existisStudent = studentRepository.existsById(studentId);
        if(!existisStudent){
            throw new IllegalStateException("Student doesn't exist "+ studentId);
        }
        Student student = studentRepository.findStudentById(studentId);
        if (firstName != null && firstName.length() > 3 
                && !Objects.equals(student.getFirstName(), firstName)){
            student.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 3 
                && !Objects.equals(student.getLastName(), lastName)){
            student.setLastName(lastName);
        }
        if (email != null && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()){
                
                throw new IllegalStateException("Email already exists");
            }
            student.setLastName(email);
        }
        if (gender != null && !Objects.equals(student.getGender(), gender)){
            switch (gender.toUpperCase()){
                case "MALE":
                    student.setGender(Gender.MALE);
                break;
                case "FEMALE":
                    student.setGender(Gender.FEMALE);
                break;
                case "OTHERS":
                    student.setGender(Gender.OTHERS);
                break;
            }
        }
       /* if(!favouriteSubjects.isEmpty()){
            List<String> favourite = student.getFavouriteSubjects();
            favourite.addAll(favourite);
        }*/
        studentRepository.save(student);
        
    }
    
}
