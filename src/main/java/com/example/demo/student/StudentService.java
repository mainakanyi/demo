package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Component is the same as at service for business logic that say it is a bean and needs to be instantiated more for readability
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
//        List ls = new ArrayList<>();
//        ls.add(new Student(1L, "James", 23, LocalDate.of(1989, 9, 26), "mainakanyi9@gmail,com"));
//
//        return ls;

    }

    public void addNewStudents(Student student) {
//System.out.println(student);
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
          throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudents(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("student wih id "+studentId+" does not exist");
        }
        studentRepository.deleteById(studentId);


    }

    //ensures atomicity and consistency
@Transactional
    public void updateStudents(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student wih id "+studentId+" does not exist"));
        //check name
        if(name !=null && name.length() >0 && !Objects.equals(student.getName(), name)){
       student.setName(name);
        }
        //check email
    if(email !=null && email.length() >0 && !Objects.equals(student.getName(), email)){
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        student.setEmail(email);
    }
    }
}
