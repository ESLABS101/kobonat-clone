package com.sonusahu.kobonat.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sonusahu.kobonat.R;
import com.sonusahu.kobonat.authUtils.UserLoginSignUp;
import com.sonusahu.kobonat.databinding.ActivityAuthBinding;

import java.util.HashMap;
import java.util.Map;

public class AuthActivity extends AppCompatActivity {


    private String uName, uEmail, uPassword, uConfirmPass;
    private CheckBox checkBox;
    ProgressDialog progressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ActivityAuthBinding binding;
    private MaterialAlertDialogBuilder dialog;
    private UserLoginSignUp userLoginSignUp;

    private final String TAG = "Auth Activity";
    private FirebaseAuth.AuthStateListener authStateListener;

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public GoogleSignInClient mGoogleSignInClient;
    private final int RC_SIGN_IN = 111;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        FacebookSdk.sdkInitialize(this);
        userLoginSignUp = new UserLoginSignUp(AuthActivity.this);

        onClick();
        dialogBox();
        progressDialog();
        faceBookSetup();

    }

    private void progressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Google sign in");
        progressDialog.setMessage("Signing in");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }

    private void dialogBox() {
        dialog = new MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme).setTitle("Kobonat clone");
        dialog.setPositiveButton("OK", (dialog, which) -> {
            dialog.cancel();
        });

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

        binding.btnSignUp.setOnClickListener(v -> {

            uName = binding.editFirstName.getText().toString();
            uEmail = binding.editSignUpEmail.getText().toString();
            uPassword = binding.editSignUpPassword.getText().toString();
            uConfirmPass = binding.editSignUpConfirmPassword.getText().toString();


            if (uName.length() >= 2) {

                if (uEmail.matches(emailPattern)) {

                    if (uPassword.length() >= 6) {

                        if (uConfirmPass.matches(uPassword)) {

                            try {
                                userLoginSignUp.createUserSignUp(uEmail, uConfirmPass, uName);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {

                            dialog.setMessage("Confirm password doesn't matched with your password)");
                            dialog.show();
                        }

                    } else {

                        dialog.setMessage("Enter password (minimum 6-digits)");
                        dialog.show();
                    }

                } else {

                    dialog.setMessage("Please Enter a valid email address");
                    dialog.show();
                }

            } else {

                dialog.setMessage("Please Enter full name");
                dialog.show();
            }


        });
        binding.btnSignin.setOnClickListener(v -> {

            userLoginSignUp = new UserLoginSignUp(AuthActivity.this);

            String email = binding.editEmail.getText().toString();
            String pass = binding.editPassword.getText().toString();


            if (email.matches(emailPattern)) {

                if (pass.length() >= 6) {


                    try {
                        userLoginSignUp.loginUser(email, pass);

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }


                } else {

                    dialog.setMessage("Enter password (minimum 6-digits)");
                    dialog.show();
                }

            } else {

                dialog.setMessage("Please Enter a valid email address");
                dialog.show();
            }


        });
        binding.btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnFacebook) {
                    binding.fbBtn.performClick();
                    progressDialog.setTitle("Facebook sign in");
                    progressDialog.setMessage("Sign in as Facebook");
                    progressDialog.show();

                }
            }
        });
        binding.btnGoogle.setOnClickListener(v -> {
            googleSetup();
            progressDialog.setTitle("Google sign in");
            progressDialog.setMessage("Sign in as Google");
            progressDialog.show();
        });

    }

    private void faceBookSetup() {

        callbackManager = CallbackManager.Factory.create();

        binding.fbBtn.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        Log.d(TAG, "onSuccess" + loginResult);

                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "onSuccess" + "onCancel");

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.e(TAG, "onSuccess" + error.getMessage());
                    }
                });


        authStateListener = firebaseAuth -> {

            FirebaseUser user = firebaseAuth.getCurrentUser();

            if (user != null) {

                uploadUserDB();
            }
        };


        AccessTokenTracker tokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                if (currentAccessToken == null) {

                    mAuth.signOut();
                }
            }
        };


    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        uploadUserDB();

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(AuthActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                });
    }

    //google
    private void googleSetup() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signIn();

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);


    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    Log.d(TAG, "signInWithCredential:success");
                    uploadUserDB();

                }).addOnFailureListener(e -> {

            Log.w(TAG, "signInWithCredential:failure" + e.getMessage());

            Toast.makeText(getApplicationContext(), "GoogleVerification Failed", Toast.LENGTH_LONG).show();


        });
    }

    private void uploadUserDB() {


        FirebaseUser user = mAuth.getCurrentUser();

        final String email = user.getEmail();
        final String displayName = user.getDisplayName();
        final String phoneNumber = user.getPhoneNumber();
        final Uri imgURL = user.getPhotoUrl();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Map<String, String> map = new HashMap<>();
        map.put("Name", displayName);
        map.put("Email", email);
        map.put("Phone Number", phoneNumber);
        map.put("Profile url", imgURL.toString());

        databaseReference.child("users").child(user.getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if (task.isSuccessful()) {


                    progressDialog.dismiss();

                    startActivity(new Intent(AuthActivity.this, HomeActivity.class));
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.w(TAG, "signInWithCredential:failure" + e.getMessage());

                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (authStateListener != null) {

            mAuth.removeAuthStateListener(authStateListener);
        }

    }
}