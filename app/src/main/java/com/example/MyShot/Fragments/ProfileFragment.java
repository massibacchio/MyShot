package com.example.MyShot.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyShot.Activities.MainActivity;
import com.example.MyShot.Adapters.ImageListAdapter;
import com.example.MyShot.Classes.FirebaseWrapper;
import com.example.MyShot.Classes.ImageItem;
import com.example.MyShot.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProfileFragment extends LogFragment {
    MainActivity mainActivity;

    private ImageView logoImage;
    private TextView usernameTextView;
    private TextView emailTextView;
    private RecyclerView recyclerView;
    private ImageListAdapter adapter;
    private List<ImageItem> imageData;
    private static final String CHILD_IMAGES = "Images";
    private static final String CHILD_USERS = "Users";
    private static final String CHILD_USERNAME = "Username";
    private String username;
    private ImageItem.Collection<ImageItem> queryCollection = null;
    static List<ImageItem> queryList = new ArrayList<ImageItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initArguments();


        DatabaseReference databaseReference = FirebaseDatabase.
                getInstance("https://myshot-5cef3-default-rtdb.europe-west1.firebasedatabase.app/").
                getReference().
                child(CHILD_USERS);

        FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    queryList.clear();
                    for (DataSnapshot child : snapshot.getChildren()) {
                        if (child.getKey().equals(auth.getUid())) {
                            DataSnapshot usernamechild = child.child(CHILD_USERNAME);
                            if (usernamechild.getValue() != null){
                                username= usernamechild.getValue().toString();
                                usernameTextView.setText("User ID:" +username);
                            }
                        }
                    }
                    queryCollection = new ImageItem.Collection<>(queryList);
                    // display the result on the UI through a recycler view
                    RunRecyclerView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //riferimenti alle view nel layout
        logoImage = view.findViewById(R.id.appLogoImageView);
        usernameTextView = view.findViewById(R.id.usernameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        recyclerView = view.findViewById(R.id.profileRecyclerView);

        //istanza di FirebaseWrapper.Auth per accedere all'oggetto utente corrente
        FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();


        DatabaseReference databaseReference = FirebaseDatabase.
                getInstance("https://myshot-5cef3-default-rtdb.europe-west1.firebasedatabase.app/").
                getReference().
                child(CHILD_USERS);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        if (child.getKey().equals(auth.getUid())) {
                            DataSnapshot usernamechild = child.child(CHILD_USERNAME);
                            if (usernamechild.getValue() != null){
                                username= usernamechild.getValue().toString();
                                usernameTextView.setText("User ID:" +username);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        String userEmail = auth.getUser().getEmail();

        if (userEmail != null) {
            
            emailTextView.setText("Email: " + userEmail);
        } else {

            emailTextView.setText("Email not available");
        }


        return view;
    }

    protected void RunRecyclerView() {
        mainActivity = (MainActivity) getActivity();
        if (recyclerView != null) {
            LinearLayoutManager manager = new LinearLayoutManager(mainActivity);
            recyclerView.setLayoutManager(manager);
            recyclerView.setHasFixedSize(true);
            ImageListAdapter adapter = new ImageListAdapter(Arrays.asList(queryCollection.images.toArray(new ImageItem[queryCollection.images.size()])));
            recyclerView.setAdapter(adapter);
        }
    }
}

