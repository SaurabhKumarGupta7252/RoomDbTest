package com.app.roomdatabase.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.app.roomdatabase.Model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("select count(id) from Product")
    LiveData<Integer> count();

    @Insert
    void insert(Product product);

    @Query("update product set qty=:qty where id=:productId")
    void update(int qty, int productId);

    @Query("delete from product where id=:productId")
    void delete(int productId);

    @Query("select * from product")
    LiveData<List<Product>> allProduct();

    @Query("select exists (select * from product where id=:productId)")
    boolean isExist(int productId);

    @Query("select qty from Product where id=:id")
    int count(int id);

}
