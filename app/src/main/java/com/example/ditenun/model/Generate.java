package com.example.ditenun.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Generate extends RealmObject {

    @PrimaryKey
    private String id;

    @SerializedName("idMotif")
    private String idMotif;

    @SerializedName("generateFile")
    private String generateFile;

    @SerializedName("nama_generate")
    private String namaGenerate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMotif() {
        return idMotif;
    }

    public void setIdMotif(String idMotif) {
        this.idMotif = idMotif;
    }

    public String getGenerateFile() {
        return generateFile;
    }

    public void setGenerateFile(String generateFile) {
        this.generateFile = generateFile;
    }

    public String getNamaGenerate() {
        return namaGenerate;
    }

    public void setNamaGenerate(String namaGenerate) {
        this.namaGenerate = namaGenerate;
    }

}
