package com.example.bookingticket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoiveInfo implements Parcelable {

    @SerializedName("id_movie")
    @Expose
    private String idMovie;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("trailer")
    @Expose
    private String trailer;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("des")
    @Expose
    private String des;
    @SerializedName("id")
    @Expose
    private String id;

    protected MoiveInfo(Parcel in) {
        idMovie = in.readString();
        name = in.readString();
        trailer = in.readString();
        genre = in.readString();
        length = in.readString();
        date = in.readString();
        lang = in.readString();
        des = in.readString();
        id = in.readString();
    }

    public static final Creator<MoiveInfo> CREATOR = new Creator<MoiveInfo>() {
        @Override
        public MoiveInfo createFromParcel(Parcel in) {
            return new MoiveInfo(in);
        }

        @Override
        public MoiveInfo[] newArray(int size) {
            return new MoiveInfo[size];
        }
    };

    public String getIdMovie() {
    return idMovie;
    }

    public void setIdMovie(String idMovie) {
    this.idMovie = idMovie;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getTrailer() {
    return trailer;
    }

    public void setTrailer(String trailer) {
    this.trailer = trailer;
    }

    public String getGenre() {
    return genre;
    }

    public void setGenre(String genre) {
    this.genre = genre;
    }

    public String getLength() {
    return length;
    }

    public void setLength(String length) {
    this.length = length;
    }

    public String getDate() {
    return date;
    }

    public void setDate(String date) {
    this.date = date;
    }

    public String getLang() {
    return lang;
    }

    public void setLang(String lang) {
    this.lang = lang;
    }

    public String getDes() {
    return des;
    }

    public void setDes(String des) {
    this.des = des;
    }

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idMovie);
        dest.writeString(name);
        dest.writeString(trailer);
        dest.writeString(genre);
        dest.writeString(length);
        dest.writeString(date);
        dest.writeString(lang);
        dest.writeString(des);
        dest.writeString(id);
    }
}