package com.example.exam.controller;

import com.example.exam.model.Message;
import com.example.exam.model.Student;
import com.example.exam.model.Supervisor;
import com.example.exam.service.StudentService;
import com.example.exam.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController //Annotating that this is going to be a RestController
public class StudentController
{

    @Autowired
    StudentService studentService;

    @Autowired
    SupervisorService supervisorService;


    @RequestMapping("/") //Request mapping for the index page
    //Remember RequestMapping is by default GET
    public ModelAndView index(Model model)
    {
        //Used to load all of the students
        model.addAttribute("students", studentService.findAll());

        //making a new ModelAndView object with the view name "index"
        ModelAndView mv = new ModelAndView("index");

        //Used to load all of the supervisors
        mv.addObject("supervisors",supervisorService.findAll());

        //returning our ModelAndView Object (which holds "index" and therefore we can open our index.html)
        return mv;
    }

    //As mentioned above RequestMapping is by default GET, therefore it is changed to POST
    @RequestMapping(value = "/api/addStudent", method = RequestMethod.POST)

    //the <?> makes me able to return pretty much anything (good for debugging)
    public ResponseEntity<?> addStudent(@RequestBody Message message)
    {
        //Creating a new student object
        Student student = new Student();

        //setting our students name and email equal to the message getters
        student.setStudentName(message.getStudentName());
        student.setStudentEmail(message.getStudentEmail());

        //creating a Supervisor object based on the id
        Supervisor supervisor = supervisorService.findById(message.getSupervisorId()).get();
        supervisor.addStudent(student);

        //using the save methods
        student = studentService.save(student);
        supervisorService.save(supervisor);

        //creating a new message and setting it
        Message message1 = new Message();
        message1.setSupervisor(supervisor);
        message1.setStudentName(student.getStudentName());
        message1.setStudentEmail(student.getStudentEmail());
        message1.setStudentId(student.getStudentId());

        return ResponseEntity.ok(message1);

    }

    @RequestMapping(value = "/api/updateStudent", method = RequestMethod.POST)
    public ResponseEntity<?> updateStudent(@RequestBody Message message)
    {

        Student student = studentService.findById(message.getStudentId()).get();
        student.setStudentName(message.getStudentName());
        student.setStudentEmail(message.getStudentEmail());

        Supervisor supervisor = supervisorService.findById(message.getSupervisorId()).get();
        supervisor.addStudent(student);

        student = studentService.save(student);
        supervisorService.save(supervisor);

        Message message1 = new Message();
        message1.setSupervisor(supervisor);
        message1.setStudentName(student.getStudentName());
        message1.setStudentEmail(student.getStudentEmail());
        message1.setStudentId(student.getStudentId());
        return ResponseEntity.ok(message1);

    }

    @RequestMapping(value = "/api/deleteStudent", method = RequestMethod.POST)
    public ResponseEntity<?> deleteStudent(@RequestBody Message message)
    {

        studentService.deleteById(message.getStudentId());
        return ResponseEntity.ok(message);

    }
}
