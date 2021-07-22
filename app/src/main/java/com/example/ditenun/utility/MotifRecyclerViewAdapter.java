package com.example.ditenun.utility;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ditenun.R;
import com.example.ditenun.activity.tenun.GenerateMotifActivity;
import com.example.ditenun.model.MotifTenun;
import com.example.ditenun.network.DitenunApiClient;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MotifRecyclerViewAdapter extends RecyclerView.Adapter<MotifRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<MotifTenun> mData;

    public MotifRecyclerViewAdapter(Context mContext, List<MotifTenun> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.layout_single_item_tenun, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MotifRecyclerViewAdapter.MyViewHolder holder, int position) {
        int models = position + 1;
        holder.model_generate.setText("Model " + models);
        String imgUrl = DitenunApiClient.ENDPOINT + mData.get(position).getImageMotif();
        Picasso.with(holder.hasil_generate.getContext()).load(imgUrl).into(holder.hasil_generate);
        holder.hasil_generate.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, GenerateMotifActivity.class);
            intent.putExtra(GenerateMotifActivity.TENUN_IMAGE_ID, mData.get(position).getId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView model_generate;
        ImageView hasil_generate;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            model_generate = (TextView) itemView.findViewById(R.id.textId);
            hasil_generate = (ImageView) itemView.findViewById(R.id.thumbTenun);
            cardView = (CardView) itemView.findViewById(R.id.container_for_item);
        }
    }
}
