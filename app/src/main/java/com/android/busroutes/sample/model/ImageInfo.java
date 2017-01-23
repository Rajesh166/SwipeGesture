package com.android.busroutes.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.busroutes.sample.util.ParcelUtils;


public class ImageInfo implements Parcelable {

    private Integer id;
    private String name;
    private String description;
    private String image;

    public ImageInfo() {
    }

    public ImageInfo(Parcel dest) {
        readFromParcel(dest);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void readFromParcel(Parcel dest) {
        id = ParcelUtils.readInteger(dest);
        name = ParcelUtils.readString(dest);
        description = ParcelUtils.readString(dest);
        image = ParcelUtils.readString(dest);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeInteger(dest, id);
        ParcelUtils.writeString(dest, name);
        ParcelUtils.writeString(dest, description);
        ParcelUtils.writeString(dest, image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ImageInfo> CREATOR = new Parcelable.Creator<ImageInfo>() {
        @Override
        public ImageInfo createFromParcel(Parcel source) {
            return new ImageInfo(source);
        }

        @Override
        public ImageInfo[] newArray(int size) {
            return new ImageInfo[size];
        }
    };
}
