package com.example.bookingticket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("avatar")
    @Expose
    private String avatar;

    protected Account(Parcel in) {
        id = in.readString();
        user = in.readString();
        pass = in.readString();
        email = in.readString();
        avatar = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public com.example.bookingticket.Account createFromParcel(Parcel in) {
            return new com.example.bookingticket.Account(in);
        }

        @Override
        public com.example.bookingticket.Account[] newArray(int size) {
            return new com.example.bookingticket.Account[size];
        }
    };

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getUser() {
    return user;
    }

    public void setUser(String user) {
    this.user = user;
    }

    public String getPass() {
    return pass;
    }

    public void setPass(String pass) {
    this.pass = pass;
    }

    public String getEmail() {
    return email;
    }

    public void setEmail(String email) {
    this.email = email;
    }

    public String getAvatar() {
    return avatar;
    }

    public void setAvatar(String avatar) {
    this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(user);
        dest.writeString(pass);
        dest.writeString(email);
        dest.writeString(avatar);
    }
}