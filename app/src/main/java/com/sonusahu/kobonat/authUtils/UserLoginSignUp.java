package com.sonusahu.kobonat.authUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sonusahu.kobonat.activity.HomeActivity;

public class UserLoginSignUp {


    Context context;

    public UserLoginSignUp(Context context) {
        this.context = context;
    }

    private static final String TAG = "Auth Activity";

    private FirebaseAuth mAuth;

    public void createUserSignUp(String email, String password,String name) {

        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");


                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(context, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }

                });
    }


    public void loginUser(String email,String password){
       FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");

                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(context, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }

                    // ...
                });
    }


}
