package com.example.exam.repositories;

import com.example.exam.model.Student;
import org.springframework.data.repository.CrudRepository;

//Repository that extends the CrudRepository interface which is a predefined interface
//provided by that spring framework
public interface StudentRepository extends CrudRepository<Student, Long>
{

}
