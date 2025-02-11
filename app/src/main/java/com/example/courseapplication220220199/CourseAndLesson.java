package com.example.courseapplication220220199;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.courseapplication220220199.databinding.ActivityCourseAndLessonBinding;

import java.util.ArrayList;

import com.example.courseapplication220220199.Adabters.AdabterViewLesson;
import com.example.courseapplication220220199.Entity.CorseEntity;
import com.example.courseapplication220220199.Entity.LessonEntity;
import com.example.courseapplication220220199.RoomDataBase.MyViewModel;


public class CourseAndLesson extends AppCompatActivity {
    ActivityCourseAndLessonBinding binding;
    ArrayList<CorseEntity> corseEntities;
    ArrayList<LessonEntity> lessonEntities; // تأكد من تهيئة القائمة
    MyViewModel viewModel;
    AdabterViewLesson adabterViewLesson;
    int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseAndLessonBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        // استقبال البيانات من Intent
        String courseName = getIntent().getStringExtra("courseName");
        String lecturerName = getIntent().getStringExtra("lecturerName");
        String coursePrice = getIntent().getStringExtra("coursePrice");
        String courseHours = getIntent().getStringExtra("courseHours");
        String studentsCount = getIntent().getStringExtra("studentsCount");
        String courseDescription = getIntent().getStringExtra("courseDescription");
        // تعيين البيانات إلى الـ TextViews باستخدام الـ ViewBinding
        binding.nameofcourse.setText(courseName);
        binding.nameoflecture.setText(lecturerName);
        binding.price.setText(coursePrice);
        binding.hourse.setText(courseHours);
        binding.nmoofstudent.setText(studentsCount);
     //   String lessonname=getIntent().getStringExtra();

        courseId = getIntent().getIntExtra("courseId", -1);
        // قم بتهيئة الـ ViewModel
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // مراقبة البيانات الخاصة بالدروس
        viewModel.getLessonsByCourseId(courseId).observe(this, lessons -> {
            if (lessons != null && !lessons.isEmpty()) {
                lessonEntities.clear();
                lessonEntities.addAll(lessons);
                adabterViewLesson.notifyDataSetChanged();
            } else {
                // يمكنك إضافة رسالة تنبيه إذا كانت القائمة فارغة
            }
        });

        // تهيئة المحول بعد التأكد من أن البيانات محملة
//        adabterViewLesson = new AdabterViewLesson(this, lessonEntities);
//        binding.rvlesson.setAdapter(adabterViewLesson);
//        binding.rvlesson.setLayoutManager(new LinearLayoutManager(this));
//        lessonEntities = new ArrayList<>();
        binding.rvlesson.setLayoutManager(new LinearLayoutManager(this));
        lessonEntities = new ArrayList<>();
        adabterViewLesson = new AdabterViewLesson(this,lessonEntities);
        binding.rvlesson.setAdapter(adabterViewLesson);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getLessonsByCourseId(courseId).observe(this, lessonEntities1 -> {
            lessonEntities.clear();
            lessonEntities.addAll(lessonEntities1);
            adabterViewLesson.notifyDataSetChanged();
        });
        binding.iconadd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddLesson.class);
            intent.putExtra("courseId", courseId);
            startActivity(intent);
        });
    }
}

