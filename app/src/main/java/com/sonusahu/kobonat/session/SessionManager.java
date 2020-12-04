package com.sonusahu.kobonat.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public Context context;
    int PRIVATE_MODE=0;
    private final String PREF_NAME="LOGIN";

    private final String LOGIN="IS_LOGIN";
    public final String NAME="NAME";
    public final String EMAIL="EMAIL";
    public final String PHONE_NUMBER="PHONE_NUMBER";
    public final String ADDRESS="ADDRESS";
    public final String AMOUNT="AMOUNT";
    public final String PASS="PASS";
    public final String DOB="DOB";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences("LOGIN",PRIVATE_MODE);
        editor=sharedPreferences.edit();
    }

    public void createSession(String email,String name,String address,String phone,String amount,String dob,String pass){

        editor.putBoolean(LOGIN,true);
        editor.putString("EMAIL",email);
        editor.putString("NAME",name);
        editor.putString("ADDRESS",address);
        editor.putString("PHONE_NUMBER",phone);
        editor.putString("AMOUNT",amount);
        editor.putString("DOB",dob);
        editor.putString("PASS",pass);

        editor.apply();
    }
    public boolean isLogin(){

        return sharedPreferences.getBoolean(LOGIN,false);

    }
    public void checkingLogin(){
        if (this.isLogin()){

            /*Intent i=new Intent(context, HomeActivity.class);
            context.startActivity(i);
            ((HomeActivity) context).finish();*/
        }
    }
    public HashMap<String,String> getUserDetail(){

        HashMap<String ,String > user=new HashMap<>();

        user.put(NAME,sharedPreferences.getString(NAME,null));
        user.put(EMAIL,sharedPreferences.getString(EMAIL,null));
        user.put(PHONE_NUMBER,sharedPreferences.getString(PHONE_NUMBER,null));
        user.put(ADDRESS,sharedPreferences.getString(ADDRESS,null));
        user.put(AMOUNT,sharedPreferences.getString(AMOUNT,null));
        user.put(DOB,sharedPreferences.getString(DOB,null));
        user.put(PASS,sharedPreferences.getString(PASS,null));
        editor.putBoolean(LOGIN,true);

        return user;

    }
    public void logout(){

        editor.clear();
        editor.commit();

    }
}
