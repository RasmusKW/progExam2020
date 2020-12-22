package com.example.exam.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity //Annotating with @Entity to specify that this class is an entity type
//The entity type describes the mapping between the actual persistable domain model object and a database table row.
public class Supervisor
{
    /***************************************************  Fields ********************************************************************/

    @Id //The @Id is the entity identifier which models the primary key of an entity, used to identify each specific entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) //setting the generation of the primary key to be GenerationType.IDENTITY which makes it auto increment
    @Column(name = "supervisor_id") //assigning the column name
    private long supervisorId; //variable name to reference in our program

    private String name;

    /***************************************************  Relational Database Varaibles **********************************************/

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "supervisor" ) //Cascade type detach, because we dont want to delete a supervisor,
    // when a student is deleted and vice versa.
    @Nullable // nullable so that we can delete the student, without causing error.
    @JsonManagedReference("supervisor") //Using Jackson to handle relations
    private Set<Student> students = new HashSet<>();


    /***************************************************  Constructor and Methods ****************************************************/


    public Supervisor(long supervisorId, String name) {
        this.supervisorId = supervisorId;
        this.name = name;
    }

    public Supervisor() { }

    /**
     * This function adds a student to the supervisor, remember to save after via a service, or the DB will not update.
     * @param student
     */
    public void addStudent(Student student)
    {
        student.setSupervisor(this);
        students.add(student);
    }

    /***************************************************  Getters and Setters ********************************************************/

    public long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(@Nullable Set<Student> students) {
        this.students = students;
    }
}
