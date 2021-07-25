package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Order implements Parcelable {

    private List<Product> product = new ArrayList<>();
    private String orderNo;
    private String orderDate;
    private String address;
    private String orderStatusCode;
    private String orderStatus;
    private String paymentStatus;
    private String paymentStatusCode;
    private ShippingStatus shippingStatus;

    @SerializedName("id")
    @Expose
    private Integer oderId;

    @SerializedName("date_created")
    @Expose
    private String createdDate;

    @SerializedName("customer_id")
    @Expose
    private Integer customerId;

    @SerializedName("total")
    @Expose
    private Double totalPrice;

    @SerializedName("shipping")
    @Expose
    private Shipping shipping;

    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;

    @SerializedName("payment_method_title")
    @Expose
    private String paymentMethodTitle;

    @SerializedName("line_items")
    @Expose
    private List<Product> productList;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("date_paid")
    @Expose
    private String datePaid;

    @SerializedName("discount_total")
    @Expose
    private Double totalDiscount;

    public Order() {
    }

    protected Order(Parcel in) {
        product = in.createTypedArrayList(Product.CREATOR);
        orderNo = in.readString();
        orderDate = in.readString();
        address = in.readString();
        orderStatusCode = in.readString();
        orderStatus = in.readString();
        paymentStatus = in.readString();
        paymentStatusCode = in.readString();
        shippingStatus = in.readParcelable(ShippingStatus.class.getClassLoader());
        if (in.readByte() == 0) {
            oderId = null;
        } else {
            oderId = in.readInt();
        }
        createdDate = in.readString();
        if (in.readByte() == 0) {
            customerId = null;
        } else {
            customerId = in.readInt();
        }
        if (in.readByte() == 0) {
            totalPrice = null;
        } else {
            totalPrice = in.readDouble();
        }
        shipping = in.readParcelable(Shipping.class.getClassLoader());
        paymentMethod = in.readString();
        paymentMethodTitle = in.readString();
        productList = in.createTypedArrayList(Product.CREATOR);
        status = in.readString();
        datePaid = in.readString();
        if (in.readByte() == 0) {
            totalDiscount = null;
        } else {
            totalDiscount = in.readDouble();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(product);
        dest.writeString(orderNo);
        dest.writeString(orderDate);
        dest.writeString(address);
        dest.writeString(orderStatusCode);
        dest.writeString(orderStatus);
        dest.writeString(paymentStatus);
        dest.writeString(paymentStatusCode);
        dest.writeParcelable(shippingStatus, flags);
        if (oderId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(oderId);
        }
        dest.writeString(createdDate);
        if (customerId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(customerId);
        }
        if (totalPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(totalPrice);
        }
        dest.writeParcelable(shipping, flags);
        dest.writeString(paymentMethod);
        dest.writeString(paymentMethodTitle);
        dest.writeTypedList(productList);
        dest.writeString(status);
        dest.writeString(datePaid);
        if (totalDiscount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(totalDiscount);
        }
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

    public Integer getOderId() {
        return oderId;
    }

    public void setOderId(Integer oderId) {
        this.oderId = oderId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethodTitle() {
        return paymentMethodTitle;
    }

    public void setPaymentMethodTitle(String paymentMethodTitle) {
        this.paymentMethodTitle = paymentMethodTitle;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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

    public void addProduct(Product product) {
        this.product.add(product);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}
