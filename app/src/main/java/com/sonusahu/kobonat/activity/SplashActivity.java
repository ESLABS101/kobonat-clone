package com.sonusahu.kobonat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sonusahu.kobonat.R;
import com.sonusahu.kobonat.databinding.ActivitySplashBinding;


public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        new Handler().postDelayed(() -> {

            binding.imgk.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));
            binding.imgk.setVisibility(View.VISIBLE);
        }, 400);

        new Handler().postDelayed(() -> {

            binding.imgO.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));
            binding.imgO.setVisibility(View.VISIBLE);
        }, 600);

        new Handler().postDelayed(() -> {

            binding.imgB.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));
            binding.imgB.setVisibility(View.VISIBLE);
        }, 800);

        new Handler().postDelayed(() -> {

            binding.imgoTwo.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));
            binding.imgoTwo.setVisibility(View.VISIBLE);
        }, 1000);
        new Handler().postDelayed(() -> {

            binding.imgN.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));
            binding.imgN.setVisibility(View.VISIBLE);
        }, 1200);

        new Handler().postDelayed(() -> {

            binding.imgA.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));
            binding.imgA.setVisibility(View.VISIBLE);
        }, 1400);
        new Handler().postDelayed(() -> {

            binding.imgT.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));
            binding.imgT.setVisibility(View.VISIBLE);
        }, 1450);

        new Handler().postDelayed(() -> {

            binding.imgPinSplash.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up_pin));
            binding.imgPinSplash.setVisibility(View.VISIBLE);
        }, 1450);

        new Handler().postDelayed(() -> {


            // Check if user is signed in (non-null) and update UI accordingly.
            user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, AuthActivity.class));
            }
            finish();


        }, 2200);
    }

}