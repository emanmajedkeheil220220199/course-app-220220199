package Entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;


@Entity(foreignKeys = {@ForeignKey(entity = CategoryEntity.class,
        parentColumns = "Id_Category",
        childColumns = "Id_Category",
        onDelete = ForeignKey.CASCADE)})
public class CorseEntity {
@PrimaryKey(autoGenerate = true)
    private int Id_Course;
    private int Id_Category;
    private String NameOfCourse;
    private String PriceOfCourse;
    private int NomOfStudent;
    private int enrolledStudents;

    private String imageUrl;
    private int NomHours;
    private String DetilesOfCourse;
    private String NameOflecturer;

    public CorseEntity() {
    }

    public CorseEntity(int id_Course, int id_Category, String nameOfCourse, String priceOfCourse, int nomOfStudent, int enrolledStudents, String imageUrl, int nomHours, String detilesOfCourse, String nameOflecturer) {
        Id_Course = id_Course;
        Id_Category = id_Category;
        NameOfCourse = nameOfCourse;
        PriceOfCourse = priceOfCourse;
        NomOfStudent = nomOfStudent;
        this.enrolledStudents = enrolledStudents;
        this.imageUrl = imageUrl;
        NomHours = nomHours;
        DetilesOfCourse = detilesOfCourse;
        NameOflecturer = nameOflecturer;
    }

    public int getId_Course() {
        return Id_Course;
    }

    public void setId_Course(int id_Course) {
        Id_Course = id_Course;
    }

    public int getId_Category() {
        return Id_Category;
    }

    public void setId_Category(int id_Category) {
        Id_Category = id_Category;
    }

    public String getNameOfCourse() {
        return NameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        NameOfCourse = nameOfCourse;
    }

    public String getPriceOfCourse() {
        return PriceOfCourse;
    }

    public void setPriceOfCourse(String priceOfCourse) {
        PriceOfCourse = priceOfCourse;
    }

    public int getNomOfStudent() {
        return NomOfStudent;
    }

    public void setNomOfStudent(int nomOfStudent) {
        NomOfStudent = nomOfStudent;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(int enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getNomHours() {
        return NomHours;
    }

    public void setNomHours(int nomHours) {
        NomHours = nomHours;
    }

    public String getDetilesOfCourse() {
        return DetilesOfCourse;
    }

    public void setDetilesOfCourse(String detilesOfCourse) {
        DetilesOfCourse = detilesOfCourse;
    }

    public String getNameOflecturer() {
        return NameOflecturer;
    }

    public void setNameOflecturer(String nameOflecturer) {
        NameOflecturer = nameOflecturer;
    }
}
