package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Status implements Parcelable {

    private String statusName;
    private String statusDate;

    public Status() {
    }

    protected Status(Parcel in) {
        statusName = in.readString();
        statusDate = in.readString();
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(statusName);
        parcel.writeString(statusDate);
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

}
