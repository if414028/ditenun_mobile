package com.example.ditenun.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class UserConfiguration {

    private static final UserConfiguration instance = new UserConfiguration();

    public static final String SP_NAME = "DitenunAppSharedPreferences";
    public static final String USERNAME = "userName";
    public static final String TOKEN = "token";
    public static final String EMAIL = "email";
    public static final String NICKNAME = "nickname";
    public static final String LOGGED_IN = "loggedIn";
    public static final String USER_ID = "user_id";

    private SharedPreferences sharedPreferences;

    public static UserConfiguration getInstance() {
        return instance;
    }

    public void initSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public void setUsername(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, username);
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(USERNAME, null);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, null);
    }

    public void setEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL, null);
    }

    public void setLoggedIn(Boolean isLoggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public Boolean isLoggedId() {
        return sharedPreferences.getBoolean(LOGGED_IN, false);
    }

    public void setNickname(String nickname) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NICKNAME, nickname);
        editor.apply();
    }

    public String getNickname() {
        return sharedPreferences.getString(NICKNAME, null);
    }

    public void setUserId(Integer userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_ID, userId);
        editor.apply();
    }

    public Integer getCustomerId() {
        return sharedPreferences.getInt(USER_ID, 0);
    }

}
