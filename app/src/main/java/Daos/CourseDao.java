package Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Entity.CorseEntity;

@Dao

public interface CourseDao {
    @Insert
    void InsertCourse(CorseEntity course);

    @Update
    void UpdateCourse(CorseEntity course);

    @Delete
    void DeletCourse(CorseEntity course);
    @Query("SELECT * FROM CorseEntity")
    LiveData<List<CorseEntity>> getAllCourses();

    @Query("SELECT * FROM CorseEntity WHERE Id_Category=:courseId")
    LiveData<List<CorseEntity>> getACoursesByIdCategory(int courseId);

    @Query("SELECT * FROM CorseEntity WHERE Id_Course=:Id_Course")
    LiveData<List<CorseEntity>> getACoursesById(int Id_Course);

}
