package com.example.MyShot.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.MyShot.Activities.EnterActivity;
import com.example.MyShot.Classes.FirebaseWrapper;
import com.example.MyShot.R;


public class LoginFragment extends LogFragment {
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // associo il file di layout all'interno della vista,
        // layout--->container (generato nell'activity)
        View externalView = inflater.inflate(R.layout.fragment_login, container, false);

        //recupero dalla vista l'oggetto 'switchLogin..' tramite identificativo
        // aggiungiamo a questa testview l'Onclick per reagire al tap e passare al SignUp
        TextView link = externalView.findViewById(R.id.switchLoginToRegisterLabel);

        //dal login frag voglio andare al frag di signup
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EnterActivity)LoginFragment.this.requireActivity()).renderFragment(false);
            }
        });

        Button button = externalView.findViewById(R.id.logButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = externalView.findViewById(R.id.userEmail);
                EditText password = externalView.findViewById(R.id.userPassword);

                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    // TODO: Better error handling + remove this hardcoded strings
                    email.setError("Email is required");
                    password.setError("Password is required");
                    return;
                }

                // fa il log-in leggendo i campi dagli edittext e chiamo il metodo di firebase

                FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();
                auth.signIn(
                        email.getText().toString(),
                        password.getText().toString(),
                        FirebaseWrapper.Callback
                                .newInstance(LoginFragment.this.requireActivity(),
                                        LoginFragment.this.callbackName,
                                        LoginFragment.this.callbackPrms)
                );
            }
        });

        return externalView;
    }
}




