package com.example.courseapplication220220199;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.courseapplication220220199.databinding.ActivityAddCourseBinding;

import java.io.IOException;

import Entity.CorseEntity;
import RoomDataBase.MyViewModel;

public class AddCourseActivity extends AppCompatActivity {
    ActivityAddCourseBinding binding;
    MyViewModel viewModel;
    Uri imageUri;
    private int selectedCategoryId;
    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            imageUri = data.getData();  // الحصول على URI للصورة
                            try {
                                // تحويل URI إلى Bitmap وعرضه في ImageView
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                                binding.imagecourseadd.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCourseBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        // استلام الـ category_id من Activity السابقة
        selectedCategoryId = getIntent().getIntExtra("category_id", -1);

        // التحقق من صحة الـ category_id
        if (selectedCategoryId != -1) {
            Toast.makeText(this, "تم استلام معرف التصنيف: " + selectedCategoryId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "لم يتم استلام معرف التصنيف بشكل صحيح", Toast.LENGTH_SHORT).show();
        }

        // تهيئة الـ ViewModel
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding.imagecourseadd.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickerLauncher.launch(intent);
        });

        // التعامل مع زر الإضافة
        binding.btnaddcourse.setOnClickListener(view -> {
            // استخراج البيانات المدخلة من المستخدم
            String courseTitle = binding.edcoursetitle.getText().toString();
            String coursePrice = binding.edcourseprice.getText().toString();
            String numOfStudentsStr = binding.ednomofstudent.getText().toString();
            String courseHoursStr = binding.ednomofcoursehours.getText().toString();
            String lecturerName = binding.edlectuername.getText().toString();
            String courseDetails = binding.eddecofcourse.getText().toString();

            // التحقق من صحة البيانات المدخلة
            if (courseTitle.isEmpty() || coursePrice.isEmpty() || numOfStudentsStr.isEmpty() ||
                    courseHoursStr.isEmpty() || lecturerName.isEmpty() || courseDetails.isEmpty() || selectedCategoryId == -1) {
                Toast.makeText(this, "Please fill all fields and select a category", Toast.LENGTH_SHORT).show();
                return;
            }

            // تحويل المدخلات النصية إلى قيم رقمية حيثما كان ذلك ضروريًا
            int numOfStudents = 0;
            int courseHours = 0;
            try {
                numOfStudents = Integer.parseInt(numOfStudentsStr); // تحويل الطلاب إلى int
                courseHours = Integer.parseInt(courseHoursStr); // تحويل ساعات الدورة إلى int
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid numbers for students and course hours.", Toast.LENGTH_SHORT).show();
                return; // إذا حدث خطأ في التحويل، إيقاف العملية
            }
            String imageUrl = imageUri != null ? imageUri.toString() : null;

            CorseEntity newCourse = new CorseEntity();
            newCourse.setNameOfCourse(courseTitle);
            newCourse.setPriceOfCourse(coursePrice);
            newCourse.setNomOfStudent(numOfStudents);
            newCourse.setNomHours(courseHours);
            newCourse.setNameOflecturer(lecturerName);
            newCourse.setDetilesOfCourse(courseDetails);
            newCourse.setId_Category(selectedCategoryId);
            newCourse.setImageUrl(imageUrl);
            viewModel.InsertCourse(newCourse);

            // عرض رسالة تأكيد
            Toast.makeText(this, "Course Added Successfully", Toast.LENGTH_SHORT).show();
        });
    }
}
