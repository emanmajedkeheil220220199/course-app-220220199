package com.example.courseapplication220220199;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.courseapplication220220199.databinding.ActivityAddCategoryAdminBinding;

import java.util.ArrayList;

import Adabters.AdbterViewCategory;
import Entity.CategoryEntity;
import RoomDataBase.MyViewModel;

public class AddCategoryAdmin extends AppCompatActivity {
    ActivityAddCategoryAdminBinding binding;
    MyViewModel viewModel;
    ArrayList<CategoryEntity> courses = new ArrayList<>();
    AdbterViewCategory adbterAdminCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCategoryAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // تحقق من وجود بيانات التصنيف عند الضغط على زر تعديل
        int categoryId = getIntent().getIntExtra("category_id", -1);
        String categoryName = getIntent().getStringExtra("category_name");

        if (categoryId != -1) {
            // إذا كان الـ categoryId موجودًا، هذا يعني أنه يتم تعديل التصنيف
            binding.ednamecat.setText(categoryName);  // وضع اسم التصنيف في الـ EditText
            binding.btnadd.setText("Edit");  // تغيير النص إلى تعديل
        }

        binding.catrvadmin.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adbterAdminCategory = new AdbterViewCategory(this, courses, viewModel);
        binding.catrvadmin.setAdapter(adbterAdminCategory);
        viewModel.getAllCategory().observe(this, categoryEntities -> {
            courses.clear();
            courses.addAll(categoryEntities);
            adbterAdminCategory.notifyDataSetChanged();
        });

        binding.btnadd.setOnClickListener(view -> {
            String categoryNameInput = binding.ednamecat.getText().toString().trim();

            if (!categoryNameInput.isEmpty()) {
                if (categoryId == -1) {
                    // إذا كان categoryId غير موجود، إضافة تصنيف جديد
                    viewModel.InsertCategory(new CategoryEntity(categoryNameInput));
                    Toast.makeText(this, "تمت إضافة التصنيف", Toast.LENGTH_SHORT).show();
                } else {
                    // إذا كان categoryId موجودًا، تعديل التصنيف
                    viewModel.UpdateCategory(new CategoryEntity(categoryId, categoryNameInput));
                    Toast.makeText(this, "تم تعديل التصنيف", Toast.LENGTH_SHORT).show();
                }
                binding.ednamecat.setText("");  // مسح الحقل
            } else {
                Toast.makeText(this, "يرجى إدخال اسم التصنيف", Toast.LENGTH_SHORT).show();
            }
        });
    }
}