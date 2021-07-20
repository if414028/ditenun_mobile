package com.example.ditenun.network.response;

import com.example.ditenun.model.Faq;
import com.example.ditenun.model.Pagination;

import java.util.List;

public class ResponseGetFaq {

    private boolean error;
    private String message;
    private List<Faq> data;
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

    public void setData(List<Faq> data) {
        this.data = data;
    }

    public List<Faq> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
