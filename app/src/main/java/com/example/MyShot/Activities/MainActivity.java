package com.example.MyShot.Activities;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.MyShot.Fragments.AddImageFragment;
import com.example.MyShot.Fragments.HomeFragment;
import com.example.MyShot.R;



public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        // Carica il tuo HomeFragment nel container
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new HomeFragment())
                .commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_action_space, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.action_fragment1:
                fragment = new AddImageFragment(); // Sostituire con il nome corretto del tuo fragment
                break;
            case R.id.action_fragment2:
                fragment = new HomeFragment();
                break;
            /*case R.id.action_fragment3:
                fragment = new Fragment3();
                break;

             */
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


