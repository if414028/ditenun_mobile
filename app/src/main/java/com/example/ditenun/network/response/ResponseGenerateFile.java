package com.example.ditenun.network.response;

import com.example.ditenun.model.Generate;

import java.util.List;

public class ResponseGenerateFile {

    private boolean success;
    private String message;
    private List<Generate> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Generate> getData() {
        return data;
    }

    public void setData(List<Generate> data) {
        this.data = data;
    }

}
