package com.example.onrequest.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSource {

    //private static final String BASE_URL = "http://127.0.0.1:8000/api/";
    //private static final String BASE_URL = "http://127.0.0.1:8000/api/";
    private static final String BASE_URL = "http://10.0.2.2:8000/api/";
    //private static final String BASE_URL = "http://10.50.45.137:8000/api/";


    private static Retrofit retrofit;


    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
