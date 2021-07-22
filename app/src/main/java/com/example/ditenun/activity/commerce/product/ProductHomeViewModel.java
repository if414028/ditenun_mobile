package com.example.ditenun.activity.commerce.product;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.ditenun.model.Category;
import com.example.ditenun.model.Product;
import com.example.ditenun.network.WooCommerceApiClient;
import com.example.ditenun.network.WooCommerceNetworkInterface;
import com.example.ditenun.utility.SingleLiveEvent;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductHomeViewModel extends AndroidViewModel {

    private SingleLiveEvent<Void> successGetListProductEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> errorGetListProductEvent = new SingleLiveEvent<>();
    private List<Product> newArrivalsProductList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();

    private SingleLiveEvent<Void> successGetListCategories = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> errorGetListCategories = new SingleLiveEvent<>();

    public ProductHomeViewModel(@NonNull @NotNull Application application) {
        super(application);
    }


    public void fetchAllProduct() {
        WooCommerceNetworkInterface apiInterface = WooCommerceApiClient.createService(WooCommerceNetworkInterface.class, WooCommerceApiClient.CONSUMER_KEY, WooCommerceApiClient.CONSUMER_SECRET);
        Call<List<Product>> call = apiInterface.getListProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    newArrivalsProductList = response.body();
                    successGetListProductEvent.callFromBackgroundThread();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                errorGetListProductEvent.callFromBackgroundThread();
            }
        });
    }

    public void clearProductList() {
        this.newArrivalsProductList.clear();
    }

    public void fetchListCategories() {
        WooCommerceNetworkInterface apiInterface = WooCommerceApiClient.createService(WooCommerceNetworkInterface.class, WooCommerceApiClient.CONSUMER_KEY, WooCommerceApiClient.CONSUMER_SECRET);
        Call<List<Category>> call = apiInterface.getListCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    categoryList = response.body();
                    successGetListCategories.callFromBackgroundThread();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                errorGetListCategories.callFromBackgroundThread();
            }
        });
    }

    public SingleLiveEvent<Void> getSuccessGetListProductEvent() {
        return successGetListProductEvent;
    }

    public SingleLiveEvent<Void> getErrorGetListProductEvent() {
        return errorGetListProductEvent;
    }

    public List<Product> getNewArrivalsProductList() {
        return newArrivalsProductList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public SingleLiveEvent<Void> getSuccessGetListCategories() {
        return successGetListCategories;
    }

    public SingleLiveEvent<Void> getErrorGetListCategories() {
        return errorGetListCategories;
    }
}
