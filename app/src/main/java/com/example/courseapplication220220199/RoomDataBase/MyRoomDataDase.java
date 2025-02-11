package com.example.courseapplication220220199.RoomDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.courseapplication220220199.Daos.CategoryDao;
import com.example.courseapplication220220199.Daos.CourseDao;
import com.example.courseapplication220220199.Daos.LessonDao;
import com.example.courseapplication220220199.Daos.StudentCourseDao;
import com.example.courseapplication220220199.Daos.StudentDao;
import com.example.courseapplication220220199.Entity.CategoryEntity;
import com.example.courseapplication220220199.Entity.CorseEntity;
import com.example.courseapplication220220199.Entity.LessonEntity;
import com.example.courseapplication220220199.Entity.StudentCourseEntity;
import com.example.courseapplication220220199.Entity.StudentEntity;
import com.example.courseapplication220220199.Entity.UriConverter;

@Database(entities = {StudentCourseEntity.class, CategoryEntity.class, LessonEntity.class, StudentEntity.class, CorseEntity.class}, version = 1, exportSchema = false)

@TypeConverters(UriConverter.class)

public abstract class MyRoomDataDase extends RoomDatabase {

    public abstract CategoryDao categoryDao();
    public abstract StudentDao studentDao();
    public abstract CourseDao courseDao();
    public abstract StudentCourseDao studentCourseDao();
    public abstract LessonDao lessonDao();



    private static volatile MyRoomDataDase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyRoomDataDase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDataDase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MyRoomDataDase.class, "MyRoomDataDase_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
