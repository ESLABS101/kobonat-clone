package com.sonusahu.kobonat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.sonusahu.kobonat.R;
import com.sonusahu.kobonat.databinding.ActivityHomeBinding;

import static com.sonusahu.kobonat.R.id.miLocation;
import static com.sonusahu.kobonat.R.id.miSearch;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;


    Fragment homeFragment=new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        loadFragment(homeFragment);

        setSupportActionBar(binding.toolbar);
        onClick();


    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container, fragment);
        ft.commit();

    }


    private void onClick() {

        binding.toolbar.setNavigationOnClickListener(v -> binding.drawerLayout.openDrawer(GravityCompat.START));

        binding.navView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {


                case R.id.menu_home:

                    Fragment homeFragment=new HomeFragment();
                    loadFragment(homeFragment);

                    Toast.makeText(getApplicationContext(), "Clicked Home", Toast.LENGTH_LONG).show();

                    break;
                case R.id.favourite_coupons:


                   /* Fragment homeFragment=new HomeFragment();
                    loadFragment(homeFragment);*/

                    Toast.makeText(getApplicationContext(), "Clicked Favorite coupons", Toast.LENGTH_LONG).show();

                    break;

                case R.id.redeemed_coupons:

                    /*HomeFragment homeFragment=new HomeFragment();
                    loadFragment(homeFragment);*/

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