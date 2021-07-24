package com.example.ditenun.activity.commerce.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ditenun.R;
import com.example.ditenun.activity.commerce.ProductDashboardActivity;
import com.example.ditenun.databinding.ActivityCommerceLoginBinding;
import com.example.ditenun.utility.UserConfiguration;

public class CommerceLoginActivity extends AppCompatActivity {

    private ActivityCommerceLoginBinding binding;
    private CommerceLoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_commerce_login);
        viewModel = ViewModelProviders.of(this).get(CommerceLoginViewModel.class);

        initLayout();
        observeLiveEvent();
    }

    private void initLayout() {
        binding.login.setOnClickListener(v -> {
            if (binding.email.getText().toString().isEmpty()) {
                binding.tvUsernameErrorMessage.setVisibility(View.VISIBLE);
                binding.tvUsernameErrorMessage.setText(getResources().getString(R.string.username_empty));
            } else {
                binding.tvUsernameErrorMessage.setVisibility(View.GONE);
            }

            if (binding.password.getText().toString().isEmpty()) {
                binding.tvPasswordErrorMessage.setVisibility(View.VISIBLE);
                binding.tvPasswordErrorMessage.setText(getResources().getString(R.string.password_empty));
            } else {
                binding.tvPasswordErrorMessage.setVisibility(View.GONE);
            }

            if (!binding.email.getText().toString().isEmpty() && !binding.password.getText().toString().isEmpty()) {
                binding.tvUsernameErrorMessage.setVisibility(View.GONE);
                binding.tvPasswordErrorMessage.setVisibility(View.GONE);
                viewModel.doLogin(binding.email.getText().toString(), binding.password.getText().toString());
            }
        });
    }

    private void observeLiveEvent() {
        viewModel.getSuccessLogin().observe(this, unused -> {
            Intent intent = new Intent(getApplicationContext(), ProductDashboardActivity.class);
            startActivity(intent);
            finish();
        });

        viewModel.getErrorLogin().observe(this, unused -> Toast.makeText(CommerceLoginActivity.this, "Login Gagal, Silahkan periksa username dan password anda", Toast.LENGTH_LONG).show());
    }
}