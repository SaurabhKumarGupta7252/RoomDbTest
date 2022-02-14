package com.app.roomdatabase.Retrofit;

import com.app.roomdatabase.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface Service {

    Service service = null;

    static Service getService() {

        if (service == null) {

            return new Retrofit.Builder().baseUrl("https://fakestoreapi.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(Service.class);
        }

        return service;
    }

    @GET("products")
    Call<List<Product>> getProduct();

}
