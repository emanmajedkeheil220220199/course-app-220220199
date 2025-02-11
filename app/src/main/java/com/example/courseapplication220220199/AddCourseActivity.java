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

import com.example.courseapplication220220199.Entity.CorseEntity;
import com.example.courseapplication220220199.RoomDataBase.MyViewModel;
import com.example.courseapplication220220199.databinding.ActivityAddCourseBinding;

import java.io.IOException;

public class AddCourseActivity extends AppCompatActivity {

    ActivityAddCourseBinding binding;
    MyViewModel viewModel;
    Uri imageUri;
    private int selectedCategoryId;
    private CorseEntity courseToEdit;

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

      viewModel = new ViewModelProvider(this).get(MyViewModel.class);

      selectedCategoryId = getIntent().getIntExtra("category_id", -1);
      courseToEdit = (CorseEntity) getIntent().getSerializableExtra("course");
      boolean isEditMode = getIntent().getBooleanExtra("isEditMode", false);

      if (isEditMode) {
          binding.btnaddcourse.setText("Save Edits");
      }

      if (courseToEdit != null) {
          populateFields(courseToEdit);
      }

      binding.imagecourseadd.setOnClickListener(v -> {
          Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          imagePickerLauncher.launch(intent);
      });

      binding.btnaddcourse.setOnClickListener(view -> {
          String courseTitle = binding.edcoursetitle.getText().toString();
          String coursePrice = binding.edcourseprice.getText().toString();
          String numOfStudentsStr = binding.ednomofstudent.getText().toString();
          String courseHoursStr = binding.ednomofcoursehours.getText().toString();
          String lecturerName = binding.edlectuername.getText().toString();
          String courseDetails = binding.eddecofcourse.getText().toString();

          if (courseTitle.isEmpty() || coursePrice.isEmpty() || numOfStudentsStr.isEmpty() ||
                  courseHoursStr.isEmpty() || lecturerName.isEmpty() || courseDetails.isEmpty() || selectedCategoryId == -1) {
              Toast.makeText(this, "Please fill all fields and select a category", Toast.LENGTH_SHORT).show();
              return;
          }

          int numOfStudents = 0;
          int courseHours = 0;
          try {
              numOfStudents = Integer.parseInt(numOfStudentsStr);
              courseHours = Integer.parseInt(courseHoursStr);
          } catch (NumberFormatException e) {
              Toast.makeText(this, "Please enter valid numbers for students and course hours.", Toast.LENGTH_SHORT).show();
              return;
          }
          String imageUrl = imageUri != null ? imageUri.toString() : null;

          if (courseToEdit != null) {
              updateCourse(courseTitle, coursePrice, numOfStudents, courseHours, lecturerName, courseDetails, imageUrl);
          } else {
              addNewCourse(courseTitle, coursePrice, numOfStudents, courseHours, lecturerName, courseDetails, imageUrl);
          }
      });
  }
    private void populateFields(CorseEntity course) {
        binding.edcoursetitle.setText(course.getNameOfCourse());
        binding.edcourseprice.setText(course.getPriceOfCourse());
        binding.ednomofstudent.setText(String.valueOf(course.getNomOfStudent()));
        binding.ednomofcoursehours.setText(String.valueOf(course.getNomHours()));
        binding.edlectuername.setText(course.getNameOflecturer());
        binding.eddecofcourse.setText(course.getDetilesOfCourse());
        selectedCategoryId = course.getId_Category();
        if (course.getImageUrl() != null) {
            imageUri = Uri.parse(course.getImageUrl());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                binding.imagecourseadd.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateCourse(String title, String price, int students, int hours, String lecturer, String details, String imageUrl) {
        if (!title.equals(courseToEdit.getNameOfCourse()) || !price.equals(courseToEdit.getPriceOfCourse()) ||
                students != courseToEdit.getNomOfStudent() || hours != courseToEdit.getNomHours() ||
                !lecturer.equals(courseToEdit.getNameOflecturer()) || !details.equals(courseToEdit.getDetilesOfCourse()) ||
                (imageUrl != null && !imageUrl.equals(courseToEdit.getImageUrl()))) {

            courseToEdit.setNameOfCourse(title);
            courseToEdit.setPriceOfCourse(price);
            courseToEdit.setNomOfStudent(students);
            courseToEdit.setNomHours(hours);
            courseToEdit.setNameOflecturer(lecturer);
            courseToEdit.setDetilesOfCourse(details);
            courseToEdit.setImageUrl(imageUrl);
            viewModel.UpdateCourse(courseToEdit);

            Toast.makeText(this, "Course updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "No changes detected", Toast.LENGTH_SHORT).show();
        }
    }

    private void addNewCourse(String title, String price, int students, int hours, String lecturer, String details, String imageUrl) {
        CorseEntity newCourse = new CorseEntity();
        newCourse.setNameOfCourse(title);
        newCourse.setPriceOfCourse(price);
        newCourse.setNomOfStudent(students);
        newCourse.setNomHours(hours);
        newCourse.setNameOflecturer(lecturer);
        newCourse.setDetilesOfCourse(details);
        newCourse.setId_Category(selectedCategoryId);
        newCourse.setImageUrl(imageUrl);
        viewModel.InsertCourse(newCourse);

        Toast.makeText(this, "Course Added Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}