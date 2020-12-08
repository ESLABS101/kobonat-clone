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
import com.sonusahu.kobonat.model.CatModel;

import java.util.ArrayList;


public class CatAdapter extends RecyclerView.Adapter<CatAdapter.VH> {

    ArrayList<CatModel> arrayList;
    private final Context context;

    public CatAdapter(ArrayList<CatModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_coupon_category_list, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {


        CatModel catModel = arrayList.get(position);

        holder.catName.setText(catModel.getCatName());

        Glide.with(context)
                .load(catModel.getPicUri())
                .placeholder(R.drawable.bg_placeholder)
                .into(holder.catImg);

        Glide.with(context)
                .load(catModel.getIconUri())
                .placeholder(R.drawable.bg_placeholder_icon)
                .into(holder.catIcon);


    }

    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class VH extends RecyclerView.ViewHolder {
        public TextView catName;
        ImageView catImg, catIcon;

        public VH(@NonNull View itemView) {
            super(itemView);

            catName = itemView.findViewById(R.id.textCategoryName);
            catIcon = itemView.findViewById(R.id.imgCategoryIcon);
            catImg = itemView.findViewById(R.id.imgCoupon);


        }
    }
}
