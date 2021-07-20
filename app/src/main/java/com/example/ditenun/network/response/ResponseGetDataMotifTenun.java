package com.example.ditenun.network.response;

import com.example.ditenun.model.MotifTenun;

public class ResponseGetDataMotifTenun {

    private boolean success;
    private String message;
    private MotifTenun motifTenun;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setMotifTenun(MotifTenun motifTenun) {
        this.motifTenun= motifTenun;
    }

    public MotifTenun getMotifTenun() {
        return motifTenun;
    }

}
