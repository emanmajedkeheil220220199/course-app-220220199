package Adabters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.courseapplication220220199.databinding.ItemViewLessonBinding;
import java.net.URI;
import java.util.ArrayList;

import Entity.LessonEntity; // تأكد من استيراد الكلاس الخاص بك

public class AdabterViewLesson extends RecyclerView.Adapter<AdabterViewLesson.LessonViewHolder> {

    ArrayList<LessonEntity> lessonEntities = new ArrayList<>(); // القائمة التي تحتوي على الدروس
     Context context;

    public AdabterViewLesson(Context context, ArrayList<LessonEntity> lessonEntities) {
        this.context = context;
        this.lessonEntities = lessonEntities;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewLessonBinding binding = ItemViewLessonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LessonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        LessonEntity lesson = lessonEntities.get(position);
  holder.lessonName.setText(lesson.getNameLesson());
        holder.lessonDetails.setText(lesson.getDescLesson());
       holder.lessonLink.setText(lesson.getLinklesson());

    }

    @Override
    public int getItemCount() {
        return lessonEntities.size();
    }
    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView lessonName, lessonDetails, lessonLink;

        public LessonViewHolder(ItemViewLessonBinding binding) {
            super(binding.getRoot());
         lessonName = binding.tvCourseName;
            lessonDetails = binding.tvCourseDetails;
            lessonLink =binding.tvCourseLink;
        }
    }
}
