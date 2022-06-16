package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/student")
public class StudentController {

    private final StudentService studentService;

    //Autowired means Everything passed to the constructor should be magically instantiated and injected
    @Autowired
    public StudentController(StudentService studentService) {
        //Dependency injection let's u avoid creating objects like below
        //this.studentService = new StudentService();
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
     return studentService.getStudents();

    }
@PostMapping
    public void registerStudents(@RequestBody Student student){
        studentService.addNewStudents(student);

    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudents(@PathVariable("studentId") Long studentId){
        studentService.deleteStudents(studentId);

    }

    @PutMapping(path = "{studentId}")
    public void deleteStudents(@PathVariable("studentId") Long studentId,
                               @RequestParam (required = false) String name,
                               @RequestParam(required = false) String email
                               ){
        studentService.updateStudents(studentId, name, email);

    }

}
