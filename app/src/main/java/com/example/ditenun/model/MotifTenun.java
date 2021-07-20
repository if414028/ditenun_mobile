package com.example.ditenun.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MotifTenun extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private String id;
    @SerializedName("id_tenun")
    private String idTenun;
    @SerializedName("nama_motif")
    private String namaMotif;
    @SerializedName("img_src")
    private String imageMotif;

    public String getIdTenun() {
        return idTenun;
    }

    public void setIdTenun(String idTenun) {
        this.idTenun = idTenun;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaMotif() {
        return namaMotif;
    }

    public void setNamaMotif(String namaMotif) {
        this.namaMotif = namaMotif;
    }

    public String getImageMotif() {
        return imageMotif;
    }

    public void setImageMotif(String imageMotif) {
        this.imageMotif = imageMotif;
    }

}
