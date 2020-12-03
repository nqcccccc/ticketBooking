package com.example.bookingticket;

public class ItemNews {
    public String tittle;
    public int img;

    public ItemNews(String tittle, int img) {
        this.tittle = tittle;
        this.img = img;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
