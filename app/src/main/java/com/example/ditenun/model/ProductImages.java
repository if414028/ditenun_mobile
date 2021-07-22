package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductImages implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("src")
    private String src;

    @SerializedName("name")
    private String name;

    @SerializedName("alt")
    private String alt;

    public ProductImages() {
    }

    protected ProductImages(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        src = in.readString();
        name = in.readString();
        alt = in.readString();
    }

    public static final Creator<ProductImages> CREATOR = new Creator<ProductImages>() {
        @Override
        public ProductImages createFromParcel(Parcel in) {
            return new ProductImages(in);
        }

        @Override
        public ProductImages[] newArray(int size) {
            return new ProductImages[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(src);
        parcel.writeString(name);
        parcel.writeString(alt);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

}
