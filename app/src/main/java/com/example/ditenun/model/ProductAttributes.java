package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductAttributes implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("position")
    private Integer position;

    @SerializedName("visible")
    private Boolean visible;

    @SerializedName("variation")
    private Boolean variation;

    @SerializedName("options")
    private List<String> options;

    public ProductAttributes() {
    }

    protected ProductAttributes(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            position = null;
        } else {
            position = in.readInt();
        }
        byte tmpVisible = in.readByte();
        visible = tmpVisible == 0 ? null : tmpVisible == 1;
        byte tmpVariation = in.readByte();
        variation = tmpVariation == 0 ? null : tmpVariation == 1;
        options = in.createStringArrayList();
    }

    public static final Creator<ProductAttributes> CREATOR = new Creator<ProductAttributes>() {
        @Override
        public ProductAttributes createFromParcel(Parcel in) {
            return new ProductAttributes(in);
        }

        @Override
        public ProductAttributes[] newArray(int size) {
            return new ProductAttributes[size];
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
        parcel.writeString(name);
        if (position == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(position);
        }
        parcel.writeByte((byte) (visible == null ? 0 : visible ? 1 : 2));
        parcel.writeByte((byte) (variation == null ? 0 : variation ? 1 : 2));
        parcel.writeStringList(options);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getVariation() {
        return variation;
    }

    public void setVariation(Boolean variation) {
        this.variation = variation;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

}
