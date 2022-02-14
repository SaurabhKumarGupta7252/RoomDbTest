package com.app.roomdatabase.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.roomdatabase.Adapter.AdapterProduct;
import com.app.roomdatabase.Model.Product;
import com.app.roomdatabase.Retrofit.Service;
import com.app.roomdatabase.RoomDatabase.AppDatabase;
import com.app.roomdatabase.RoomDatabase.ProductDao;
import com.app.roomdatabase.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ProductDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        dao = AppDatabase.getInstance(MainActivity.this).getDao();

        getProduct();

        dao.count().observe(this, integer -> {

            Toast.makeText(this, "Count " + integer, Toast.LENGTH_SHORT).show();
        });
    }

    private void getProduct() {

        Service.getService().getProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                binding.rvProduct.setAdapter(new AdapterProduct(MainActivity.this, response.body()));

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}