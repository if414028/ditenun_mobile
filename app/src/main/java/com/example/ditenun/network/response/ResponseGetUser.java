package com.example.ditenun.network.response;

import com.example.ditenun.model.Pagination;
import com.example.ditenun.model.User;

import java.util.List;

public class ResponseGetUser {

    private boolean error;
    private String message;
    private List<User> data;
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

    public void setData(List<User> data) {
        this.data = data;
    }

    public List<User> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
