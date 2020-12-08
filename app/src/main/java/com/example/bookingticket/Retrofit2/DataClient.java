package com.example.bookingticket.Retrofit2;

import com.example.bookingticket.Account;
import com.example.bookingticket.BookInfo;
import com.example.bookingticket.Comment;
import com.example.bookingticket.MoiveInfo;
import com.example.bookingticket.News;
import com.example.bookingticket.Poster;
import com.example.bookingticket.Show;
import com.example.bookingticket.Slider;

import org.w3c.dom.Text;

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

    @FormUrlEncoded
    @POST("bookcheck.php")
    Call<List<BookInfo>> getBookInfo(@Field("id_show") Integer id_show);

    @FormUrlEncoded
    @POST("booking.php")
    Call<String> setBooking(@Field("id_show") Integer id_show,
                                  @Field("movie_name") String movie_name,
                                  @Field("user_id") Integer user_id,
                                  @Field("seat") String seat,
                                  @Field("date") String date,
                                  @Field("qty") Integer qty);

    @FormUrlEncoded
    @POST("bookcheckid.php")
    Call<List<BookInfo>> getBookID(@Field("user_id") Integer user_id);

    @FormUrlEncoded
    @POST("history.php")
    Call<List<Poster>> getHistoryPoster(@Field("name") String name);

    @FormUrlEncoded
    @POST("comment.php")
    Call<String> upComment(@Field("user_name") String user_name,
                            @Field("user_ava") String user_ava,
                            @Field("movie_id") Integer movie_id,
                            @Field("comment") String comment,
                            @Field("rating") float rating);

    @FormUrlEncoded
    @POST("getcmt.php")
    Call<List<Comment>> getComment(@Field("movie_id") Integer movie_id);

    @FormUrlEncoded
    @POST("news.php")
    Call<List<News>> getNews(@Field("status") Integer status);


}
