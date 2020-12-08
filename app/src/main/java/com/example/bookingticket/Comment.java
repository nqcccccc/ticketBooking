package com.example.bookingticket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment implements Parcelable {

@SerializedName("id")
@Expose
private String id;
@SerializedName("user_name")
@Expose
private String userName;
@SerializedName("user_ava")
@Expose
private String userAva;
@SerializedName("movie_id")
@Expose
private String movieId;
@SerializedName("comment")
@Expose
private String comment;
@SerializedName("rating")
@Expose
private String rating;

    protected Comment(Parcel in) {
        id = in.readString();
        userName = in.readString();
        userAva = in.readString();
        movieId = in.readString();
        comment = in.readString();
        rating = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public String getUserAva() {
return userAva;
}

public void setUserAva(String userAva) {
this.userAva = userAva;
}

public String getMovieId() {
return movieId;
}

public void setMovieId(String movieId) {
this.movieId = movieId;
}

public String getComment() {
return comment;
}

public void setComment(String comment) {
this.comment = comment;
}

public String getRating() {
return rating;
}

public void setRating(String rating) {
this.rating = rating;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userName);
        dest.writeString(userAva);
        dest.writeString(movieId);
        dest.writeString(comment);
        dest.writeString(rating);
    }
}