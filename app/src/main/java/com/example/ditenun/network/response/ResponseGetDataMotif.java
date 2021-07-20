package com.example.ditenun.network.response;

import com.example.ditenun.model.MotifTenun;
import com.example.ditenun.model.Pagination;

import java.util.List;

public class ResponseGetDataMotif {

    private boolean error;
    private String message;
    private List<MotifTenun> data;
    private Pagination pagination;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MotifTenun> getData() {
        return data;
    }

    public void setData(List<MotifTenun> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
