package com.sonusahu.kobonat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sonusahu.kobonat.R;
import com.sonusahu.kobonat.model.StoreModel;

import java.util.ArrayList;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.VH> {

    ArrayList<StoreModel> arrayList;
    private final Context context;

    public StoreAdapter(ArrayList<StoreModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_store_list, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {


        StoreModel storeModel=arrayList.get(position);

        holder.storeName.setText(storeModel.getStoreName());


        Glide.with(context)
                .load(storeModel.getStoreLogo())
                .placeholder(R.drawable.bg_placeholder)
                .into(holder.storeLogo);

       /* Glide.with(context)
                .load(catModel.getIconUri())
                .placeholder(R.drawable.bg_placeholder_icon)
                .into(holder.);*/


    }

    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class VH extends RecyclerView.ViewHolder {
        public TextView storeName,viewOnMap;
        ImageView storeLogo, locationBtn;

        public VH(@NonNull View itemView) {
            super(itemView);

            storeName = itemView.findViewById(R.id.textStoreName);
            viewOnMap = itemView.findViewById(R.id.viewOnMaps);
            locationBtn = itemView.findViewById(R.id.imgGetDirection);
            storeLogo = itemView.findViewById(R.id.imgStore);


        }
    }
}
