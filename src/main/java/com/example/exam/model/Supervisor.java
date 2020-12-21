package com.example.exam.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supervisor
{
    /***************************************************  Fields ********************************************************************/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supervisor_id")
    private long supervisorId;

    private String name;

    /***************************************************  Relational Database Varaibles **********************************************/

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "supervisor" ) //Cascade type detach, because we dont want to delete a supervisor,
    // when a student is deleted and vice versa.
    @Nullable // nullable so that we can delete the student, without causing error.
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
