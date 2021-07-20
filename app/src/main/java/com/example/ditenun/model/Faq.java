package com.example.ditenun.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Faq extends RealmObject {

    @PrimaryKey
    private String id;

    @SerializedName("judul")
    private String judul;

    @SerializedName("deskripsi")
    private String deskripsi;

    public Faq() {
    }

    public Faq(String id, String judul, String deskripsi) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
    }

    public Faq(String judul, String deskripsi) {
        this.judul = judul;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

}
