package Daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import Entity.StudentCourseEntity;
import Entity.StudentEntity;

@Dao
public interface StudentCourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertStudentCourse(StudentCourseEntity studentCourse);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertMultipleStudentCourses(List<StudentCourseEntity> studentCourses);
    @Delete
    void DeleteStudentCourse(StudentCourseEntity studentCourse);

    @Query("UPDATE StudentCourseEntity SET status = :status WHERE Id_Course = :courseId AND Id_OfStudent = :studentId")
    void UpdateStatus(int courseId, int studentId, String status);

    @Query("SELECT * FROM StudentCourseEntity WHERE Id_OfStudent =:studentid")
    LiveData<List<StudentCourseEntity>> getCoursesStudent(int studentid);

    //        @Query("SELECT * FROM StudentCourse WHERE Id_OfStudent = :studentId")
//        LiveData<List<StudentCourse>> getCoursesStudent(int studentId);
    @Query("SELECT * FROM StudentCourseEntity WHERE Id_Course = :courseId")
    LiveData<List<StudentCourseEntity>> getStudentsCourse(int courseId);

    @Query("SELECT * FROM StudentCourseEntity WHERE Id_OfStudent = :studentId AND status = 'Completed'")
    LiveData<List<StudentCourseEntity>> getCompletedCoursesByStudent(int studentId);

    @Query("SELECT * FROM StudentCourseEntity WHERE Id_Course = :courseId AND enrollmentDate = :date")
    LiveData<List<StudentCourseEntity>> getStudentsByCourseAndDate(int courseId, String date);

    @Query("DELETE FROM StudentCourseEntity WHERE Id_OfStudent = :studentId")
    void deleteAllCoursesForStudent(int studentId);
}
