package com.example.exam.controller;

import com.example.exam.model.Message;
import com.example.exam.model.Student;
import com.example.exam.model.Supervisor;
import com.example.exam.service.StudentService;
import com.example.exam.service.SupervisorService;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StudentController
{

    @Autowired
    StudentService studentService;

    @Autowired
    SupervisorService supervisorService;

    @RequestMapping("/")
    public ModelAndView index(Model model)
    {
        model.addAttribute("students", studentService.findAll());
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("supervisors",supervisorService.findAll());
        return mv;
    }

    @RequestMapping(value = "/api/addStudent", method = RequestMethod.POST)
    public ResponseEntity<?> addStudent(@RequestBody Message message)
    {

        Student student = new Student();

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

    @RequestMapping(value = "/api/updateStudent", method = RequestMethod.POST)
    public ResponseEntity<?> updateStudent(@RequestBody Message message)
    {

        Student student = studentService.findById(message.getStudentId()).get();
        student.setStudentName(message.getStudentName());
        student.setStudentEmail(message.getStudentEmail());

        Supervisor supervisor = supervisorService.findById(message.getSupervisorId()).get();

        supervisor.addStudent(student);
        System.out.println(student.getSupervisor());

        student = studentService.save(student);
        supervisorService.save(supervisor);
        System.out.println(student.getSupervisor());

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
