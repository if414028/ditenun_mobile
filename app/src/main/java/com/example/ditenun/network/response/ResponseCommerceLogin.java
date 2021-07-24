package com.example.ditenun.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCommerceLogin {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("user_email")
    @Expose
    private String userEmail;

    @SerializedName("user_nicename")
    @Expose
    private String userNicename;

    @SerializedName("user_display_name")
    @Expose
    private String userDisplayName;

    @SerializedName("user_id")
    @Expose
    private Integer userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNicename() {
        return userNicename;
    }

    public void setUserNicename(String userNicename) {
        this.userNicename = userNicename;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
