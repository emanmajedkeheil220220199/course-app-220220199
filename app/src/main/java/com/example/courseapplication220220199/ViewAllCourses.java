package com.example.courseapplication220220199;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.courseapplication220220199.databinding.ActivityViewAllCoursesBinding;

import java.util.ArrayList;

import Adabters.AdabterViewCourse;
import Adabters.AdbterViewCategory;
import Entity.CategoryEntity;
import Entity.CorseEntity;
import RoomDataBase.MyViewModel;

public class ViewAllCourses extends AppCompatActivity {
    ActivityViewAllCoursesBinding binding;
    MyViewModel viewModel;
    CategoryEntity categoryEntity;
    private int categoryId;
    AdabterViewCourse course;
AdbterViewCategory category;
ArrayList<CorseEntity> corseEntities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityViewAllCoursesBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        categoryId = getIntent().getIntExtra("category_id", -1);

        binding.rvviewCourse.setLayoutManager(new LinearLayoutManager(this));
        corseEntities = new ArrayList<>();
        course = new AdabterViewCourse(corseEntities,ViewAllCourses.this);
        binding.rvviewCourse.setAdapter(course);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // جلب الكورسات الخاصة بالتصنيف
        viewModel.getACoursesByIdCategory(categoryId).observe(this, courses -> {
            corseEntities.clear();
            corseEntities.addAll(courses);
            course.notifyDataSetChanged();
        });
        binding.btnaddcourse.setOnClickListener(v -> {
            Intent intent = new Intent(ViewAllCourses.this, AddCourseActivity.class);
            intent.putExtra("category_id", categoryId); // تمرير ID التصنيف إلى واجهة إضافة الكورس
            startActivity(intent);
        });
    }
}