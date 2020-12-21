package com.example.exam.service;

import java.util.Optional;
import java.util.Set;

//Super service that is extended by the other services
//It uses Generics annotated by the T
public interface CrudService<T,ID>{

    Set<T> findAll(); //Used to find all objects in a Set
    T save(T object); //Used to save objects
    void deleteById(ID id); //Used to delete object by their ID
    Optional<T> findById(ID id); //Used to find objects by their ID

}
