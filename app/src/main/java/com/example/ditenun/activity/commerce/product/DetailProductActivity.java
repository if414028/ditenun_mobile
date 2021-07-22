package com.example.ditenun.activity.commerce.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;

import com.bumptech.glide.request.RequestOptions;
import com.example.ditenun.R;
import com.example.ditenun.databinding.ActivityDetailProductBinding;
import com.example.ditenun.model.ProductImages;
import com.example.ditenun.utility.TextUtility;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.DefaultSliderView;

public class DetailProductActivity extends AppCompatActivity {

    private ActivityDetailProductBinding binding;
    private DetailProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_product);
        viewModel = ViewModelProviders.of(this).get(DetailProductViewModel.class);

        initLayout();
        observeLiveData();
        getAdditionalData();
    }

    private void getAdditionalData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("product_id")) {
                viewModel.fetchDetailProduct(intent.getIntExtra("product_id", 0));
            }
        }

    }

    private void initLayout() {
        binding.setIsLoaded(false);
        binding.setIsSuccess(true);
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnBuy.setOnClickListener(v -> {
            orderProduct();
        });
    }

    private void observeLiveData() {
        viewModel.getSuccessGetDetailProduct().observe(this, aVoid -> {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.centerCrop();
            binding.slider.removeAllSliders();
            for (ProductImages imageUrl : viewModel.getProduct().getImages()) {
                if (imageUrl != null) {
                    DefaultSliderView sliderView = new DefaultSliderView(this);
                    sliderView
                            .image(imageUrl.getSrc())
                            .setRequestOption(requestOptions)
                            .setProgressBarVisible(true);

                    binding.slider.addSlider(sliderView);
                }
            }
            binding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            binding.slider.setDuration(4000);
            binding.tvProductNameAppBar.setText(viewModel.getProduct().getName());
            binding.tvProductName.setText(viewModel.getProduct().getName());
            binding.tvProductPrice.setText(TextUtility.getInstance().formatToRp(viewModel.getProduct().getPriceInDouble()));
            binding.tvProductDescription.setText(Html.fromHtml(viewModel.getProduct().getShortDescription()));
            binding.setIsSuccess(true);
            binding.setIsLoaded(true);
        });

        viewModel.getErrorGetDetailProduct().observe(this, aVoid -> {
            binding.setIsSuccess(false);
            binding.setIsLoaded(true);
        });
    }

    private void orderProduct() {
        Uri uri = Uri.parse(viewModel.getProduct().getPermalink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}