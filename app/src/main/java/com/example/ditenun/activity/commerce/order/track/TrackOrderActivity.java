package com.example.ditenun.activity.commerce.order.track;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import com.example.ditenun.R;
import com.example.ditenun.databinding.ActivityTrackOrderBinding;
import com.example.ditenun.databinding.ItemReviewPaymentProductBinding;
import com.example.ditenun.model.Product;
import com.example.ditenun.utility.SimpleRecyclerAdapter;
import com.example.ditenun.utility.TextUtility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TrackOrderActivity extends AppCompatActivity {

    private ActivityTrackOrderBinding binding;
    private TrackOrderViewModel viewModel;

    private SimpleRecyclerAdapter<Product> productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_track_order);
        viewModel = ViewModelProviders.of(this).get(TrackOrderViewModel.class);

        initLayout();
        observeLiveData();
        getAdditionalData();
    }

    private void initLayout() {
        binding.btnBack.setOnClickListener(view -> onBackPressed());
        initProductRecyclerView();
        binding.btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                ListDeliveryStatusDialog listDeliveryStatusDialog = new ListDeliveryStatusDialog();
                listDeliveryStatusDialog.show(fragmentManager, "delivery_status_dialog");
            }
        });
    }

    private void getAdditionalData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("order")) {
                viewModel.setOrder(intent.getParcelableExtra("order"));
            }
        }
    }

    private void observeLiveData() {
        viewModel.getSuccessGetDetailOrder().observe(this, aVoid -> {
//            productAdapter.setMainData(viewModel.getOrder().getProduct());
//            productAdapter.notifyDataSetChanged();
//
//            binding.tvOrderNumberValue.setText(viewModel.getOrder().getOrderNo());
//            binding.tvOrderDateValue.setText(getReadableDateFormat(Long.parseLong(viewModel.getOrder().getOrderDate())));
//            binding.tvPaymentStatusValue.setText(viewModel.getOrder().getPaymentStatus());
//            binding.tvPaymentMethodValue.setText(viewModel.getOrder().getPaymentMethod().getPaymentName());
//
//            if (viewModel.getOrder().getShippingStatus() != null)
//                binding.tvDeliveryStatus.setText(getShippingStatus());
//            else binding.tvDeliveryStatus.setText(viewModel.getOrder().getPaymentStatus());
//
//            binding.btnTrack.setEnabled(viewModel.getOrder().getShippingStatus() != null);
        });
    }

    private String getShippingStatus() {
        String shippingStatusName = "";
        int lastShippingStatusPosition = 0;
        if (viewModel.getOrder().getShippingStatus() != null && viewModel.getOrder().getShippingStatus().getShippingStatuses().size() > 0) {
            lastShippingStatusPosition = viewModel.getOrder().getShippingStatus().getShippingStatuses().size() - 1;
            shippingStatusName = viewModel.getOrder().getShippingStatus().getShippingStatuses().get(lastShippingStatusPosition).getStatusName();
        }
        return shippingStatusName;
    }

    private void initProductRecyclerView() {
        binding.rvProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        productAdapter = new SimpleRecyclerAdapter<>(new ArrayList<>(), R.layout.item_review_payment_product, (holder, item) -> {
            ItemReviewPaymentProductBinding itemBinding = (ItemReviewPaymentProductBinding) holder.getLayoutBinding();

            itemBinding.tvProductName.setText(item.getName());
            itemBinding.tvQty.setText(String.format("Jumlah %s", TextUtility.getInstance().formatToRp(item.getPriceInDouble())));
            itemBinding.tvPrice.setText(String.format("%s / Unit", TextUtility.getInstance().formatToRp(item.getPriceInDouble() * item.getPurchasedStock())));
            Picasso.with(getApplicationContext()).load(item.getImages().get(0).getSrc()).into(itemBinding.imgProduct);
        });
        binding.rvProduct.setAdapter(productAdapter);
    }

    private String getReadableDateFormat(long timestamp) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(timestamp);
        String date = DateFormat.format("dd MMM yyyy", cal).toString();
        return date;
    }
}