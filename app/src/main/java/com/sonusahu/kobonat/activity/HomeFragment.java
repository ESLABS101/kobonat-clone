package com.sonusahu.kobonat.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sonusahu.kobonat.R;
import com.sonusahu.kobonat.adapters.TabAdapter;
import com.sonusahu.kobonat.databinding.FragmentHomeBinding;
import com.sonusahu.kobonat.fragments.CouponsFragment;
import com.sonusahu.kobonat.fragments.FlashesFragment;
import com.sonusahu.kobonat.fragments.StoresFragment;


public class HomeFragment extends Fragment {



    FragmentHomeBinding binding;
    private TabAdapter pagerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);


        try {

            setupViewPager(binding.viewPager);
            binding.tabLayout.setupWithViewPager(binding.viewPager);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return binding.getRoot();
    }

    private void setupViewPager(ViewPager viewPager) {


        if (viewPager != null) {


            CouponsFragment couponsFragment = new CouponsFragment();
            FlashesFragment flashesFragment = new FlashesFragment();
            StoresFragment storesFragment = new StoresFragment();


            pagerAdapter = new TabAdapter(getActivity().getSupportFragmentManager());
            pagerAdapter.addFragment(couponsFragment, "Coupons");
            pagerAdapter.addFragment(flashesFragment, "Flashes");
            pagerAdapter.addFragment(storesFragment, "Store");


        }

        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(4);
            viewPager.setAdapter(pagerAdapter);
        }
    }
}