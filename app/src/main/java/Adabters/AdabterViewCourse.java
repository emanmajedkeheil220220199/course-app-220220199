package Adabters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseapplication220220199.CourseAndLesson;
import com.example.courseapplication220220199.R;
import com.example.courseapplication220220199.databinding.ItemviewcourseBinding;

import java.util.ArrayList;

import Entity.CorseEntity;
public class AdabterViewCourse extends RecyclerView.Adapter<AdabterViewCourse.CourseViewHolder> {

    ArrayList<CorseEntity> courseEntities;
    Context context;

    public AdabterViewCourse(ArrayList<CorseEntity> courseEntities,Context context) {
        this.courseEntities = courseEntities;
        this.context=context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemviewcourseBinding binding = ItemviewcourseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CorseEntity course = courseEntities.get(position);

        holder.courseName.setText(course.getNameOfCourse());
        holder.lecturerName.setText(course.getNameOflecturer());
        holder.coursePrice.setText("السعر: " + course.getPriceOfCourse() + "$");
        holder.courseHours.setText("عدد الساعات: " + course.getNomHours());
        holder.studentsCount.setText("عدد الطلاب: " + course.getNomOfStudent());
        holder.courseDescription.setText(course.getDetilesOfCourse());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CourseAndLesson.class);
            intent.putExtra("courseId", course.getId_Course()); // تمرير ID الدورة
            intent.putExtra("courseName", course.getNameOfCourse());
            intent.putExtra("lecturerName", course.getNameOflecturer());
            intent.putExtra("coursePrice", course.getPriceOfCourse());
            intent.putExtra("courseHours", course.getNomHours());
            intent.putExtra("studentsCount", course.getNomOfStudent());
            intent.putExtra("courseDescription", course.getDetilesOfCourse());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return courseEntities.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        ImageView courseImage;
        TextView courseName, lecturerName, coursePrice, courseHours, studentsCount, courseDescription;

        public CourseViewHolder(ItemviewcourseBinding binding) {
            super(binding.getRoot());

            courseImage = binding.viewimgecourse;
            courseName = binding.viewcoursename;
            lecturerName = binding.viewcourselecure;
            coursePrice = binding.pricecourse;
            courseHours = binding.hourecourse;
            studentsCount = binding.nomofstucourse;
            courseDescription = binding.deccourse;
        }
    }
}

