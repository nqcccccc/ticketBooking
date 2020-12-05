package com.example.bookingticket.Retrofit2;

import com.example.bookingticket.Account;
import com.example.bookingticket.Slider;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClient {

    @Multipart
    @POST("avaUpload.php")
    Call<String> uploadAva(@Part MultipartBody.Part photo);

    @FormUrlEncoded
    @POST("register.php")
    Call<String> insertUser(@Field("user") String user,
                            @Field("pass") String pass,
                            @Field("email") String email,
                            @Field("avatar") String avatar);

    @FormUrlEncoded
    @POST("login.php")
    Call<List<Account>> userLogin(@Field("user") String user,
                                  @Field("pass") String pass);

    @FormUrlEncoded
    @POST("banner.php")
    Call<List<Slider>> getSlider(@Field("status") Integer status);

}
