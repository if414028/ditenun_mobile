package com.example.ditenun.activity.commerce.account;

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

import com.example.ditenun.R;
import com.example.ditenun.activity.commerce.order.list.OrderActivity;
import com.example.ditenun.databinding.AccountFragmentBinding;

import org.jetbrains.annotations.NotNull;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;
    private AccountFragmentBinding binding;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.account_fragment, container, false);

        initLayout();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initLayout() {
        binding.btnListTransaction.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), OrderActivity.class);
            startActivity(intent);
        });
    }
}
