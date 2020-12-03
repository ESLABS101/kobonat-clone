package com.sonusahu.kobonat.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sonusahu.kobonat.R;
import com.sonusahu.kobonat.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {


    private String uName, uEmail, uPassword, uConfirmPass;
    private String error = "";
    private CheckBox checkBox;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ActivityAuthBinding binding;
    private MaterialAlertDialogBuilder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);


        viewInit();
        onClick();
        dialogBox();

    }



    /*private void getUserDetails() {

        uName=binding.editFirstName.getText().toString();
        uEmail=binding.editSignUpEmail.getText().toString();
        uPassword=binding.editSignUpPassword.getText().toString();
        uConfirmPass=binding.editSignUpConfirmPassword.getText().toString();
    }*/

    private void dialogBox() {
        dialog = new MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setTitle("Kobonat clone");
        dialog.setPositiveButton("OK", (dialog, which) -> {

            dialog.cancel();
        });

    }

    private void validation() {


        if (binding.editFirstName.getText().toString().length() >= 2) {

            if (binding.editSignUpEmail.getText().toString().matches(emailPattern)) {

                if (binding.editSignUpPassword.getText().toString().length() >= 6) {

                    if (binding.editSignUpConfirmPassword.getText().toString()
                            .matches(binding.editSignUpPassword.getText().toString())) {
                        if (binding.cbAcceptTC.isChecked()) {

                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();


                        } else {
                            dialog.setMessage("Please accept Terms & Conditions");
                            dialog.show();
                        }


                    } else {

                        dialog.setMessage("Confirm password doesn't matched");
                        dialog.show();


                    }

                } else {


                    dialog.setMessage("Enter password (Minimum 6-Digits)");
                    dialog.show();

                }

            } else {

                dialog.setMessage("Enter a valid email address!");
                dialog.show();
            }

        } else {


            dialog.setMessage("Please enter full name!");
            dialog.show();


        }

    }

    private void onClick() {

        binding.textSignUp.setOnClickListener(v -> {
            binding.llSignIn.setVisibility(View.GONE);
            binding.llSignUp.setVisibility(View.VISIBLE);
        });
        binding.textSignIn.setOnClickListener(v -> {

            binding.llSignUp.setVisibility(View.GONE);
            binding.llSignIn.setVisibility(View.VISIBLE);

        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validation();

                startActivity(new Intent(AuthActivity.this,OtpActivity.class));

            }
        });

    }

    private void viewInit() {


    }



}