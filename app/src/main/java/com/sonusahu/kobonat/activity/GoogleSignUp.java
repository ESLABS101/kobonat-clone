package com.sonusahu.kobonat.activity;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.sonusahu.kobonat.R;

public class GoogleSignUp {


    Context context;
    String email;
    private GoogleSignInClient mGoogleSignInClient;

    public GoogleSignUp(Context context, String email) {
        this.context = context;
        this.email = email;
    }


}
