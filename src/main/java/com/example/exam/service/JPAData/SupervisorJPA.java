package com.example.exam.service.JPAData;

import com.example.exam.model.Student;
import com.example.exam.model.Supervisor;
import com.example.exam.repositories.SupervisorRepository;
import com.example.exam.service.SupervisorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class SupervisorJPA implements SupervisorService
{
    /******************************* Fields ******************************/

    private SupervisorRepository supervisorRepository;

    /******************************* Constructor ******************************/

    public SupervisorJPA(SupervisorRepository supervisorRepository)
    {
        this.supervisorRepository = supervisorRepository;
    }

    /******************************* Methods ******************************/

    @Override
    public Set<Supervisor> findAll()
    {
        Set<Supervisor> supervisorSet = new HashSet<>();
        supervisorRepository.findAll().forEach(supervisorSet::add);
        return supervisorSet;
    }

    @Override
    public Supervisor save(Supervisor object)
    {
        return supervisorRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong)
    {
        supervisorRepository.deleteById(aLong);
    }

    @Override
    public Optional<Supervisor> findById(Long aLong)
    {
        return supervisorRepository.findById(aLong);
    }
}
