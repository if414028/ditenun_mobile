package com.example.ditenun.activity.commerce.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.ditenun.R;
import com.example.ditenun.databinding.ItemNewArrivalsBinding;
import com.example.ditenun.databinding.ProductHomeFragmentBinding;
import com.example.ditenun.model.Category;
import com.example.ditenun.model.Product;
import com.example.ditenun.utility.SimpleRecyclerAdapter;
import com.example.ditenun.utility.TextUtility;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.DefaultSliderView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProductHomeFragment extends Fragment {

    private ProductHomeViewModel mViewModel;
    private ProductHomeFragmentBinding binding;

    private SimpleRecyclerAdapter<Product> newArrivalsAdapter;
    private SimpleRecyclerAdapter<Category> categoryAdapter;

    private Integer pageNo = 1;

    public static ProductHomeFragment newInstance() {
        return new ProductHomeFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(ProductHomeViewModel.class);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.product_home_fragment, container, false);

        initLayout();
        observeLiveEvent();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.clearProductList();
        mViewModel.fetchAllProduct(pageNo);
        mViewModel.fetchListCategories();
    }

    private void initLayout() {
        initImageSlider();
        initNewArrivalsRecyclerView();
    }

    private void initImageSlider() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.fitCenter();
        List<String> imageList = new ArrayList<>();
        imageList.add("https://ditenun.com/wp-content/uploads/2021/07/Hero-Promo-Minggu-1-July-2021.png");
        imageList.add("https://ditenun.com/wp-content/uploads/2020/12/Banner-Home-landing-page-1-.png");
        imageList.add("https://ditenun.com/wp-content/uploads/2020/12/Banner-Home-landing-page-2-.png");
        imageList.add("https://ditenun.com/wp-content/uploads/2020/12/Banner-Home-landing-page-3-.png");
        imageList.add("https://ditenun.com/wp-content/uploads/2020/12/Banner-Home-landing-page-4-.png");
        imageList.add("https://ditenun.com/wp-content/uploads/2020/12/Banner-Home-landing-page-5-.png");


        for (int i = 0; i < imageList.size(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            sliderView
                    .image(imageList.get(i))
                    .setRequestOption(requestOptions)
                    .setProgressBarVisible(true);

            binding.slider.addSlider(sliderView);
        }

        binding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        binding.slider.setDuration(4000);

    }

    private void initNewArrivalsRecyclerView() {
        binding.rvNewArrivals.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        newArrivalsAdapter = new SimpleRecyclerAdapter<>(new ArrayList<>(), R.layout.item_new_arrivals, (holder, item) -> {
            ItemNewArrivalsBinding itemBinding = (ItemNewArrivalsBinding) holder.getLayoutBinding();
            if (item != null) {
                if (item.getImages() != null) {
                    Picasso.with(getContext()).load(item.getImages().get(0).getSrc()).into(itemBinding.imgNewArrivals);
                }
            }
            itemBinding.tvProductName.setText(item.getName());
            itemBinding.tvProductPrice.setText(TextUtility.getInstance().formatToRp(item.getPriceInDouble()));
            itemBinding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), DetailProductActivity.class);
                intent.putExtra("product_id", item.getId());
                intent.putExtra("product", item);
                startActivity(intent);
            });
        });
        binding.rvNewArrivals.setAdapter(newArrivalsAdapter);
    }

    private void observeLiveEvent() {
        mViewModel.getSuccessGetListProductEvent().observe(this, aVoid -> {
            newArrivalsAdapter.setMainData(mViewModel.getNewArrivalsProductList());
            newArrivalsAdapter.notifyDataSetChanged();
            binding.progressBar.setVisibility(View.GONE);
        });
        mViewModel.getErrorGetListProductEvent().observe(this, databaseError -> {
            binding.progressBar.setVisibility(View.GONE);
        });
    }
}
