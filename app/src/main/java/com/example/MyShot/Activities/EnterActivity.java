package com.example.MyShot.Activities;

import android.content.Intent;
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
    private static final String TAG = EnterActivity.class.getCanonicalName();

    private FragmentManager fragmentManager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);


        //TODO: render fragment
        //true perchè vogliamo sempre andare al login di default
        renderFragment(true);
        {

        }
    }




    //come input prende callback e la lista di arg del metodo Login o signup;
    //cioè ci dice quale fragment caricare nel container

    public void renderFragment(boolean isLogin) {

        Fragment fragment = null;

        if (isLogin){
            fragment = LogFragment.newInstance(LoginFragment.class, "signinCallback", boolean.class);
        } else {
            fragment = LogFragment.newInstance(SignupFragment.class, "signinCallback", boolean.class);
    }

       if (this.fragmentManager== null){
           this.fragmentManager = this.getSupportFragmentManager();

        }

        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        //1.ottimizza le operazioni
        //2.all interno del contenitore 'loginRegisterFragment' inserisci il fragment 'fragment'
        // For optimizations -- See: https://developer.android.com/reference/androidx/fragment/app/FragmentTransaction#setReorderingAllowed(boolean)
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.loginRegisterFragment, fragment);

        fragmentTransaction.commit();
    }


    //questo boolean ci dice se il processo ha avuto successo o meno
    public void signinCallback(boolean result){
        if(!result) {
            //TODO: better handling of the error
            Toast.makeText(this, "Username or pw are not valid", Toast.LENGTH_SHORT).show();
        }
        else{
                // go to splash
                Intent intent = new Intent(this, SplashActivity.class);
                this.startActivity(intent);
                this.finish();
            }
        }
    }












