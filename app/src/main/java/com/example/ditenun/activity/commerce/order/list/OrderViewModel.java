package com.example.ditenun.activity.commerce.order.list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.ditenun.model.Order;
import com.example.ditenun.network.WooCommerceApiClient;
import com.example.ditenun.network.WooCommerceNetworkInterface;
import com.example.ditenun.utility.SingleLiveEvent;
import com.example.ditenun.utility.UserConfiguration;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends AndroidViewModel {

    private SingleLiveEvent<Void> successGetOrders = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> emptyGetOrders = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> errorGetOrders = new SingleLiveEvent<>();

    private List<Order> orderList = new ArrayList<>();

    public OrderViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void fetchOrders(Integer page) {
        WooCommerceNetworkInterface networkInterface = WooCommerceApiClient.createService(WooCommerceNetworkInterface.class, "");
        Call<List<Order>> call = networkInterface.getListOrders(WooCommerceApiClient.CONSUMER_KEY, WooCommerceApiClient.CONSUMER_SECRET, 7, page);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        orderList.addAll(response.body());
                        successGetOrders.call();
                    } else {
                        emptyGetOrders.call();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                errorGetOrders.callFromBackgroundThread();
            }
        });
    }

    public SingleLiveEvent<Void> getSuccessGetOrders() {
        return successGetOrders;
    }

    public SingleLiveEvent<Void> getEmptyGetOrders() {
        return emptyGetOrders;
    }

    public SingleLiveEvent<Void> getErrorGetOrders() {
        return errorGetOrders;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
