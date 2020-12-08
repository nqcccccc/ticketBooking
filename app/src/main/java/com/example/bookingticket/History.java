package com.example.bookingticket;

public class History {
    private String movie_name;
    private String seat;
    private String date;
    private String img;

    public History(String movie_name, String seat, String date,String img) {
        this.movie_name = movie_name;
        this.seat = seat;
        this.date = date;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
