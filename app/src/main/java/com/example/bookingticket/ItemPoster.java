package com.example.bookingticket;

public class ItemPoster {

    private String poster;
    private Integer id;


    public ItemPoster(String poster, Integer id) {
        this.poster = poster;
        this.id = id;
    }
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getId() {
        return id;
    }

    public void setId(String name) {
        this.id = id;
    }

}
