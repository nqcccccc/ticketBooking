package com.example.bookingticket;

public class ItemNews {
    public String tittle;
    public String img;

    public ItemNews(String tittle, String img) {
        this.tittle = tittle;
        this.img = img;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
