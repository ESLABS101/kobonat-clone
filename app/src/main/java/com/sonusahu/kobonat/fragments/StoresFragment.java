package com.sonusahu.kobonat.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sonusahu.kobonat.R;
import com.sonusahu.kobonat.adapters.StoreAdapter;
import com.sonusahu.kobonat.databinding.FragmentStoresBinding;
import com.sonusahu.kobonat.model.StoreModel;

import java.util.ArrayList;

public class StoresFragment extends Fragment {


    FragmentStoresBinding binding;
    private DatabaseReference myRef;
    private ArrayList<StoreModel> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stores, container, false);


        binding.progressBar.setVisibility(View.VISIBLE);

        binding.storeRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.storeRv.setHasFixedSize(true);
        binding.storeRv.setDrawingCacheEnabled(true);
        binding.storeRv.setItemViewCacheSize(500);
        binding.storeRv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        getDataDb();

        return binding.getRoot();

    }

    private void getDataDb() {


        myRef = FirebaseDatabase.getInstance().getReference().child("market_place");
        arrayList = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                    String name = snapshot.child("name").getValue(String.class);
                    String logo = snapshot.child("logo").getValue(String.class);
                    String location = snapshot.child("lat_lang").getValue(String.class);


                    arrayList.add(new StoreModel(name, logo, location.toString()));


                }

                StoreAdapter storeModel = new StoreAdapter(arrayList, getActivity());
                binding.storeRv.setAdapter(storeModel);

                binding.progressBar.setVisibility(View.GONE);
                binding.storeRv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("log", "Failed to read value.", error.toException());
                binding.progressBar.setVisibility(View.GONE);

            }
        });


    }
}