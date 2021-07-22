package com.example.ditenun.activity.commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.ditenun.R;
import com.example.ditenun.activity.commerce.account.AccountFragment;
import com.example.ditenun.activity.commerce.product.ProductFragment;
import com.example.ditenun.activity.commerce.product.ProductHomeFragment;
import com.example.ditenun.databinding.ActivityProductDasboardBinding;

public class ProductDashboardActivity extends AppCompatActivity {

    private ActivityProductDasboardBinding binding;

    public static final String ARG_SUCCESS_CREATE_ORDER = "success_create_order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_dasboard);

        initLayout();
    }

    private void initLayout() {
        loadFragment(ProductHomeFragment.newInstance());
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.bottom_nav_home: {
                    fragment = ProductHomeFragment.newInstance();
                    break;
                }
                case R.id.bottom_nav_product: {
                    fragment = ProductFragment.newInstance();
                    break;
                }
                case R.id.bottom_nav_account: {
                    fragment = AccountFragment.newInstance();
                    break;
                }
            }
            return loadFragment(fragment);
        });
    }

    private Boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ly_content, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}