package com.example.ditenun.activity.commerce.order.track;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.ditenun.model.Order;
import com.example.ditenun.utility.SingleLiveEvent;

import org.jetbrains.annotations.NotNull;

public class TrackOrderViewModel extends AndroidViewModel {

    private Order order;
    private SingleLiveEvent<Void> successGetDetailOrder = new SingleLiveEvent<>();

    public TrackOrderViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        successGetDetailOrder.callFromBackgroundThread();
    }

    public SingleLiveEvent<Void> getSuccessGetDetailOrder() {
        return successGetDetailOrder;
    }
}
