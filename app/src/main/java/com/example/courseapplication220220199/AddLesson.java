package com.example.courseapplication220220199;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.courseapplication220220199.databinding.ActivityAddLessonBinding;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import Adabters.AdabterViewLesson;
import Entity.LessonEntity;
import RoomDataBase.MyViewModel;
public class AddLesson extends AppCompatActivity {

    MyViewModel viewModel;
    ActivityAddLessonBinding binding;
    AdabterViewLesson adabterViewLesson;
    ArrayList<LessonEntity> lessonEntities = new ArrayList<>();
    int courseId; // تأكد من تعريف courseId بشكل صحيح

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

binding= ActivityAddLessonBinding.inflate(getLayoutInflater());
        courseId = getIntent().getIntExtra("courseId", -1);  // استلام courseId
        setContentView(binding.getRoot());
        // تهيئة ViewModel
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // مراقبة التغييرات في بيانات الدروس
        viewModel.getLessonsByCourseId(courseId).observe(this, lessons -> {
            if (lessons != null && !lessons.isEmpty()) {
                lessonEntities.clear();
                lessonEntities.addAll(lessons);

                // إذا تم تحميل الدروس بنجاح، تأكد من أن المحول تم تهيئته
                if (adabterViewLesson == null) {
                    adabterViewLesson = new AdabterViewLesson(this, lessonEntities);
                } else {
                    adabterViewLesson.notifyDataSetChanged();
                }
            }
        });

        // إضافة درس جديد
        binding.btnAddlesson.setOnClickListener(view -> {
            String lessonname = binding.edNamelesson.getText().toString();
            String lessondec = binding.edDeclesson.getText().toString();
            String linklesson = binding.edLinklesson.getText().toString();
            if (lessonname.isEmpty() || lessondec.isEmpty() || linklesson.isEmpty()
                  ) {
                Toast.makeText(this, "Please fill all fields and select a category", Toast.LENGTH_SHORT).show();
                return;
            }
            LessonEntity newLesson = new LessonEntity();
            // إنشاء كائن الدرس الجديد مع تعيين قيمه
            newLesson.setNameLesson(lessonname);
            newLesson.setDescLesson(lessondec);
            newLesson.setLinklesson(linklesson);
            newLesson.setId_Course(courseId); // **تعيين courseId للدرس**

            // إدخال الدرس في قاعدة البيانات
            viewModel.InsertLessson(newLesson);

            Toast.makeText(this, "Lesson added successfully!", Toast.LENGTH_SHORT).show();
        });

    }
}
