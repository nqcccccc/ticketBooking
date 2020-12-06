package com.example.bookingticket;

public class ItemHistory {
    public String mName, cinema, time, seat, ticketID ;

    public ItemHistory(String mName, String cinema, String time, String seat, String ticketID) {
        this.mName = mName;
        this.cinema = cinema;
        this.time = time;
        this.seat = seat;
        this.ticketID = ticketID;

    }

    public String getMName() {
        return mName;
    }
    public void setMName(String mName) {
        this.mName = mName;
    }


    public String getCinema() {
        return cinema;
    }
    public void setCinema(String cinema) {
        this.cinema = cinema;
    }


    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }


    public String getSeat() {
        return seat;
    }
    public void setSeat(String seat) {
        this.seat = seat;
    }


    public String getTicketID() {
        return ticketID;
    }
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }
}

