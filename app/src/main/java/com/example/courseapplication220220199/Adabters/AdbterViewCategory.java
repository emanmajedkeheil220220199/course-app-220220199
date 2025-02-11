package com.example.courseapplication220220199.Adabters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseapplication220220199.AddCategoryAdmin;
import com.example.courseapplication220220199.ViewAllCourses;
import com.example.courseapplication220220199.databinding.ItemViewCategoryBinding;

import java.util.ArrayList;

import com.example.courseapplication220220199.Entity.CategoryEntity;
import com.example.courseapplication220220199.RoomDataBase.MyViewModel;

public class AdbterViewCategory extends RecyclerView.Adapter<AdbterViewCategory.AdminCategory> {
    ArrayList<CategoryEntity> categoryCourses;
    Context context; // السياق الذي سيتم استخدامه
    MyViewModel viewModel;

    // تعديل المُنشئ ليأخذ السياق
    public AdbterViewCategory(Context context, ArrayList<CategoryEntity> categoryCourses,MyViewModel viewModel) {
        this.context = context; // تعيين السياق
        this.categoryCourses = categoryCourses;
        this.viewModel=viewModel;
    }

    @NonNull
    @Override
    public AdminCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewCategoryBinding binding = ItemViewCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AdminCategory(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminCategory holder, int position) {
        CategoryEntity categoryCourse = categoryCourses.get(position);
        holder.Categoryname.setText(categoryCourse.getName_Cat());

        // التأكد من وجود السياق
        holder.card_view.setOnClickListener(v -> {
            if (context != null) {
                int categoryId = categoryCourses.get(position).getId_Category(); // استدعاء getId() من كائن التصنيف
                Intent intent = new Intent(context, ViewAllCourses.class);
                intent.putExtra("category_id", categoryId); // تمرير ID التصنيف
                context.startActivity(intent);
            } else {
                // طباعة رسالة خطأ إذا كان السياق null
                Log.e("AdbterViewCategory", "Context is null");
            }
        });
        holder.btn_delete.setOnClickListener(v -> {
            int categoryId = categoryCourses.get(position).getId_Category();
                viewModel.deleteCategory(categoryId);
                // تحديث القائمة بعد الحذف
                categoryCourses.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, categoryCourses.size());

        });
        holder.edit.setOnClickListener(v -> {
            if (context != null) {
                // تمرير بيانات التصنيف إلى Activity التعديل
                Intent intent = new Intent(context, AddCategoryAdmin.class); // نفس Activity لكن مع تعديل
                intent.putExtra("category_id", categoryCourse.getId_Category()); // إرسال الـ ID للتصنيف
                intent.putExtra("category_name", categoryCourse.getName_Cat()); // إرسال الاسم الحالي
                context.startActivity(intent);
            } else {
                Log.e("AdbterViewCategory", "Context is null");
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryCourses.size();
    }

    class AdminCategory extends RecyclerView.ViewHolder {
        TextView Categoryname;
        CardView card_view;
        ImageView btn_delete;
        ImageView edit;

        public AdminCategory(ItemViewCategoryBinding binding) {
            super(binding.getRoot());
            card_view = binding.cardView;
            Categoryname = binding.categoryName;
            btn_delete=binding.btnDelete;
            edit=binding.editicon;
        }
    }
}
