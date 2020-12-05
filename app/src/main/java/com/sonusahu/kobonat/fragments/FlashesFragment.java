package com.sonusahu.kobonat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sonusahu.kobonat.R;
import com.sonusahu.kobonat.databinding.FragmentFlashesBinding;

public class FlashesFragment extends Fragment {


    FragmentFlashesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_flashes, container, false);



        binding.msg.setText("There are no flashes available at this moment. \nPlease revisit later");

        return binding.getRoot();
    }
}