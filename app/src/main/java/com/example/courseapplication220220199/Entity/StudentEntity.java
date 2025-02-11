package com.example.courseapplication220220199.Entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class StudentEntity {
@PrimaryKey(autoGenerate = true)
    private int Id_OfStudent;
    private String NameOfStudent;
    private String EmailOfStudent;
    private int  enrolledCoursesCount;
    private String password;

    public StudentEntity(int id_OfStudent, String nameOfStudent, String emailOfStudent, int enrolledCoursesCount, String password) {
        Id_OfStudent = id_OfStudent;
        NameOfStudent = nameOfStudent;
        EmailOfStudent = emailOfStudent;
        this.enrolledCoursesCount = enrolledCoursesCount;
        this.password = password;
    }

    public StudentEntity() {

    }

    public int getId_OfStudent() {
        return Id_OfStudent;
    }

    public void setId_OfStudent(int id_OfStudent) {
        Id_OfStudent = id_OfStudent;
    }

    public String getNameOfStudent() {
        return NameOfStudent;
    }

    public void setNameOfStudent(String nameOfStudent) {
        NameOfStudent = nameOfStudent;
    }

    public String getEmailOfStudent() {
        return EmailOfStudent;
    }

    public void setEmailOfStudent(String emailOfStudent) {
        EmailOfStudent = emailOfStudent;
    }

    public int getEnrolledCoursesCount() {
        return enrolledCoursesCount;
    }

    public void setEnrolledCoursesCount(int enrolledCoursesCount) {
        this.enrolledCoursesCount = enrolledCoursesCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
