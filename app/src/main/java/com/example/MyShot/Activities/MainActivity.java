package com.example.MyShot.Activities;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.MyShot.Fragments.AddImageFragment;
import com.example.MyShot.Fragments.HomeFragment;
import com.example.MyShot.Fragments.ProfileFragment;
import com.example.MyShot.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new HomeFragment();

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        // Inizializza il BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Imposta il listener per il BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.action_fragment1:
                    fragment = new HomeFragment();
                    break;

                case R.id.action_fragment2:
                    fragment = new AddImageFragment();
                    break;

                case R.id.action_fragment3:
                    fragment = new ProfileFragment();
                    break;
            }

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .addToBackStack(null)
                        .commit();
            }
            return true;
        });
    }
}


