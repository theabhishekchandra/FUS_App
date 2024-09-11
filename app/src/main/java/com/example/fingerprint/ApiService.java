package com.example.fingerprint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("api/read_log.php")
        Call<List<ResponseModel>> getdata();


    @FormUrlEncoded
    @POST("ad_data.php")
    Call<ResponseModel> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("insert_user.php")
    Call<ResponseModel> updatewebcode(
            @Field("username") String username,
            @Field("webcode") String webcode

    );
    @FormUrlEncoded
    @POST("user_act.php")
    Call<ResponseModel> user_act_up(
            @Field("username") String username,
            @Field("status") String status
    );

}
