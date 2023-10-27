package com.example.MyShot.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MyShot.Activities.EnterActivity;
import com.example.MyShot.Classes.FirebaseWrapper;
import com.example.MyShot.R;


public class SignupFragment extends LogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initArguments();

    }

    //oncreateview associa al frag la sua vista
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

        Button button = externalView.findViewById(R.id.logButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = externalView.findViewById(R.id.userEmail);
                EditText password = externalView.findViewById(R.id.userPassword);
                EditText password2 = externalView.findViewById(R.id.userPasswordAgain);

                if (email.getText().toString().isEmpty() ||
                        password.getText().toString().isEmpty() ||
                        password2.getText().toString().isEmpty()) {
                    // TODO: Better error handling + remove this hardcoded strings
                    email.setError("Email is required");
                    password.setError("Password is required");
                    password2.setError("Password is required");
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

                // Perform SignIn
                FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();
                auth.signUp(
                        email.getText().toString(),
                        password.getText().toString(),
                        FirebaseWrapper.Callback
                                .newInstance(SignupFragment.this.requireActivity(),
                                        SignupFragment.this.callbackName,
                                        SignupFragment.this.callbackPrms)
                );
            }
        });

        return externalView;
        }
    }