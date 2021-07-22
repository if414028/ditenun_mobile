package com.example.ditenun.activity.commerce.order.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;

import com.example.ditenun.R;
import com.example.ditenun.databinding.ActivityDetailOrderBinding;
import com.example.ditenun.databinding.ItemReviewPaymentProductBinding;
import com.example.ditenun.model.Product;
import com.example.ditenun.utility.SimpleRecyclerAdapter;
import com.example.ditenun.utility.TextUtility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class DetailOrderActivity extends AppCompatActivity {

    private ActivityDetailOrderBinding binding;
    private DetailOrderViewModel viewModel;

    private SimpleRecyclerAdapter<Product> productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_order);
        viewModel = ViewModelProviders.of(this).get(DetailOrderViewModel.class);

        initLayout();
        observeLiveData();
        getAdditionalData();
    }

    private void getAdditionalData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("order")) {
                viewModel.setOrder(intent.getParcelableExtra("order"));
            }
        }
    }

    private void initLayout() {
        binding.btnBack.setOnClickListener(view -> onBackPressed());
        initProductRecyclerView();
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

    private void observeLiveData() {
        viewModel.getSuccessGetDetailOrder().observe(this, aVoid -> {
            productAdapter.setMainData(viewModel.getOrder().getProduct());
            productAdapter.notifyDataSetChanged();

            binding.tvOrderNumberValue.setText(viewModel.getOrder().getOrderNo());
            binding.tvOrderDateValue.setText(getReadableDateFormat(Long.parseLong(viewModel.getOrder().getOrderDate())));
            binding.tvPaymentStatusValue.setText(viewModel.getOrder().getPaymentStatus());
            binding.tvPaymentMethodValue.setText(viewModel.getOrder().getPaymentMethod().getPaymentName());
        });
    }

    private String getReadableDateFormat(long timestamp) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(timestamp);
        String date = DateFormat.format("dd MMM yyyy", cal).toString();
        return date;
    }
}