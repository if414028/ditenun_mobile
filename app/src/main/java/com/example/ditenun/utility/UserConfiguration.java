package com.example.ditenun.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class UserConfiguration {

    private static final UserConfiguration instance = new UserConfiguration();

    public static final String SP_NAME = "DitenunAppSharedPreferences";

    private SharedPreferences sharedPreferences;

    public static UserConfiguration getInstance() {
        return instance;
    }

    public void initSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

}
