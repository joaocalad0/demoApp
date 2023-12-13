package com.example.onrequest.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {
    @GET("menu-items")
    Call<List<MenuItemAPI>> getMenuItems();

    @GET("menu_items/search")
    Call<List<MenuItemAPI>> searchMenuItems(@Query("keyword") String keyword);

}
