package com.example.fingerprint;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class controller {
    private static  final  String url = "http://192.168.106.17/fus/";
    private static  controller clientObject;
    private static Retrofit retrofit;


    controller(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit =new  Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public  static  synchronized  controller getInstance(){

        if (clientObject==null){
            clientObject = new controller();

        }
        return clientObject;


    }
    ApiService getapi( )
    {
        return  retrofit.create(ApiService.class);
    }



}
