package com.example.onrequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {
    @POST("menu_items")
    retrofit2.Call<MenuItemAPI> createMenuItemApi(@Body MenuItemAPI menuItemAPI);

    @GET("menu_items/search")
    Call<List<MenuItemAPI>> searchMenuItems(@Query("keyword") String keyword);

}
