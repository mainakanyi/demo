package com.example.demo.student;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class StudentConfig {


    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
return args -> {
    Student hahmes = new Student( "Hahmes", LocalDate.of(1988, 9, 26), "maina@gmail.com");
    Student joseph = new Student( "Joseph",  LocalDate.of(1986, 9, 26), "joseph@gmail.com");
    studentRepository.saveAll(Arrays.asList(hahmes, joseph));
};

    }
}
