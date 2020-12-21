package com.example.exam.model;



import com.sun.istack.Nullable;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student
{

    /******************************* Fields ******************************/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    private String studentName;

    private String studentEmail;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    @Nullable
    private Supervisor supervisor;

    /******************************* Constructor and Methods ******************************/

    public Student(Long studentId, String studentName, String studentEmail, Supervisor supervisor)
    {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.supervisor = supervisor;
    }
    public Student()
    {

    }

    public Student(String studentName, String studentEmail, Supervisor supervisor)
    {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.supervisor = supervisor;
    }


    /******************************* Getters and Setters ******************************/

    public Long getStudentId()
    {
        return studentId;
    }

    public void setStudentId(Long studentId)
    {
        this.studentId = studentId;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public String getStudentEmail()
    {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail)
    {
        this.studentEmail = studentEmail;
    }

    public Supervisor getSupervisor()
    {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor)
    {
        this.supervisor = supervisor;
    }
}
