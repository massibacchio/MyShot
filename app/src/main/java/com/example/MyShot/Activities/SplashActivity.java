package com.example.MyShot.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MyShot.Classes.FirebaseWrapper;
import com.example.MyShot.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //check if user is logged or not
        //if not logged ---> log in/register
        FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();
        if (!auth.isAuthenticated()) {
            // Go to Activity for LogIn or SignUp
            this.goToActivity(EnterActivity.class);
        }
    }

    private void goToActivity (Class<?> activity){
        Intent intent = new Intent (this, activity);
        this.startActivity(intent);
        finish();
    }

    //go to MainActivity dopo i permessi garantiti
   // this.goToActivity(MainActivity.class);






        //PARTE PER I PERMESSI































}




