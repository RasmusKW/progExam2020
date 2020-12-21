package com.example.exam.controller;

import com.example.exam.model.Student;
import com.example.exam.model.Supervisor;
import com.example.exam.service.StudentService;
import com.example.exam.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController
{

    @Autowired
    StudentService studentService;

    @Autowired
    SupervisorService supervisorService;

    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("students", studentService.findAll());
        return "index";
    }

    @RequestMapping(value = "/api/addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute Student student, @RequestParam("supervisorId") long supervisorId)
    {
        Supervisor supervisor;

//        if();
    }

}
