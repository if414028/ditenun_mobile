package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Order implements Parcelable {

    private List<Product> product = new ArrayList<>();
    private Shipping shipping;
    private PaymentMethod paymentMethod;
    private String orderNo;
    private String orderDate;
    private String address;
    private String orderStatusCode;
    private String orderStatus;
    private String paymentStatus;
    private String paymentStatusCode;
    private ShippingStatus shippingStatus;

    public Order() {
    }

    protected Order(Parcel in) {
        product = in.createTypedArrayList(Product.CREATOR);
        shipping = in.readParcelable(Shipping.class.getClassLoader());
        paymentMethod = in.readParcelable(PaymentMethod.class.getClassLoader());
        orderNo = in.readString();
        orderDate = in.readString();
        address = in.readString();
        orderStatusCode = in.readString();
        orderStatus = in.readString();
        paymentStatus = in.readString();
        paymentStatusCode = in.readString();
        shippingStatus = in.readParcelable(ShippingStatus.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(product);
        dest.writeParcelable(shipping, flags);
        dest.writeParcelable(paymentMethod, flags);
        dest.writeString(orderNo);
        dest.writeString(orderDate);
        dest.writeString(address);
        dest.writeString(orderStatusCode);
        dest.writeString(orderStatus);
        dest.writeString(paymentStatus);
        dest.writeString(paymentStatusCode);
        dest.writeParcelable(shippingStatus, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderStatusCode() {
        return orderStatusCode;
    }

    public void setOrderStatusCode(String orderStatusCode) {
        this.orderStatusCode = orderStatusCode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatusCode() {
        return paymentStatusCode;
    }

    public void setPaymentStatusCode(String paymentStatusCode) {
        this.paymentStatusCode = paymentStatusCode;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public void addProduct(Product product){
        this.product.add(product);
    }

}
