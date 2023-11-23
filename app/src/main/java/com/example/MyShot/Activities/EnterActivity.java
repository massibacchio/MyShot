package com.example.MyShot.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.MyShot.Fragments.LogFragment;
import com.example.MyShot.Fragments.LoginFragment;
import com.example.MyShot.Fragments.SignupFragment;
import com.example.MyShot.R;

public class EnterActivity extends AppCompatActivity {


    private FragmentManager fragmentManager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        //true because login fragment is the first choice
        renderFragment(true);
        {

        }
    }




    //as inputs:  callback and arg list of login or signup method
    //tells us which fragment has to be loaded in the container

    public void renderFragment(boolean isLogin) {

        Fragment fragment;

        if (isLogin){
            fragment = LogFragment.newInstance(LoginFragment.class, "signInCallback", boolean.class);
        } else {
            fragment = LogFragment.newInstance(SignupFragment.class, "signInCallback", boolean.class);
    }

       if (this.fragmentManager== null){
           this.fragmentManager = this.getSupportFragmentManager();

        }

        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        //1.optimizes operations
        //2.inside the container 'loginRegisterFragment' put Fragment 'fragment'

        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.loginRegisterFragment, fragment);

        fragmentTransaction.commit();
    }




    //this boolean says is this process was successful
    public void signInCallback(boolean result){
        if(!result) {

            Toast.makeText(this, "Username or pw are not valid", Toast.LENGTH_SHORT).show();
        }
        else{
                //LogFragment.newInstance(LoginFragment.class, "signInCallback", boolean.class);
            }
        }




}












