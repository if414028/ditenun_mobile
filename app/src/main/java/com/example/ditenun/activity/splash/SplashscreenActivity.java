package com.example.ditenun.activity.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ditenun.R;
import com.example.ditenun.activity.main.MainActivity;
import com.example.ditenun.databinding.ActivitySplashscreenBinding;
import com.example.ditenun.network.DitenunApiClient;
import com.example.ditenun.network.DitenunNetworkInterface;
import com.example.ditenun.network.response.ResponseGenerateFile;
import com.example.ditenun.network.response.ResponseGetData;
import com.example.ditenun.network.response.ResponseGetDataMotif;
import com.example.ditenun.network.response.ResponseGetFaq;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashscreenActivity extends AppCompatActivity {

    private ActivitySplashscreenBinding binding;

    final static String DEFAULT_SIZE = "all";
    final static int DEFAULT_CURSOR = 0;

    private DitenunNetworkInterface networkInterface;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splashscreen);
        networkInterface = DitenunApiClient.createService(DitenunNetworkInterface.class);
        realm = Realm.getDefaultInstance();

        Objects.requireNonNull(getSupportActionBar()).hide();
        fetchData();
    }

    private void fetchData() {
        requestMotifFromServer();
    }

    private void requestMotifFromServer() {
        Call<ResponseGetDataMotif> getDataMotifCall = networkInterface.getListMotif(DitenunApiClient.ACCESS_TOKEN, DEFAULT_CURSOR, DEFAULT_SIZE);
        getDataMotifCall.enqueue(new Callback<ResponseGetDataMotif>() {
            @Override
            public void onResponse(Call<ResponseGetDataMotif> call, Response<ResponseGetDataMotif> response) {
                if (response.isSuccessful()) {
                    realm.beginTransaction();
                    realm.executeTransactionAsync(realm1 -> realm1.insertOrUpdate(response.body().getData()));
                    realm.commitTransaction();

                    requestGenerateMotifFromServer();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetDataMotif> call, Throwable t) {
                showErrorMessage(t.getMessage());
            }
        });
    }

    private void requestGenerateMotifFromServer() {
        Call<ResponseGenerateFile> getGeneratedFile = networkInterface.getListGenerate(DitenunApiClient.ACCESS_TOKEN, DEFAULT_CURSOR, DEFAULT_SIZE);
        getGeneratedFile.enqueue(new Callback<ResponseGenerateFile>() {
            @Override
            public void onResponse(Call<ResponseGenerateFile> call, Response<ResponseGenerateFile> response) {
                if (response.isSuccessful()) {
                    realm.beginTransaction();
                    realm.executeTransactionAsync(realm -> realm.insertOrUpdate(response.body().getData()));
                    realm.commitTransaction();
                    requestTenunFromServer();
                }
            }

            @Override
            public void onFailure(Call<ResponseGenerateFile> call, Throwable t) {
                showErrorMessage(t.getMessage());
            }
        });
    }

    private void requestTenunFromServer() {
        Call<ResponseGetData> getTenunData = networkInterface.getListTenun(DitenunApiClient.ACCESS_TOKEN, DEFAULT_CURSOR, DEFAULT_SIZE);
        getTenunData.enqueue(new Callback<ResponseGetData>() {
            @Override
            public void onResponse(Call<ResponseGetData> call, Response<ResponseGetData> response) {
                if (response.isSuccessful()) {
                    realm.beginTransaction();
                    realm.executeTransactionAsync(realm -> realm.insertOrUpdate(response.body().getData()));
                    realm.commitTransaction();
                    requestFaqFromServer();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetData> call, Throwable t) {
                showErrorMessage(t.getMessage());
            }
        });
    }

    private void requestFaqFromServer() {
        Call<ResponseGetFaq> getFaq = networkInterface.getListFaq(DitenunApiClient.ACCESS_TOKEN);
        getFaq.enqueue(new Callback<ResponseGetFaq>() {
            @Override
            public void onResponse(Call<ResponseGetFaq> call, Response<ResponseGetFaq> response) {
                if (response.isSuccessful()) {
                    realm.beginTransaction();
                    realm.executeTransactionAsync(realm -> realm.insertOrUpdate(response.body().getData()));
                    realm.commitTransaction();
                    startHomeActivity();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetFaq> call, Throwable t) {
                showErrorMessage(t.getMessage());
            }
        });
    }

    private void showErrorMessage(String message) {
        createSnackbar(getResources().getString(R.string.error) + ": " + message, true).show();
    }

    private Snackbar createSnackbar(String message, boolean isAllowedToContinue) {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_INDEFINITE);
        if (isAllowedToContinue) {
            snackbar.setAction(R.string.skip, v -> {
                startHomeActivity();
            });
        } else {
            snackbar.setAction(R.string.retry, v -> {
                fetchData();
            });
        }

        return snackbar;
    }

    private void startHomeActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}