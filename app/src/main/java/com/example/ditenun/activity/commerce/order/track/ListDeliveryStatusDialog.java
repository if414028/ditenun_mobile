package com.example.ditenun.activity.commerce.order.track;

import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ditenun.R;
import com.example.ditenun.databinding.DialogDeliveryStatusListBinding;
import com.example.ditenun.databinding.ItemDeliveryStatusBinding;
import com.example.ditenun.model.Status;
import com.example.ditenun.utility.SimpleRecyclerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ListDeliveryStatusDialog extends DialogFragment {

    private TrackOrderViewModel viewModel;
    private DialogDeliveryStatusListBinding binding;

    private SimpleRecyclerAdapter<Status> deliveryStatusAdapter;

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.filterDialogTheme);
        viewModel = ViewModelProviders.of(getActivity()).get(TrackOrderViewModel.class);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_delivery_status_list, container, false);

        initLayout();

        return binding.getRoot();
    }

    private void initLayout() {
        binding.btnBack.setOnClickListener(view -> dismiss());
        binding.tvShipperNameValue.setText(viewModel.getOrder().getShippingStatus().getShipperName());
        binding.tvOrderNumberValue.setText(viewModel.getOrder().getOrderNo());

        initDeliveryStatusRecyclerView();
    }

    private void initDeliveryStatusRecyclerView() {
        binding.rvDeliveryStatus.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        deliveryStatusAdapter = new SimpleRecyclerAdapter<>(new ArrayList<>(), R.layout.item_delivery_status, (holder, item) -> {

            ItemDeliveryStatusBinding itemBinding = (ItemDeliveryStatusBinding) holder.getLayoutBinding();
            itemBinding.tvStatus.setText(item.getStatusName());
            itemBinding.tvStatusDate.setText(getReadableDateFormat(Long.parseLong(item.getStatusDate())));

        });
        binding.rvDeliveryStatus.setAdapter(deliveryStatusAdapter);
        deliveryStatusAdapter.setMainData(viewModel.getOrder().getShippingStatus().getShippingStatuses());
        deliveryStatusAdapter.notifyDataSetChanged();
    }

    private String getReadableDateFormat(long timestamp) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(timestamp);
        String date = DateFormat.format("dd MMM yyyy", cal).toString();
        return date;
    }
}
