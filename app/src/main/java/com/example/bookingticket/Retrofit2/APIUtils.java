package com.example.bookingticket.Retrofit2;

public class APIUtils {
    public static final String baseUrl = "http://192.168.1.5/bookingticket/";

    public static DataClient getData(){
        return RetrofitClient.getClient(baseUrl).create(DataClient.class);
    }
}
