package com.example.bookingticket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News implements Parcelable {

@SerializedName("id")
@Expose
private String id;
@SerializedName("tittle")
@Expose
private String tittle;
@SerializedName("content")
@Expose
private String content;
@SerializedName("img")
@Expose
private String img;
@SerializedName("status")
@Expose
private String status;

    protected News(Parcel in) {
        id = in.readString();
        tittle = in.readString();
        content = in.readString();
        img = in.readString();
        status = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTittle() {
return tittle;
}

public void setTittle(String tittle) {
this.tittle = tittle;
}

public String getContent() {
return content;
}

public void setContent(String content) {
this.content = content;
}

public String getImg() {
return img;
}

public void setImg(String img) {
this.img = img;
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
        dest.writeString(tittle);
        dest.writeString(content);
        dest.writeString(img);
        dest.writeString(status);
    }
}