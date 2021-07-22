package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ShippingStatus implements Parcelable {

    private String shipperName;
    private String shippingNumber;
    private List<Status> shippingStatuses;

    public ShippingStatus(){}

    protected ShippingStatus(Parcel in) {
        shipperName = in.readString();
        shippingNumber = in.readString();
        shippingStatuses = in.createTypedArrayList(Status.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shipperName);
        dest.writeString(shippingNumber);
        dest.writeTypedList(shippingStatuses);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShippingStatus> CREATOR = new Creator<ShippingStatus>() {
        @Override
        public ShippingStatus createFromParcel(Parcel in) {
            return new ShippingStatus(in);
        }

        @Override
        public ShippingStatus[] newArray(int size) {
            return new ShippingStatus[size];
        }
    };

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShippingNumber() {
        return shippingNumber;
    }

    public void setShippingNumber(String shippingNumber) {
        this.shippingNumber = shippingNumber;
    }

    public List<Status> getShippingStatuses() {
        return shippingStatuses;
    }

    public void setShippingStatuses(List<Status> shippingStatuses) {
        this.shippingStatuses = shippingStatuses;
    }

}
