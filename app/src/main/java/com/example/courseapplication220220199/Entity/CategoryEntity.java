package com.example.courseapplication220220199.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CategoryEntity {
    @PrimaryKey(autoGenerate = true)
    private int Id_Category;
    private String Name_Cat;

    public CategoryEntity(int id_Category, String name_Cat) {
        Id_Category = id_Category;
        Name_Cat = name_Cat;
    }

    public CategoryEntity() {
    }

    public CategoryEntity(String name_Cat) {
        Name_Cat = name_Cat;
    }

    public int getId_Category() {
        return Id_Category;
    }

    public void setId_Category(int id_Category) {
        Id_Category = id_Category;
    }

    public String getName_Cat() {
        return Name_Cat;
    }

    public void setName_Cat(String name_Cat) {
        Name_Cat = name_Cat;
    }
}

