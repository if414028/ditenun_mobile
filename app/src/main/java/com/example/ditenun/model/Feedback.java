package com.example.ditenun.model;

import com.google.gson.annotations.SerializedName;

import io.realm.annotations.PrimaryKey;

public class Feedback {

    @PrimaryKey
    private String id;

    @SerializedName("subjek")
    private String subjek;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("rating")
    private Integer rating;

    @SerializedName("user_id")
    private Integer user_id;

    public Feedback(String subjek, String deskripsi, Integer rating) {
        this.subjek = subjek;
        this.deskripsi = deskripsi;
        this.rating = rating;
    }

    public Feedback(String id, String subjek, String deskripsi, Integer rating, Integer user_id) {

        this.id = id;
        this.subjek = subjek;
        this.deskripsi = deskripsi;
        this.rating = rating;
        this.user_id = user_id;
    }

    public Feedback() {

    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjek() {
        return subjek;
    }

    public void setSubjek(String subjek) {
        this.subjek = subjek;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
