package com.example.MyShot.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MyShot.Activities.EnterActivity;
import com.example.MyShot.Activities.MainActivity;
import com.example.MyShot.Classes.FirebaseWrapper;
import com.example.MyShot.R;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.TimeUnit;


public class SignupFragment extends LogFragment {

    EnterActivity enterActivity;
    private final static String TAG = FirebaseWrapper.Callback.class.getCanonicalName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initArguments();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View externalView = inflater.inflate(R.layout.fragment_signup, container, false);

        // Inflate the layout for this fragment
        TextView link = externalView.findViewById(R.id.switchRegisterToLoginLabel);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EnterActivity) SignupFragment.this.requireActivity()).renderFragment(true);
            }
        });


        Button button = externalView.findViewById(R.id.SignupButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = externalView.findViewById(R.id.userEmail);
                EditText password = externalView.findViewById(R.id.userPassword);
                EditText password2 = externalView.findViewById(R.id.userPasswordAgain);
                EditText username = externalView.findViewById(R.id.userName);

                if (email.getText().toString().isEmpty() ||
                        password.getText().toString().isEmpty() ||
                        password2.getText().toString().isEmpty()) {

                    // TODO: Better error handling + remove this hardcoded strings
                    email.setError("Email is required");
                    password.setError("Password is required");
                    password2.setError("Password is required");
                    username.setError("Username is required");
                    return;
                }

                if (!password.getText().toString().equals(password2.getText().toString())) {

                    // TODO: Better error handling + remove this hardcoded strings
                    Toast
                            .makeText(SignupFragment.this.requireActivity(), "Passwords are different", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (!password.getText().toString().equals(password2.getText().toString())) {

                    // TODO: Better error handling + remove this hardcoded strings
                    Toast
                            .makeText(SignupFragment.this.requireActivity(), "Passwords are different", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                // Perform SignUp
                FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();

                auth.signUp(
                        email.getText().toString(),
                        password.getText().toString(),
                        FirebaseWrapper.Callback
                                .newInstance(SignupFragment.this.requireActivity(),
                                        SignupFragment.this.callbackName,
                                        SignupFragment.this.callbackPrms)
                );

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                //Create the bundle to pass the username
                Bundle bundle = new Bundle();
                bundle.putString("username", username.getText().toString());

                //Create fragment and set the bundle
                LoginFragment loginFragment = new LoginFragment();
                loginFragment.setArguments(bundle);

                if (username != null) {
                    DatabaseReference imageDatabaseRef = FirebaseWrapper.Auth.RTDatabase.getDb();
                    if (imageDatabaseRef != null) {
                        new FirebaseWrapper.Auth.RTDatabase().writeDbUsername(username.getText().toString());
                    } else {
                        Log.e(TAG, "Database reference is null.");
                    }
                } else {
                    Log.e(TAG, "Username is null.");
                }


                enterActivity = (EnterActivity) getActivity();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    requireContext().startActivity(intent);




            }
        });

        return externalView;
    }
}





