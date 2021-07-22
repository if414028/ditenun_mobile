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
        mViewModel.fetchAllProduct();
        mViewModel.fetchListCategories();
    }

    private void initLayout() {
        initImageSlider();
        initNewArrivalsRecyclerView();
    }

    private void initImageSlider() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        List<String> imageList = new ArrayList<>();
        imageList.add("https://firebasestorage.googleapis.com/v0/b/ditenun-62c37.appspot.com/o/product%2Fimage%2FHero%20banner%201.png?alt=media&token=404bcdc1-cd0b-496b-92ce-16ef1ec96d57");
        imageList.add("https://firebasestorage.googleapis.com/v0/b/ditenun-62c37.appspot.com/o/product%2Fimage%2FHero%20banner%202.png?alt=media&token=ed1595f5-84d5-4ea6-9855-5bc783625c6c");
        imageList.add("https://firebasestorage.googleapis.com/v0/b/ditenun-62c37.appspot.com/o/product%2Fimage%2FHero%20banner%203.png?alt=media&token=1b838228-25a0-40e3-8431-ed5c88e00c21");


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
