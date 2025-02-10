package Entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = CorseEntity
        .class,parentColumns = "Id_Course",childColumns ="Id_Course"),@ForeignKey(entity = StudentEntity.class,
        parentColumns = "Id_OfStudent",childColumns = "Id_OfStudent")})
public class StudentCourseEntity {
@PrimaryKey(autoGenerate = true)
    private  int Id_Course;

    private  int Id_OfStudent;
    private String enrollmentDate;
    private String status;
@Ignore
    public StudentCourseEntity(int id_Course, int id_OfStudent, String enrollmentDate, String status) {
        Id_Course = id_Course;
        Id_OfStudent = id_OfStudent;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public StudentCourseEntity() {
    }

    public int getId_Course() {
        return Id_Course;
    }

    public void setId_Course(int id_Course) {
        Id_Course = id_Course;
    }

    public int getId_OfStudent() {
        return Id_OfStudent;
    }

    public void setId_OfStudent(int id_OfStudent) {
        Id_OfStudent = id_OfStudent;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
