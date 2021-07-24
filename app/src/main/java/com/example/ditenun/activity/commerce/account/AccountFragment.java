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
import com.example.ditenun.activity.main.MainActivity;
import com.example.ditenun.databinding.AccountFragmentBinding;
import com.example.ditenun.utility.UserConfiguration;

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
        setupAction();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initLayout() {
        String username = UserConfiguration.getInstance().getUsername();
        String nickname = UserConfiguration.getInstance().getNickname();
        String email = UserConfiguration.getInstance().getEmail();
        binding.tvUsername.setText(String.format("Hi, %s", nickname));
        binding.tvAccountType.setText(username);
        binding.tvEmail.setText(email);
    }

    private void setupAction() {
        binding.btnListTransaction.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), OrderActivity.class);
            startActivity(intent);
        });

        binding.btnLogout.setOnClickListener(v -> {
            UserConfiguration.getInstance().setLoggedIn(false);
            UserConfiguration.getInstance().setUsername("");
            UserConfiguration.getInstance().setEmail("");
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            getActivity().finish();
        });
    }
}
