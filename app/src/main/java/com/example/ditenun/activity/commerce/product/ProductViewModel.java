package com.example.ditenun.activity.commerce.product;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

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

public class ProductViewModel extends AndroidViewModel {

    private SingleLiveEvent<Void> successGetListProductEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> errorGetListProductEvent = new SingleLiveEvent<>();
    private List<Product> productList = new ArrayList<>();

    public ProductViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void fetchListProduct(Integer pageNo) {
        WooCommerceNetworkInterface apiInterface = WooCommerceApiClient.createService(WooCommerceNetworkInterface.class, "");
        Call<List<Product>> call = apiInterface.getListProducts(pageNo, WooCommerceApiClient.CONSUMER_KEY, WooCommerceApiClient.CONSUMER_SECRET);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    productList = response.body();
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
        this.productList.clear();
    }

    public SingleLiveEvent<Void> getSuccessGetListProductEvent() {
        return successGetListProductEvent;
    }

    public SingleLiveEvent<Void> getErrorGetListProductEvent() {
        return errorGetListProductEvent;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
