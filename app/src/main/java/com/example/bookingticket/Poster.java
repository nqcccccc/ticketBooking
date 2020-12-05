package com.example.bookingticket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Poster implements Parcelable {

@SerializedName("id")
@Expose
private String id;
@SerializedName("poster")
@Expose
private String poster;
@SerializedName("status")
@Expose
private String status;

    protected Poster(Parcel in) {
        id = in.readString();
        poster = in.readString();
        status = in.readString();
    }

    public static final Creator<Poster> CREATOR = new Creator<Poster>() {
        @Override
        public Poster createFromParcel(Parcel in) {
            return new Poster(in);
        }

        @Override
        public Poster[] newArray(int size) {
            return new Poster[size];
        }
    };

    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getPoster() {
return poster;
}

public void setPoster(String poster) {
this.poster = poster;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(poster);
        dest.writeString(status);
    }
}