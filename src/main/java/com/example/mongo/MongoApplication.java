package com.example.mongo;

import com.example.mongo.model.Address;
import com.example.mongo.model.Gender;
import com.example.mongo.model.Student;
import com.example.mongo.repository.StudentRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


@SpringBootApplication
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}
        @Bean
        CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTempalte){
            return args ->{
                Address address = new Address("Brasil", "Rio de Janeiro", "25555-210");
                String email = "lobo.isaac92@gmail.com";
                Student student = new Student(
                        "Isaac",
                        "Lobo",
                        email,
                        Gender.MALE,address,
                        List.of("Computer Science","Maths"),
                        BigDecimal.TEN,
                        LocalDateTime.now()
                );
                //usingMongoTemplateAndWuery(email, mongoTempalte, student, repository);
                repository.findStudentByEmail(email).ifPresentOrElse(s -> {
                    System.out.println(student + " already exists");
                },()-> {
                    System.out.println("Insert student " + student);
                    repository.insert(student);} );
                
            };
        }

    private void usingMongoTemplateAndWuery(String email, MongoTemplate mongoTempalte, Student student, StudentRepository repository) throws IllegalStateException {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        
        
        List<Student> students = mongoTempalte.find(query, Student.class);
        if (students.size() > 1) {
            throw new IllegalStateException("found many students with email" + email);
        }
        if (students.isEmpty()) {
            System.out.println("Insert student " + student);
            repository.insert(student);
        } else{
            System.out.println(student + " already exists");
        }
    }

}
