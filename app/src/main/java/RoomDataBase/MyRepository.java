package RoomDataBase;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Daos.CategoryDao;
import Daos.CourseDao;
import Daos.LessonDao;
import Daos.StudentCourseDao;
import Daos.StudentDao;
import Entity.CategoryEntity;
import Entity.CorseEntity;
import Entity.LessonEntity;
import Entity.StudentCourseEntity;
import Entity.StudentEntity;

public class MyRepository{
private StudentDao studentDao;
    private LessonDao lessonDao;
    private CourseDao courseDao;
    private StudentCourseDao studentCourseDao;
    private CategoryDao categoryDao;

    public MyRepository(Application application) {
        MyRoomDataDase dp = MyRoomDataDase.getDatabase(application);
        courseDao =dp.courseDao();
        studentDao = dp.studentDao();
        studentCourseDao = dp.studentCourseDao();
        categoryDao = dp.categoryDao();
        lessonDao = dp.lessonDao();
    }


    void InsertCourse(CorseEntity course){
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            courseDao.InsertCourse(course);
        });
    }

    void UpdateCourse(CorseEntity course)
    {
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            courseDao.UpdateCourse(course);
        });
    }

    void DeletCourse(CorseEntity course){
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            courseDao.DeletCourse(course);
        });
    }
    LiveData<List<CorseEntity>> getAllCourses(){
        return courseDao.getAllCourses();
    }

    LiveData<List<CorseEntity>> getACoursesByIdCategory(int courseId){
        return courseDao.getACoursesByIdCategory(courseId);
    }


    LiveData<List<CorseEntity>> getACoursesById(int Id_Course){
        return  courseDao.getACoursesById(Id_Course);
    }
    void InsertCategory(CategoryEntity category) {
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            categoryDao.InsertCategory(category);
        });
    }

    void UpdateCategory(CategoryEntity category) {
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            categoryDao.UpdateCategory(category);
        });

    }



    void DeletCategory(CategoryEntity category) {
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            categoryDao.DeletCategory(category);
        });
    }
    LiveData<List<CategoryEntity>> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    LiveData<CategoryEntity> getCategoryById(int categoryid) {
        return categoryDao.getCategoryById(categoryid);
    }

        void InsertLessson(LessonEntity lesson){
            MyRoomDataDase.databaseWriteExecutor.execute(() -> {
                lessonDao.InsertLessson(lesson);
            });
        }
        void UpdateLessson(LessonEntity lesson){
            MyRoomDataDase.databaseWriteExecutor.execute(() -> {
                lessonDao.UpdateLessson(lesson);
            });
        }
        void DeletLessson(LessonEntity lesson){
            MyRoomDataDase.databaseWriteExecutor.execute(() -> {
                lessonDao.DeletLessson(lesson);
            });
        }
        LiveData<List<LessonEntity>> getAllLesssons(){
        return lessonDao.getAllLesssons();
        }
    LiveData<List<LessonEntity>> getLessonsByCourseId(int courseId){
        return  lessonDao.getLessonsByCourseId(courseId);
    }
   public void deleteCategory(int categoryId){
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            categoryDao.deleteCategory(categoryId);
        });
    }

    void InsertStudentCourse(StudentCourseEntity studentCourse) {
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.InsertStudentCourse(studentCourse);
        });
    }

    void InsertMultipleStudentCourses(List<StudentCourseEntity> studentCourses) {
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.InsertMultipleStudentCourses(studentCourses);
        });
    }
    void DeleteStudentCourse(StudentCourseEntity studentCourse) {
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.DeleteStudentCourse(studentCourse);
        });
    }

    void UpdateStatus(int courseId, int studentId, String status) {
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.UpdateStatus(courseId,studentId,status);
        });
    }

    LiveData<List<StudentCourseEntity>> getCoursesStudent(int studentid) {
        return studentCourseDao.getCoursesStudent(studentid);
    }

    //        @Query("SELECT * FROM StudentCourse WHERE Id_OfStudent = :studentId")
//        LiveData<List<StudentCourse>> getCoursesStudent(int studentId);
    LiveData<List<StudentCourseEntity>> getStudentsCourse(int courseId) {
        return studentCourseDao.getStudentsCourse(courseId);
    }

    LiveData<List<StudentCourseEntity>> getCompletedCoursesByStudent(int studentId) {
        return studentCourseDao.getCompletedCoursesByStudent(studentId);
    }

    LiveData<List<StudentCourseEntity>> getStudentsByCourseAndDate(int courseId, String date) {
        return studentCourseDao.getStudentsByCourseAndDate(courseId,date);
    }


    void deleteAllCoursesForStudent(int studentId){
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.deleteAllCoursesForStudent(studentId);
        });

}


        void InsertStudents(StudentEntity student ){
        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
            studentDao.InsertStudents(student);
        });

    }
        void UpdateStudents(StudentEntity student ) {
            MyRoomDataDase.databaseWriteExecutor.execute(() -> {
                studentDao.UpdateStudents(student);
            });
        }

        void DeletStudents(StudentEntity student ) {
            MyRoomDataDase.databaseWriteExecutor.execute(() -> {
                studentDao.DeletStudents(student);
            });
        }

        LiveData<List<StudentEntity>> getAllStudents() {
        return studentDao.getAllStudents();
        }


        LiveData<StudentEntity> getAllStudentspasswordandemail(String EmailOfStudent,String peaaword) {

return studentDao.getAllStudentspasswordandemail(EmailOfStudent,peaaword);
        }

        LiveData<List<StudentEntity>> getAllStudentsdemail(String EmailOfStudent) {
return studentDao.getAllStudentsdemail(EmailOfStudent);
        }

        LiveData<List<StudentEntity>> getStudentbyId(int Id_OfStudent) {
return studentDao.getStudentbyId(Id_OfStudent);
        }

//    void deleteLessonsByCategory(int categoryId){
//        MyRoomDataDase.databaseWriteExecutor.execute(() -> {
//            lessonDao.deleteLessonsByCategory(categoryId);
//            categoryDao.deleteCategory(categoryId);
//        });
    }



