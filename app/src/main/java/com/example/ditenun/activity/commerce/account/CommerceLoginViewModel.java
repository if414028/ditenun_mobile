package com.example.ditenun.activity.commerce.account;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.ditenun.network.WooCommerceApiClient;
import com.example.ditenun.network.WooCommerceNetworkInterface;
import com.example.ditenun.network.response.ResponseCommerceLogin;
import com.example.ditenun.network.response.ResponseGetUser;
import com.example.ditenun.utility.SingleLiveEvent;
import com.example.ditenun.utility.UserConfiguration;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommerceLoginViewModel extends AndroidViewModel {

    private SingleLiveEvent<Void> successLogin = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> errorLogin = new SingleLiveEvent<>();

    public CommerceLoginViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void doLogin(String username, String password) {
        WooCommerceNetworkInterface apiInterface = WooCommerceApiClient.createService(WooCommerceNetworkInterface.class, WooCommerceApiClient.CONSUMER_KEY, WooCommerceApiClient.CONSUMER_SECRET);
        Call<ResponseCommerceLogin> call = apiInterface.login("", username, password);
        call.enqueue(new Callback<ResponseCommerceLogin>() {
            @Override
            public void onResponse(Call<ResponseCommerceLogin> call, Response<ResponseCommerceLogin> response) {
                if (response.isSuccessful()) {
                    UserConfiguration.getInstance().setUsername(response.body().getUserDisplayName());
                    UserConfiguration.getInstance().setNickname(response.body().getUserNicename());
                    UserConfiguration.getInstance().setToken(response.body().getToken());
                    UserConfiguration.getInstance().setEmail(response.body().getUserEmail());
                    UserConfiguration.getInstance().setUserId(response.body().getUserId());
                    UserConfiguration.getInstance().setLoggedIn(true);
                    successLogin.callFromBackgroundThread();
                } else errorLogin.callFromBackgroundThread();
            }

            @Override
            public void onFailure(Call<ResponseCommerceLogin> call, Throwable t) {
                errorLogin.callFromBackgroundThread();
            }
        });
    }

    public SingleLiveEvent<Void> getSuccessLogin() {
        return successLogin;
    }

    public SingleLiveEvent<Void> getErrorLogin() {
        return errorLogin;
    }
}
