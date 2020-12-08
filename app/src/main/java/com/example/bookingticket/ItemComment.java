package com.example.bookingticket;

public class ItemComment {
    String comment;
    String ava;
    String name;
    Float rating;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public ItemComment(String comment, String ava, String name, Float rating) {
        this.comment = comment;
        this.ava = ava;
        this.name = name;
        this.rating = rating;
    }
}
