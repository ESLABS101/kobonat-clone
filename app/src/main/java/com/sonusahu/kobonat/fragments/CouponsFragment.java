package com.sonusahu.kobonat.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sonusahu.kobonat.R;
import com.sonusahu.kobonat.adapters.CatAdapter;
import com.sonusahu.kobonat.databinding.FragmentCouponsBinding;
import com.sonusahu.kobonat.model.CatModel;

import java.util.ArrayList;


public class CouponsFragment extends Fragment {

    FragmentCouponsBinding binding;
    private DatabaseReference myRef;

    ProgressDialog progressDialog;
    ArrayList<CatModel> arrayList;
    private CatModel catModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coupons, null, false);


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait..");
        progressDialog.show();

        binding.catListRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.catListRv.setHasFixedSize(true);
        binding.catListRv.setDrawingCacheEnabled(true);
        binding.catListRv.setItemViewCacheSize(500);
        binding.catListRv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        binding.catListRv.setAnimation(new AnimationUtils().loadAnimation(getActivity(),R.anim.fade_in_from_bottom));

        getDataDb();

        return binding.getRoot();

    }

    private void getDataDb() {

        myRef = FirebaseDatabase.getInstance().getReference().child("category").child("cat").child("catlist");
        arrayList = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                    String name = snapshot.child("cat_name").getValue(String.class);
                    String ic_url = snapshot.child("ic_url").getValue(String.class);
                    String pic_url = snapshot.child("pic_url").getValue(String.class);

                    arrayList.add(new CatModel(name.toString(), ic_url, pic_url));

                }

                CatAdapter catAdapter = new CatAdapter(arrayList, getActivity());
                binding.catListRv.setAdapter(catAdapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("log", "Failed to read value.", error.toException());
                progressDialog.dismiss();
            }
        });

    }


}

