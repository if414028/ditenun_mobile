package com.example.ditenun.activity.commerce.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ditenun.R;
import com.example.ditenun.activity.commerce.ProductDashboardActivity;
import com.example.ditenun.activity.tenun.GenerateKristikActivity;
import com.example.ditenun.databinding.ActivityCommerceRegisterBinding;
import com.example.ditenun.utility.DialogUtility;
import com.example.ditenun.utility.OnConfirmListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CommerceRegisterActivity extends AppCompatActivity {

    private ActivityCommerceRegisterBinding binding;
    private CommerceRegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_commerce_register);
        viewModel = ViewModelProviders.of(this).get(CommerceRegisterViewModel.class);

        initLayout();
        observeViewModel();
    }

    private void initLayout() {
        binding.register.setOnClickListener(v -> {
            String fullName = binding.etRegisterFullName.getText().toString();
            String username = binding.etRegisterUsername.getText().toString();
            String email = binding.etRegisterEmail.getText().toString();
            String password = binding.etRegisterPassword.getText().toString();

            if (fullName.isEmpty()) {
                binding.tvFullnameErrorMessage.setVisibility(View.VISIBLE);
                binding.tvFullnameErrorMessage.setText("Nama lengkap tidak boleh kosong");
            } else {
                binding.tvFullnameErrorMessage.setVisibility(View.GONE);
            }

            if (username.isEmpty()) {
                binding.tvUsernameErrorMessage.setVisibility(View.VISIBLE);
                binding.tvUsernameErrorMessage.setText("Username tidak boleh kosong");
            } else {
                binding.tvUsernameErrorMessage.setVisibility(View.GONE);
            }

            if (email.isEmpty()) {
                binding.tvEmailErrorMessage.setVisibility(View.VISIBLE);
                binding.tvEmailErrorMessage.setText("Email tidak boleh kosong");
            } else {
                binding.tvEmailErrorMessage.setVisibility(View.GONE);
            }

            if (password.isEmpty()) {
                binding.tvPasswordErrorMessage.setVisibility(View.VISIBLE);
                binding.tvPasswordErrorMessage.setText("Password tidak boleh kosong");
            } else {
                binding.tvPasswordErrorMessage.setVisibility(View.GONE);
            }

            if (!fullName.isEmpty() && !username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                viewModel.doRegister(fullName, username, email, password);
            }
        });
        binding.tvSignIn.setOnClickListener(v -> onBackPressed());
    }

    private void observeViewModel() {
        viewModel.getSuccessRegister().observe(this, unused -> {
            DialogUtility.getInstance().displayConfirmationDialog(this,
                    R.drawable.ic_check_mark,
                    getResources().getString(R.string.regsiter_success),
                    getResources().getString(R.string.ok),
                    null,
                    false,
                    new OnConfirmListener() {
                        @Override
                        public void onPositive(View view, BottomSheetDialog dialog) {
                            dialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(), ProductDashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onNegative(View view, BottomSheetDialog dialog) {

                        }
                    });
        });
    }
}
