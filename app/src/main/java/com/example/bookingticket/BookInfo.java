package com.example.bookingticket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookInfo implements Parcelable {

@SerializedName("id_book")
@Expose
private String idBook;
@SerializedName("id_show")
@Expose
private String idShow;
@SerializedName("movie_name")
@Expose
private String movieName;
@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("seat")
@Expose
private String seat;
@SerializedName("date")
@Expose
private String date;
@SerializedName("qty")
@Expose
private String qty;
@SerializedName("qr")
@Expose
private String qr;

    protected BookInfo(Parcel in) {
        idBook = in.readString();
        idShow = in.readString();
        movieName = in.readString();
        userId = in.readString();
        seat = in.readString();
        date = in.readString();
        qty = in.readString();
        qr = in.readString();
    }

    public static final Creator<BookInfo> CREATOR = new Creator<BookInfo>() {
        @Override
        public BookInfo createFromParcel(Parcel in) {
            return new BookInfo(in);
        }

        @Override
        public BookInfo[] newArray(int size) {
            return new BookInfo[size];
        }
    };

    public String getIdBook() {
return idBook;
}

public void setIdBook(String idBook) {
this.idBook = idBook;
}

public String getIdShow() {
return idShow;
}

public void setIdShow(String idShow) {
this.idShow = idShow;
}

public String getMovieName() {
return movieName;
}

public void setMovieName(String movieName) {
this.movieName = movieName;
}

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
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

public String getQty() {
return qty;
}

public void setQty(String qty) {
this.qty = qty;
}

public String getQr() {
return qr;
}

public void setQr(String qr) {
this.qr = qr;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBook);
        dest.writeString(idShow);
        dest.writeString(movieName);
        dest.writeString(userId);
        dest.writeString(seat);
        dest.writeString(date);
        dest.writeString(qty);
        dest.writeString(qr);
    }
}