package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("permalink")
    @Expose
    private String permalink;

    @SerializedName("date_created")
    @Expose
    private String dateCreated;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("catalog_visibility")
    @Expose
    private String catalogVisibility;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("short_description")
    @Expose
    private String shortDescription;

    @SerializedName("sku")
    @Expose
    private String sku;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("regular_price")
    @Expose
    private String regularPrice;

    @SerializedName("sale_price")
    @Expose
    private String salePrice;

    @SerializedName("on_sale")
    @Expose
    private Boolean onSale;

    @SerializedName("purchasable")
    @Expose
    private Boolean purchasable;

    @SerializedName("stock_quantity")
    @Expose
    private Integer stockQuantity;

    @SerializedName("stock_status")
    @Expose
    private String stockStatus;

    @SerializedName("weight")
    @Expose
    private String weight;

    @SerializedName("dimensions")
    @Expose
    private ProductDimension dimensions;

    @SerializedName("images")
    @Expose
    private List<ProductImages> images;

    @SerializedName("attributes")
    @Expose
    private List<ProductAttributes> attributes;

    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    @SerializedName("subtotal")
    @Expose
    private Double subtotal;

    private int purchasedStock;

    public Product() {
    }

    protected Product(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        slug = in.readString();
        permalink = in.readString();
        dateCreated = in.readString();
        status = in.readString();
        catalogVisibility = in.readString();
        description = in.readString();
        shortDescription = in.readString();
        sku = in.readString();
        price = in.readString();
        regularPrice = in.readString();
        salePrice = in.readString();
        byte tmpOnSale = in.readByte();
        onSale = tmpOnSale == 0 ? null : tmpOnSale == 1;
        byte tmpPurchasable = in.readByte();
        purchasable = tmpPurchasable == 0 ? null : tmpPurchasable == 1;
        if (in.readByte() == 0) {
            stockQuantity = null;
        } else {
            stockQuantity = in.readInt();
        }
        stockStatus = in.readString();
        weight = in.readString();
        dimensions = in.readParcelable(ProductDimension.class.getClassLoader());
        images = in.createTypedArrayList(ProductImages.CREATOR);
        attributes = in.createTypedArrayList(ProductAttributes.CREATOR);
        purchasedStock = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(slug);
        dest.writeString(permalink);
        dest.writeString(dateCreated);
        dest.writeString(status);
        dest.writeString(catalogVisibility);
        dest.writeString(description);
        dest.writeString(shortDescription);
        dest.writeString(sku);
        dest.writeString(price);
        dest.writeString(regularPrice);
        dest.writeString(salePrice);
        dest.writeByte((byte) (onSale == null ? 0 : onSale ? 1 : 2));
        dest.writeByte((byte) (purchasable == null ? 0 : purchasable ? 1 : 2));
        if (stockQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(stockQuantity);
        }
        dest.writeString(stockStatus);
        dest.writeString(weight);
        dest.writeParcelable(dimensions, flags);
        dest.writeTypedList(images);
        dest.writeTypedList(attributes);
        dest.writeInt(purchasedStock);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCatalogVisibility() {
        return catalogVisibility;
    }

    public void setCatalogVisibility(String catalogVisibility) {
        this.catalogVisibility = catalogVisibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    public Boolean getPurchasable() {
        return purchasable;
    }

    public void setPurchasable(Boolean purchasable) {
        this.purchasable = purchasable;
    }

    public Integer getStockQuantity() {
        if (stockQuantity != null) {
            return stockQuantity;
        }
        return 0;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public ProductDimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(ProductDimension dimensions) {
        this.dimensions = dimensions;
    }

    public List<ProductImages> getImages() {
        return images;
    }

    public void setImages(List<ProductImages> images) {
        this.images = images;
    }

    public List<ProductAttributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductAttributes> attributes) {
        this.attributes = attributes;
    }

    public int getPurchasedStock() {
        return purchasedStock;
    }

    public void setPurchasedStock(int purchasedStock) {
        this.purchasedStock = purchasedStock;
    }

    public Double getPriceInDouble() {
        if (price != null && !price.isEmpty()) {
            return Double.valueOf(price);
        }
        return 0.0;
    }

    public Double getRegularPriceInDouble() {
        return Double.valueOf(regularPrice);
    }

    public Double getSalePriceInDouble() {
        return Double.parseDouble(salePrice);
    }

}
