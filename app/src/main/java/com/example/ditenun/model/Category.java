package com.example.ditenun.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {

    @SerializedName("id")
    private Integer categoryId;

    @SerializedName("name")
    private String categoryName;

    @SerializedName("slug")
    private String slug;

    @SerializedName("parent")
    private Integer parent;

    @SerializedName("description")
    private String description;

    @SerializedName("display")
    private String display;

    @SerializedName("image")
    private String categoryImage;

    @SerializedName("menu_order")
    private Integer menuOrder;

    @SerializedName("count")
    private Integer count;

    public Category() {
    }

    protected Category(Parcel in) {
        if (in.readByte() == 0) {
            categoryId = null;
        } else {
            categoryId = in.readInt();
        }
        categoryName = in.readString();
        slug = in.readString();
        if (in.readByte() == 0) {
            parent = null;
        } else {
            parent = in.readInt();
        }
        description = in.readString();
        display = in.readString();
        categoryImage = in.readString();
        if (in.readByte() == 0) {
            menuOrder = null;
        } else {
            menuOrder = in.readInt();
        }
        if (in.readByte() == 0) {
            count = null;
        } else {
            count = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (categoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(categoryId);
        }
        dest.writeString(categoryName);
        dest.writeString(slug);
        if (parent == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(parent);
        }
        dest.writeString(description);
        dest.writeString(display);
        dest.writeString(categoryImage);
        if (menuOrder == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(menuOrder);
        }
        if (count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(count);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
