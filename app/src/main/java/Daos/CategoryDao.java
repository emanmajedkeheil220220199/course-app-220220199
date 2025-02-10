package Daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Entity.CategoryEntity;

@Dao
public interface CategoryDao {
    @Insert
    void InsertCategory(CategoryEntity category);
    @Update
    void UpdateCategory(CategoryEntity category);
    @Delete
    void DeletCategory(CategoryEntity category);
    @Query("SELECT * FROM CategoryEntity")
    LiveData<List<CategoryEntity>> getAllCategory();
    @Query("DELETE FROM CategoryEntity WHERE id_Category = :categoryId")
    void deleteCategory(int categoryId);

    @Query("SELECT * FROM CategoryEntity WHERE Id_Category=:categoryid")
    LiveData<CategoryEntity> getCategoryById(int categoryid);
}
