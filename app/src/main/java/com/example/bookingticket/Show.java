package com.example.bookingticket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Show implements Parcelable {

@SerializedName("id")
@Expose
private String id;
@SerializedName("date")
@Expose
private String date;
@SerializedName("time")
@Expose
private String time;
@SerializedName("id_movie")
@Expose
private String idMovie;

    protected Show(Parcel in) {
        id = in.readString();
        date = in.readString();
        time = in.readString();
        idMovie = in.readString();
    }

    public static final Creator<Show> CREATOR = new Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel in) {
            return new Show(in);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };

    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public String getIdMovie() {
return idMovie;
}

public void setIdMovie(String idMovie) {
this.idMovie = idMovie;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(idMovie);
    }
}