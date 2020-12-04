package com.sonusahu.kobonat;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.view.animation.AnimationUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sonusahu.kobonat.activity.AuthActivity;
import com.sonusahu.kobonat.activity.HomeActivity;
import com.sonusahu.kobonat.databinding.ActivitySplashBinding;


public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.imgk.setVisibility(View.VISIBLE);

                binding.imgk.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));

            }
        }, 400);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.imgO.setVisibility(View.VISIBLE);

                binding.imgO.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));

            }
        }, 600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.imgB.setVisibility(View.VISIBLE);

                binding.imgB.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));

            }
        }, 800);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.imgoTwo.setVisibility(View.VISIBLE);

                binding.imgoTwo.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));

            }
        }, 1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.imgN.setVisibility(View.VISIBLE);

                binding.imgN.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));

            }
        }, 1200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.imgA.setVisibility(View.VISIBLE);

                binding.imgA.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));

            }
        }, 1400);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.imgT.setVisibility(View.VISIBLE);

                binding.imgT.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up));

            }
        }, 1450);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.imgPinSplash.setVisibility(View.VISIBLE);

                binding.imgPinSplash.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_up_pin));

            }
        }, 1450);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                // Check if user is signed in (non-null) and update UI accordingly.
                 user = FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                    finish();
                }


            }
        }, 2200);
    }

}