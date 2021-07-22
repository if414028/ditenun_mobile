package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Shipping implements Parcelable {

    private Integer id;
    private String courierName;
    private Double shipPrice;
    private boolean isSelected;

    public Shipping() {
    }

    protected Shipping(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        courierName = in.readString();
        if (in.readByte() == 0) {
            shipPrice = null;
        } else {
            shipPrice = in.readDouble();
        }
        isSelected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(courierName);
        if (shipPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(shipPrice);
        }
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Shipping> CREATOR = new Creator<Shipping>() {
        @Override
        public Shipping createFromParcel(Parcel in) {
            return new Shipping(in);
        }

        @Override
        public Shipping[] newArray(int size) {
            return new Shipping[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public Double getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(Double shipPrice) {
        this.shipPrice = shipPrice;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
