package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductDimension implements Parcelable {

    @SerializedName("length")
    private String length;

    @SerializedName("width")
    private String width;

    @SerializedName("height")
    private String height;

    public ProductDimension() {
    }

    protected ProductDimension(Parcel in) {
        length = in.readString();
        width = in.readString();
        height = in.readString();
    }

    public static final Creator<ProductDimension> CREATOR = new Creator<ProductDimension>() {
        @Override
        public ProductDimension createFromParcel(Parcel in) {
            return new ProductDimension(in);
        }

        @Override
        public ProductDimension[] newArray(int size) {
            return new ProductDimension[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(length);
        parcel.writeString(width);
        parcel.writeString(height);
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

}
