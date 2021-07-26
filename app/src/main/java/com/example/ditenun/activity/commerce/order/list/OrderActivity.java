package com.example.ditenun.activity.commerce.order.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import com.example.ditenun.R;
import com.example.ditenun.activity.commerce.order.detail.DetailOrderActivity;
import com.example.ditenun.activity.commerce.order.track.TrackOrderActivity;
import com.example.ditenun.databinding.ActivityOrderBinding;
import com.example.ditenun.databinding.ItemMyOrderBinding;
import com.example.ditenun.databinding.ItemNewArrivalsBinding;
import com.example.ditenun.model.Order;
import com.example.ditenun.model.Product;
import com.example.ditenun.utility.SimpleRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding binding;
    private OrderViewModel viewModel;

    private SimpleRecyclerAdapter<Order> orderAdapter;
    private SimpleRecyclerAdapter<Product> productAdapter;

    private int pageNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order);
        viewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        initLayout();
        observeLiveData();
        viewModel.fetchOrders(pageNo);
    }

    private void initLayout() {
        binding.setIsEmpty(false);
        binding.setIsError(false);
        binding.btnBack.setOnClickListener(view -> onBackPressed());
        initOrderRecyclerView();
    }

    private void initOrderRecyclerView() {
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        orderAdapter = new SimpleRecyclerAdapter<>(new ArrayList<>(), R.layout.item_my_order, (holder, item) -> {
            ItemMyOrderBinding itemBinding = (ItemMyOrderBinding) holder.getLayoutBinding();

            itemBinding.tvOrderNumber.setText(String.format("Nomor Pesanan : %s", item.getOderId()));
            itemBinding.tvOrderDate.setText(parseApiDateFormat(item.getCreatedDate()));
            itemBinding.tvQty.setText(String.format("%d Produk", item.getProductList().size()));
            itemBinding.tvOrderStatus.setText(item.getStatus());

            itemBinding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), DetailOrderActivity.class);
                intent.putExtra(DetailOrderActivity.ARG_DETAIL_ORDER, item);
                startActivity(intent);
            });
        });
        binding.rvOrder.setAdapter(orderAdapter);
    }

    private String parseApiDateFormat(String dateString) {
        SimpleDateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.getDefault());
        SimpleDateFormat shortDateFormatWithName = new SimpleDateFormat("dd MMMM yyyy, HH.mm", Locale.getDefault());
        Date date;
        String outputDate = "-";

        if (dateString != null) {
            try {
                date = apiDateFormat.parse(dateString);
                outputDate = shortDateFormatWithName.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return outputDate;
    }

    private void observeLiveData() {
        viewModel.getSuccessGetOrders().observe(this, unused -> {
            binding.setIsLoaded(true);
            binding.setIsEmpty(false);
            binding.setIsError(false);
            orderAdapter.setMainData(viewModel.getOrderList());
            orderAdapter.notifyDataSetChanged();
        });

        viewModel.getEmptyGetOrders().observe(this, unused -> {
            if (orderAdapter.getItemCount() <= 0) {
                binding.setIsEmpty(true);
                binding.setIsLoaded(true);
                binding.setIsError(false);
            }
        });

        viewModel.getErrorGetOrders().observe(this, unused -> {
            if (orderAdapter.getItemCount() <= 0) {
                binding.setIsLoaded(true);
                binding.setIsEmpty(false);
                binding.setIsError(true);
            }
        });
    }
}