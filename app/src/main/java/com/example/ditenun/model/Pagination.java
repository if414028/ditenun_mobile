package com.example.ditenun.model;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("is_exist_next")
    private boolean isExistNext;

    @SerializedName("next_cursor")
    private int nextCursor;

    @SerializedName("size")
    private int size;

    public Pagination() {
        this.isExistNext = true;
        this.nextCursor = 0;
        this.size = 20;
    }

    public boolean isExistNext() {
        return this.isExistNext;
    }

    public void setExistNext(boolean isExistNext) {
        this.isExistNext = isExistNext;
    }

    public int getNextCursor() {
        return this.nextCursor;
    }

    public void setNextCursor(int nextCursor) {
        this.nextCursor = nextCursor;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
