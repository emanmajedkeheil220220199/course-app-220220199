package com.example.courseapplication220220199.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = CorseEntity.class,
                        parentColumns = "Id_Course",
                        childColumns = "Id_Course",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index(value = "Id_Course")}
)
public class LessonEntity {
    @PrimaryKey(autoGenerate = true)
    private int Id_Lesson;
    private int Id_Course;
    private String NameLesson;
    private String descLesson;
    private String linklesson;

    public LessonEntity(int id_Course, String nameLesson, String descLesson, String linklesson) {
        Id_Course = id_Course;
        NameLesson = nameLesson;
        this.descLesson = descLesson;
        this.linklesson = linklesson;
    }

    public LessonEntity() {
    }

    public int getId_Lesson() {
        return Id_Lesson;
    }

    public void setId_Lesson(int id_Lesson) {
        Id_Lesson = id_Lesson;
    }

    public int getId_Course() {
        return Id_Course;
    }

    public void setId_Course(int id_Course) {
        Id_Course = id_Course;
    }

    public String getNameLesson() {
        return NameLesson;
    }

    public void setNameLesson(String nameLesson) {
        NameLesson = nameLesson;
    }

    public String getDescLesson() {
        return descLesson;
    }

    public void setDescLesson(String descLesson) {
        this.descLesson = descLesson;
    }

    public String getLinklesson() {
        return linklesson;
    }

    public void setLinklesson(String linklesson) {
        this.linklesson = linklesson;
    }
}