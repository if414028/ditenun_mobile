package com.example.ditenun.activity.tenun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ditenun.R;
import com.example.ditenun.databinding.ActivityListTenunBinding;
import com.example.ditenun.model.Tenun;
import com.example.ditenun.network.DitenunApiClient;
import com.example.ditenun.network.DitenunNetworkInterface;
import com.example.ditenun.utility.BrowseTenunRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

public class ListTenunActivity extends AppCompatActivity implements BrowseTenunRecyclerViewAdapter.OnClickItemTenunListener {

    static final String LAYOUT_EMPTY = "emptyList";
    static final String LAYOUT_ERROR = "error";
    static final String LAYOUT_LOADING = "loading";
    static final String LAYOUT_SUCCESS = "success";

    private ActivityListTenunBinding binding;

    private DitenunNetworkInterface networkInterface;
    private Realm realm;

    private List<Tenun> listTenun = Collections.EMPTY_LIST;
    private List<Object> listObject = new ArrayList<>();
    private BrowseTenunRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager linearLayoutManager;
    private GridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_tenun);
        networkInterface = DitenunApiClient.createService(DitenunNetworkInterface.class);
        realm = Realm.getDefaultInstance();

        initLayout();
    }

    private void initLayout() {
        adapter = new BrowseTenunRecyclerViewAdapter(listObject, this);
        binding.rvMotif.setAdapter(adapter);
        manager = new GridLayoutManager(getApplicationContext(), 3, GridLayoutManager.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case BrowseTenunRecyclerViewAdapter.TENUN:
                        return 1;
                    case BrowseTenunRecyclerViewAdapter.HEADER_TENUN:
                        return manager.getSpanCount();
                    default:
                        return 0;
                }
            }
        });
        binding.rvMotif.setLayoutManager(manager);
        populateListTenun();
    }

    private void populateListTenun() {
        realm.executeTransactionAsync(realm1 -> {
            listTenun = realm1.copyFromRealm(realm1.where(Tenun.class).findAll().sort("asalTenun", Sort.DESCENDING));
        }, () -> {
            if (!listTenun.isEmpty()) {
                adapter.setData(populateNewListObject(listTenun));
                updateLayout(LAYOUT_SUCCESS);
            } else {
                updateLayout(LAYOUT_EMPTY);
            }
        });
    }

    private List<Object> populateNewListObject(List<Tenun> listTenun) {
        List<Object> tempListObject = new ArrayList<>();

        String asalTenun = listTenun.get(0).getAsalTenun();
        tempListObject.add(asalTenun);

        for (Tenun tenun : listTenun) {
            if (!asalTenun.equals(tenun.getAsalTenun())) {
                asalTenun = tenun.getAsalTenun();
                tempListObject.add(asalTenun);
                tempListObject.add(tenun);

            } else {
                tempListObject.add(tenun);
            }
        }

        return tempListObject;
    }

    private void updateLayout(String status) {
        switch (status) {
            case LAYOUT_SUCCESS:
                binding.progress.setVisibility(View.GONE);
                binding.lyError.setVisibility(View.GONE);
                binding.rvMotif.setVisibility(View.VISIBLE);
                break;
            case LAYOUT_EMPTY:
                binding.progress.setVisibility(View.GONE);
                binding.lyError.setVisibility(View.VISIBLE);
                binding.rvMotif.setVisibility(View.GONE);
                binding.tvErrorMessage.setText("{fa-info 200%}  No data found");
                break;
            case LAYOUT_ERROR:
                binding.progress.setVisibility(View.GONE);
                binding.lyError.setVisibility(View.VISIBLE);
                binding.rvMotif.setVisibility(View.GONE);
                binding.tvErrorMessage.setText("{fa-info 200%} Error");
                break;
            case LAYOUT_LOADING:
                binding.progress.setVisibility(View.VISIBLE);
                binding.rvMotif.setVisibility(View.GONE);
                binding.lyError.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void OnClickItemTenun(String idTenun, View imageThumb) {
        Intent intent = new Intent(getApplicationContext(), DetailTenunActivity.class);
        intent.putExtra(DetailTenunActivity.KEY_ID_TENUN, idTenun);
        startActivity(intent);
    }
}