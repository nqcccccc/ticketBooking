package com.example.bookingticket.Retrofit2;

public class APIUtils {
    public static final String baseUrl = "http://192.168.0.15/bookingticket/";

    public static DataClient getData(){
        return RetrofitClient.getClient(baseUrl).create(DataClient.class);
    }
}