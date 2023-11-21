package com.example.MyShot.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.MyShot.Activities.MainActivity;
import com.example.MyShot.Adapters.ImageListAdapter;
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


public class HomeFragment extends LogFragment {
    MainActivity mainActivity;

    View externalView;
    private RecyclerView recyclerView;
    private ImageListAdapter adapter;
    private List<ImageItem> imageData;
    private static final String CHILD_IMAGES = "Images";
    private static final String CHILD_USERS = "Users";

    private ImageItem.Collection<ImageItem> queryCollection = null;
    static List<ImageItem> queryList = new ArrayList<ImageItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initArguments();

        mainActivity = (MainActivity) getActivity();

        // we have to discern whether the user has set the filters or not
        // check if the filter values are equal to min/max values
        // if they are equal, show the 5 latest Ads
        // if not, do a search based on the filter values, but bear in mind that he/she hasn't inserted any string in the search bar yet
        DatabaseReference databaseReference = FirebaseDatabase.
                getInstance("https://myshot-5cef3-default-rtdb.europe-west1.firebasedatabase.app/").
                getReference().
                child(CHILD_USERS);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    queryList.clear();
                    for (DataSnapshot child : snapshot.getChildren()) {
                        Iterable<DataSnapshot> ImageChildren = child.child(CHILD_IMAGES).getChildren();
                        for (DataSnapshot childIm : ImageChildren) {
                            ImageItem img = childIm.getValue(ImageItem.class);
                            queryList.add(img);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        externalView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = externalView.findViewById(R.id.recyclerView);

        return externalView;
    }

    //
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