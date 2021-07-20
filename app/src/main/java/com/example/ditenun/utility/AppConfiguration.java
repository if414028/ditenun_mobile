package com.example.ditenun.utility;

public class AppConfiguration {

    public static final AppConfiguration instance = new AppConfiguration();

    public static AppConfiguration getInstance() {
        return instance;
    }

    private AppConfiguration() {
    }
}
