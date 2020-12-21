package com.example.exam.repositories;

import com.example.exam.model.Supervisor;
import org.springframework.data.repository.CrudRepository;

//Repository that extends the CrudRepository interface which is a predefined interface
//provided by that spring framework
public interface SupervisorRepository extends CrudRepository<Supervisor, Long>
{

}
