package com.example.exam.service.JPAData;

import com.example.exam.model.Student;
import com.example.exam.repositories.StudentRepository;
import com.example.exam.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentJPA implements StudentService
{
    /******************************* Fields ******************************/

    private StudentRepository studentRepository;

    /******************************* Constructor ******************************/

    public StudentJPA(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    /******************************* Methods ******************************/

    @Override
    public Set<Student> findAll()
    {
        Set<Student> studentSet = new HashSet<>();
        studentRepository.findAll().forEach(studentSet::add);
        return studentSet;
    }

    @Override
    public Student save(Student object)
    {
        return studentRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong)
    {
        studentRepository.deleteById(aLong);
    }

    @Override
    public Optional<Student> findById(Long aLong) {
        return studentRepository.findById(aLong);
    }
}
