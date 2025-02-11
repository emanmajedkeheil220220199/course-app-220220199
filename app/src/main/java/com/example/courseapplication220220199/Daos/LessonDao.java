package com.example.courseapplication220220199.Daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.courseapplication220220199.Entity.LessonEntity;

@Dao
public interface LessonDao {
    @Insert
    void InsertLessson(LessonEntity lesson);
    @Update
    void UpdateLessson(LessonEntity lesson);
    @Delete
    void DeletLessson(LessonEntity lesson);
    @Query("SELECT * FROM LessonEntity WHERE Id_Course = :courseId")
    LiveData<List<LessonEntity>> getLessonsByCourseId(int courseId);
    @Query("SELECT * FROM LessonEntity")
    LiveData<List<LessonEntity>> getAllLesssons();
    @Query("DELETE FROM LessonEntity WHERE Id_Course = :categoryId")
    void deleteLessonsByCategory(int categoryId);
}

