package com.example.ditenun.activity.commerce.order.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import com.example.ditenun.R;
import com.example.ditenun.databinding.ActivityDetailOrderBinding;
import com.example.ditenun.databinding.ItemReviewPaymentProductBinding;
import com.example.ditenun.model.Product;
import com.example.ditenun.utility.SimpleRecyclerAdapter;
import com.example.ditenun.utility.TextUtility;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailOrderActivity extends AppCompatActivity {

    public static final String ARG_DETAIL_ORDER = "detailOrder";

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
            if (intent.hasExtra(ARG_DETAIL_ORDER)) {
                viewModel.setOrder(intent.getParcelableExtra(ARG_DETAIL_ORDER));
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
            itemBinding.tvQty.setText(String.format("Jumlah: %s", item.getQuantity()));
            itemBinding.tvPrice.setText(TextUtility.getInstance().formatToRp(item.getSubtotal()));
        });
        binding.rvProduct.setAdapter(productAdapter);
    }

    private void observeLiveData() {
        viewModel.getSuccessGetDetailOrder().observe(this, aVoid -> {
            productAdapter.setMainData(viewModel.getOrder().getProductList());
            productAdapter.notifyDataSetChanged();

            binding.tvOrderNumberValue.setText(String.valueOf(viewModel.getOrder().getOderId()));
            binding.tvOrderDateValue.setText(parseApiDateFormat(viewModel.getOrder().getDatePaid()));
            binding.tvPaymentStatusValue.setText(viewModel.getOrder().getStatus());
            binding.tvPaymentMethodValue.setText(viewModel.getOrder().getPaymentMethodTitle());
            binding.tvTotalPrice.setText(TextUtility.getInstance().formatToRp(viewModel.getOrder().getTotalPrice()));
            if (viewModel.getOrder().getTotalDiscount() != null && viewModel.getOrder().getTotalDiscount() > 0) {
                binding.tvTotalDiscount.setText(TextUtility.getInstance().formatToRp(viewModel.getOrder().getTotalDiscount()));
                binding.tvTotalDiscount.setVisibility(View.VISIBLE);
                binding.tvTotalDiscountTitle.setVisibility(View.VISIBLE);
            } else {
                binding.tvTotalDiscount.setVisibility(View.GONE);
                binding.tvTotalDiscountTitle.setVisibility(View.GONE);
            }
        });
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
}