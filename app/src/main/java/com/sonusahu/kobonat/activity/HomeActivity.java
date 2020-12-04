package com.sonusahu.kobonat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.sonusahu.kobonat.R;
import com.sonusahu.kobonat.adapters.TabAdapter;
import com.sonusahu.kobonat.databinding.ActivityHomeBinding;
import com.sonusahu.kobonat.fragments.CouponsFragment;
import com.sonusahu.kobonat.fragments.FlashesFragment;
import com.sonusahu.kobonat.fragments.StoresFragment;

import static com.sonusahu.kobonat.R.id.miLocation;
import static com.sonusahu.kobonat.R.id.miSearch;

public class HomeActivity extends AppCompatActivity {


    private View view;

    ActivityHomeBinding binding;
    private TabAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setSupportActionBar(binding.toolbar);
        onClick();

        try {

            setupViewPager(binding.viewPager);
            binding.tabLayout.setupWithViewPager(binding.viewPager);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void setupViewPager(ViewPager viewPager) {


        if (viewPager != null) {


            CouponsFragment couponsFragment = new CouponsFragment();
            FlashesFragment flashesFragment = new FlashesFragment();
            StoresFragment storesFragment = new StoresFragment();


            pagerAdapter = new TabAdapter(getSupportFragmentManager());
            pagerAdapter.addFragment(couponsFragment, "Coupons");
            pagerAdapter.addFragment(flashesFragment, "Flashes");
            pagerAdapter.addFragment(storesFragment, "Store");


        }

        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(4);
            viewPager.setAdapter(pagerAdapter);
        }
    }

    private void onClick() {

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.menu_home:


                        Toast.makeText(getApplicationContext(), "Clicked Home", Toast.LENGTH_LONG).show();

                        break;
                    case R.id.favourite_coupons:

                        Toast.makeText(getApplicationContext(), "Clicked Favorite coupons", Toast.LENGTH_LONG).show();

                        break;

                    case R.id.redeemed_coupons:


                        Toast.makeText(getApplicationContext(), "Clicked Redeemed coupons", Toast.LENGTH_LONG).show();

                        break;
                    case R.id.redeemed_flash:

                        Toast.makeText(getApplicationContext(), "Clicked Redeemed flash", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.notifications:

                        Toast.makeText(getApplicationContext(), "Clicked Notifications", Toast.LENGTH_LONG).show();

                        break;

                    case R.id.logout:


                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(HomeActivity.this, AuthActivity.class));
                        finish();

                        break;


                }

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {

            case miSearch:

                Toast.makeText(getApplicationContext(), "Clicked Search", Toast.LENGTH_LONG).show();

                //binding.navView.setItemBackground(R.drawable.dra);

                break;
            case miLocation:

                Toast.makeText(getApplicationContext(), "Clicked Location", Toast.LENGTH_LONG).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}