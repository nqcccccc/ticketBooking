package com.example.bookingticket.Retrofit2;

public class APIUtils {
    public static final String baseUrl = "http://207.148.67.7/bookingticket/";

    public static DataClient getData(){
        return RetrofitClient.getClient(baseUrl).create(DataClient.class);
    }
}
