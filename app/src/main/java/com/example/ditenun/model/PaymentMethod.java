package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentMethod implements Parcelable {

    private Integer id;
    private String paymentName;
    private String paymentDescription;
    private String paymentCategoryCode;
    private String paymentCategoryName;
    private boolean isSelected;

    public PaymentMethod() {
    }

    protected PaymentMethod(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        paymentName = in.readString();
        paymentDescription = in.readString();
        paymentCategoryCode = in.readString();
        paymentCategoryName = in.readString();
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
        dest.writeString(paymentName);
        dest.writeString(paymentDescription);
        dest.writeString(paymentCategoryCode);
        dest.writeString(paymentCategoryName);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PaymentMethod> CREATOR = new Creator<PaymentMethod>() {
        @Override
        public PaymentMethod createFromParcel(Parcel in) {
            return new PaymentMethod(in);
        }

        @Override
        public PaymentMethod[] newArray(int size) {
            return new PaymentMethod[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getPaymentCategoryCode() {
        return paymentCategoryCode;
    }

    public void setPaymentCategoryCode(String paymentCategoryCode) {
        this.paymentCategoryCode = paymentCategoryCode;
    }

    public String getPaymentCategoryName() {
        return paymentCategoryName;
    }

    public void setPaymentCategoryName(String paymentCategoryName) {
        this.paymentCategoryName = paymentCategoryName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
