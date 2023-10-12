package com.example.MyShot.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MyShot.R;

import java.util.Random;

import com.example.MyShot.Classes.FirebaseManager;
import com.example.MyShot.Classes.PermissionManager;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getCanonicalName();
    private static final int PERMISSION_REQUEST_CODE = (new Random()).nextInt() & Integer.MAX_VALUE;


    private void toActivity(Class<?> activity){
        Intent intent = new Intent(this,activity);
        this.startActivity (intent);
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    //check user log in or not
    FirebaseManager.Auth auth = new FirebaseManager.Auth();
        if (!auth.isAuthenticated()) {

        // Go to Activity for LogIn or SignUp
        this.toActivity(EnterActivity.class);
    }























}




