package com.example.ditenun.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Tenun extends RealmObject {

    @PrimaryKey
    @SerializedName("id_tenun")
    private String id;
    @SerializedName("nama_tenun")
    private String namaTenun;
    @SerializedName("deskripsi_tenun")
    private String deskripsiTenun;
    @SerializedName("sejarah_tenun")
    private String sejarahTenun;
    @SerializedName("kegunaan_tenun")
    private String kegunaanTenun;
    @SerializedName("warna_tenun")
    private String warnaTenun;
    @SerializedName("asal_tenun")
    private String asalTenun;
    @SerializedName("img_src")
    private String imageSrc;
    @SerializedName("nama_motif")
    private String namaMotif;

    public String getNamaMotif() {
        return namaMotif;
    }

    public void setNamaMotif(String namaMotif) {
        this.namaMotif = namaMotif;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaTenun() {
        return namaTenun;
    }

    public void setNamaTenun(String namaTenun) {
        this.namaTenun = namaTenun;
    }

    public String getDeskripsiTenun() {
        return deskripsiTenun;
    }

    public void setDeskripsiTenun(String deskripsiTenun) {
        this.deskripsiTenun = deskripsiTenun;
    }

    public String getSejarahTenun() {
        return sejarahTenun;
    }

    public void setSejarahTenun(String sejarahTenun) {
        this.sejarahTenun = sejarahTenun;
    }

    public String getKegunaanTenun() {
        return kegunaanTenun;
    }

    public void setKegunaanTenun(String kegunaanTenun) {
        this.kegunaanTenun = kegunaanTenun;
    }

    public String getWarnaTenun() {
        return warnaTenun;
    }

    public void setWarnaTenun(String warnaTenun) {
        this.warnaTenun = warnaTenun;
    }

    public String getAsalTenun() {
        return asalTenun;
    }

    public void setAsalTenun(String asalTenun) {
        this.asalTenun = asalTenun;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

}
