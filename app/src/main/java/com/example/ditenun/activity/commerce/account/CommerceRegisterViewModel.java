package com.example.ditenun.activity.commerce.account;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.ditenun.utility.SingleLiveEvent;
import com.example.ditenun.utility.UserConfiguration;

import org.jetbrains.annotations.NotNull;

public class CommerceRegisterViewModel extends AndroidViewModel {

    private SingleLiveEvent<Void> successRegister = new SingleLiveEvent<>();

    public CommerceRegisterViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void doRegister(String fullName, String username, String email, String password) {
        UserConfiguration.getInstance().setLoggedIn(true);
        UserConfiguration.getInstance().setEmail(email);
        UserConfiguration.getInstance().setNickname(fullName);
        UserConfiguration.getInstance().setUsername(username);
        UserConfiguration.getInstance().setUserId(8);

        successRegister.callFromBackgroundThread();
    }

    public SingleLiveEvent<Void> getSuccessRegister() {
        return successRegister;
    }
}
