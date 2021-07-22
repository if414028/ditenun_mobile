package com.example.ditenun.activity.tenun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ditenun.R;
import com.example.ditenun.databinding.ActivityDetailTenunBinding;
import com.example.ditenun.model.MotifTenun;
import com.example.ditenun.model.Tenun;
import com.example.ditenun.network.DitenunApiClient;
import com.example.ditenun.network.DitenunNetworkInterface;
import com.example.ditenun.utility.MotifRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

public class DetailTenunActivity extends AppCompatActivity {

    public static final String KEY_ID_TENUN = "id_key";

    private ActivityDetailTenunBinding binding;

    private Realm realm;

    List<MotifTenun> listMotif = new ArrayList<>();
    private Tenun tenun;
    private String idTenun;

    MotifRecyclerViewAdapter motifAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tenun);
        realm = Realm.getDefaultInstance();

        getAdditionalData();
        prepareDataFromDatabase();
        initLayout();
    }

    private void prepareDataFromDatabase() {
        listMotif = realm.where(MotifTenun.class).equalTo("idTenun", idTenun).findAll().sort("id", Sort.ASCENDING);
        tenun = realm.where(Tenun.class)
                .equalTo("id", idTenun)
                .findFirst();
    }

    private void initLayout() {
        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });
        setupRecyclerView();
        String imageUrl = DitenunApiClient.ENDPOINT + tenun.getImageSrc();
        Picasso.with(getApplicationContext()).load(imageUrl).into(binding.lyDetailTenun.thumbTenun);
        binding.lyDetailTenun.historyTenun.setText(tenun.getSejarahTenun());
        binding.lyDetailTenun.descTenun.setText(tenun.getDeskripsiTenun());
        binding.lyDetailTenun.titleTenun.setText(tenun.getNamaTenun());
    }

    private void setupRecyclerView() {
        motifAdapter = new MotifRecyclerViewAdapter(this, listMotif);
        binding.lyDetailTenun.recyclerviewulosId.setLayoutManager(new GridLayoutManager(this, 3));
        binding.lyDetailTenun.recyclerviewulosId.setAdapter(motifAdapter);
    }

    private void getAdditionalData() {
        Intent returnIntent = getIntent();
        if (returnIntent != null) {
            if (returnIntent.hasExtra(KEY_ID_TENUN)) {
                idTenun = returnIntent.getStringExtra(KEY_ID_TENUN);
            }
        }
    }
}