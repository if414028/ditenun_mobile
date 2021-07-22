package com.example.ditenun.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ditenun.R;
import com.example.ditenun.activity.commerce.ProductDashboardActivity;
import com.example.ditenun.activity.tenun.ListTenunActivity;
import com.example.ditenun.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initLayout();
    }

    private void initLayout() {
        binding.btnDesignModule.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ListTenunActivity.class);
            startActivity(intent);
        });

        binding.btnEcommerceModule.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ProductDashboardActivity.class);
            startActivity(intent);
        });
    }
}