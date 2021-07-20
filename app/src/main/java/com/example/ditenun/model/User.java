package com.example.ditenun.model;

import com.google.gson.annotations.SerializedName;

import io.realm.annotations.PrimaryKey;

public class User {

    @PrimaryKey
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("jenis_tenun")
    private String jenis_tenun;

    @SerializedName("no_hp")
    private String no_hp;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String email, String alamat, String jenis_tenun, String no_hp) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.alamat = alamat;
        this.jenis_tenun = jenis_tenun;
        this.no_hp = no_hp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_tenun() {
        return jenis_tenun;
    }

    public void setJenis_tenun(String jenis_tenun) {
        this.jenis_tenun = jenis_tenun;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

}
