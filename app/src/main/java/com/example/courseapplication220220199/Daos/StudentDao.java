package com.example.courseapplication220220199.Daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.courseapplication220220199.Entity.StudentEntity;

@Dao
public interface StudentDao {

    @Insert
    void InsertStudents(StudentEntity student );
    @Update
    void UpdateStudents(StudentEntity student );
    @Delete
    void DeletStudents(StudentEntity student );

    @Query("SELECT * FROM StudentEntity")
    LiveData<List<StudentEntity>> getAllStudents();
    @Query("SELECT * FROM StudentEntity WHERE EmailOfStudent =:EmailOfStudent AND password=:peaaword ")
    LiveData<StudentEntity> getAllStudentspasswordandemail(String EmailOfStudent,String peaaword);

    @Query("SELECT * FROM StudentEntity WHERE EmailOfStudent =:EmailOfStudent")
    LiveData<List<StudentEntity>> getAllStudentsdemail(String EmailOfStudent);
    @Query("SELECT * FROM StudentEntity WHERE Id_OfStudent=:Id_OfStudent")
    LiveData<List<StudentEntity>> getStudentbyId(int Id_OfStudent);

}
