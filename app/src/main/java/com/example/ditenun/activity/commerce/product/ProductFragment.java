package com.example.ditenun.activity.commerce.product;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.ditenun.R;
import com.example.ditenun.databinding.ItemNewArrivalsBinding;
import com.example.ditenun.databinding.ProductFragmentBinding;
import com.example.ditenun.model.Product;
import com.example.ditenun.utility.SimpleFilterRecyclerAdapter;
import com.example.ditenun.utility.TextUtility;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    private ProductViewModel viewModel;
    private ProductFragmentBinding binding;

    private SimpleFilterRecyclerAdapter<Product> productAdapter;

    private Integer pageNo;

    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(ProductViewModel.class);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.product_fragment, container, false);

        initLayout();
        observeLiveData();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.clearProductList();
        viewModel.fetchListProduct(pageNo);
    }

    private void initLayout() {
        setupAction();
        initProductRecyclerView();
    }

    private void setupAction() {
        binding.etSearchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null) {
                    String searchedText = editable.toString();
                    productAdapter.filter(searchedText);
                } else {
                    binding.setIsNotFound(false);
                }
            }
        });
    }

    private void initProductRecyclerView() {
        binding.rvProduct.setLayoutManager(new GridLayoutManager(getContext(), 3));
        productAdapter = new SimpleFilterRecyclerAdapter<>(new ArrayList<>(), R.layout.item_new_arrivals, (holder, item) -> {
            ItemNewArrivalsBinding itemBinding = (ItemNewArrivalsBinding) holder.getLayoutBinding();
            if (item != null) {
                if (item.getImages() != null && item.getImages().size() > 0) {
                    Picasso.with(getContext()).load(item.getImages().get(0).getSrc()).resize(100, 150).centerCrop().into(itemBinding.imgNewArrivals);
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
        }, new SimpleFilterRecyclerAdapter.OnSearchListener<Product>() {
            @Override
            public Product onSearchRules(Product model, String searchedText) {
                if (model.getName().toLowerCase().contains(searchedText)) {
                    binding.setIsNotFound(false);
                    return model;
                }
                return null;
            }

            @Override
            public void onSearch(ArrayList<Product> model) {

            }

            @Override
            public void onSearchEmpty(String searchedText) {
                binding.setIsNotFound(true);
            }
        });
        binding.rvProduct.setAdapter(productAdapter);
    }

    private void observeLiveData() {
        viewModel.getSuccessGetListProductEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                productAdapter.setMainData(viewModel.getProductList());
                productAdapter.notifyDataSetChanged();
            }
        });
    }
}
