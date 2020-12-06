package com.example.bookingticket.Retrofit2;

import com.example.bookingticket.Account;
import com.example.bookingticket.MoiveInfo;
import com.example.bookingticket.Poster;
import com.example.bookingticket.Show;
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

    @FormUrlEncoded
    @POST("poster.php")
    Call<List<Poster>> getPoster(@Field("status") Integer status);

    @FormUrlEncoded
    @POST("movie.php")
    Call<List<MoiveInfo>> getMoive(@Field("id") Integer id);

    @FormUrlEncoded
    @POST("userinfo.php")
    Call<List<Account>> getUser(@Field("id") Integer id);

    @FormUrlEncoded
    @POST("show.php")
    Call<List<Show>> getShow(@Field("date") String date,
                             @Field("id_movie") Integer id_movie);
}
