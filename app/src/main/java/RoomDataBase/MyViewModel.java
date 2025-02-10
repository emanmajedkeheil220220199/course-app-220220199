package RoomDataBase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

import Entity.CategoryEntity;
import Entity.CorseEntity;
import Entity.LessonEntity;
import Entity.StudentCourseEntity;
import Entity.StudentEntity;

public class MyViewModel extends AndroidViewModel {
    private MyRepository repo;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repo=new MyRepository(application);
    }

  public   void InsertCourse(CorseEntity course){
            repo.InsertCourse(course);

    }

    public void UpdateCourse(CorseEntity course)
    {
            repo.UpdateCourse(course);

    }

    void DeletCourse(CorseEntity course){
            repo.DeletCourse(course);

    }
    LiveData<List<CorseEntity>> getAllCourses(){
        return repo.getAllCourses();
    }

   public   LiveData<List<CorseEntity>> getACoursesByIdCategory(int courseId){
        return repo.getACoursesByIdCategory(courseId);
    }


    public LiveData<List<CorseEntity>> getACoursesById(int Id_Course){
        return  repo.getACoursesById(Id_Course);
    }
   public void InsertCategory(CategoryEntity category) {
            repo.InsertCategory(category);
    }


     public void UpdateCategory(CategoryEntity category) {
            repo.UpdateCategory(category);


    }



       public void DeletCategory(CategoryEntity category) {
            repo.DeletCategory(category);

    }
   public    LiveData<List<CategoryEntity>> getAllCategory() {
        return repo.getAllCategory();
    }

     public LiveData<CategoryEntity> getCategoryById(int categoryid) {
        return repo.getCategoryById(categoryid);
    }
     public LiveData<List<LessonEntity>> getLessonsByCourseId(int courseId){
        return  repo.getLessonsByCourseId(courseId);
    }

     public void InsertLessson(LessonEntity lesson){
            repo.InsertLessson(lesson);

    }
    void UpdateLessson(LessonEntity lesson){
            repo.UpdateLessson(lesson);

    }
    void DeletLessson(LessonEntity lesson){
            repo.DeletLessson(lesson);

    }
     public LiveData<List<LessonEntity>> getAllLesssons(){
        return repo.getAllLesssons();
    }

    void InsertStudentCourse(StudentCourseEntity studentCourse) {
            repo.InsertStudentCourse(studentCourse);
    }

    void InsertMultipleStudentCourses(List<StudentCourseEntity> studentCourses) {
            repo.InsertMultipleStudentCourses(studentCourses);

    }
    void DeleteStudentCourse(StudentCourseEntity studentCourse) {
            repo.DeleteStudentCourse(studentCourse);

    }

    void UpdateStatus(int courseId, int studentId, String status) {
            repo.UpdateStatus(courseId,studentId,status);

    }

    LiveData<List<StudentCourseEntity>> getCoursesStudent(int studentid) {
        return repo.getCoursesStudent(studentid);
    }

    LiveData<List<StudentCourseEntity>> getStudentsCourse(int courseId) {
        return repo.getStudentsCourse(courseId);
    }

    LiveData<List<StudentCourseEntity>> getCompletedCoursesByStudent(int studentId) {
        return repo.getCompletedCoursesByStudent(studentId);
    }

    LiveData<List<StudentCourseEntity>> getStudentsByCourseAndDate(int courseId, String date) {
        return repo.getStudentsByCourseAndDate(courseId,date);
    }


    void deleteAllCoursesForStudent(int studentId){
            repo.deleteAllCoursesForStudent(studentId);


    }


    void InsertStudents(StudentEntity student ){
            repo.InsertStudents(student);

    }
    void UpdateStudents(StudentEntity student ) {
            repo.UpdateStudents(student);

    }

    void DeletStudents(StudentEntity student ) {
            repo.DeletStudents(student);

    }

    LiveData<List<StudentEntity>> getAllStudents() {
        return repo.getAllStudents();
    }


    LiveData<StudentEntity> getAllStudentspasswordandemail(String EmailOfStudent,String peaaword) {

        return repo.getAllStudentspasswordandemail(EmailOfStudent,peaaword);
    }

    LiveData<List<StudentEntity>> getAllStudentsdemail(String EmailOfStudent) {
        return repo.getAllStudentsdemail(EmailOfStudent);
    }

    LiveData<List<StudentEntity>> getStudentbyId(int Id_OfStudent) {
        return repo.getStudentbyId(Id_OfStudent);
    }
    public void selectCategory(int categoryId) {
        repo.getCategoryById(categoryId);
    }

    // ✅ دالة لمراقبة التصنيف المحدد

    public void deleteCategory(int categoryId) {
        repo.deleteCategory(categoryId);
    }
}





