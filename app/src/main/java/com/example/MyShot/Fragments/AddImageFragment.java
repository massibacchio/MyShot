package com.example.MyShot.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.example.MyShot.Activities.MainActivity;
import com.example.MyShot.Classes.FirebaseWrapper;
import com.example.MyShot.Classes.ImageItem;
import com.example.MyShot.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class AddImageFragment extends LogFragment {
    View externalView;
    private Uri imageUrl;
    private ImageView imageView;
    private EditText imageTitle;
    private EditText imageDescr;
    private boolean valid = false;
    private boolean confirm = false;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private static final String CHILD_USERS = "Users";
    private static final String CHILD_USERNAME = "Username";
    MainActivity mainActivity;
    private String username;



    private static final int REQUEST_IMAGE_PICK = 1001;
    private FirebaseWrapper.Auth.RTDatabase rtDatabase;

    private final static String TAG = FirebaseWrapper.Callback.class.getCanonicalName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initArguments();

        //rtDatabase = new FirebaseWrapper.Auth.RTDatabase();
    }




    private void writeToDatabase() {
        Random random = new Random();
        int imageId = random.nextInt(Integer.MAX_VALUE);
        FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();
        String userEmail = auth.getUser().getEmail();

        mainActivity = (MainActivity) getActivity();

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
                            username = child.child(CHILD_USERNAME).getValue().toString();
                        }
                    }
                    if (imageDescr.getText().toString().isEmpty() || imageTitle.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(), "Error: Empty title or description", Toast.LENGTH_LONG).show();
                    } else {
                        ImageItem imageItem = new ImageItem(imageUrl.toString(), imageId, imageTitle.getText().toString(), userEmail, imageDescr.getText().toString(), username);
                        DatabaseReference imageDatabaseRef = FirebaseWrapper.Auth.RTDatabase.getDb();
                        if (imageDatabaseRef != null) {
                            new FirebaseWrapper.Auth.RTDatabase().writeDbData(imageItem);
                        } else {
                            Log.e(TAG, "Database reference is null.");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        externalView = inflater.inflate(R.layout.fragment_add_image, container, false);
        imageView = externalView.findViewById(R.id.imageView);
        imageTitle = externalView.findViewById(R.id.editTextTitle);
        imageDescr = externalView.findViewById(R.id.editTextDescription);

        Button AddImageButton = externalView.findViewById(R.id.AddImageButton);
        Button ConfirmButton = externalView.findViewById(R.id.ConfirmButton);

        // If I go back from the confirmation, show previously uploaded image
        if (imageUrl != null){
            Picasso.get().load(imageUrl).into(imageView);
        }

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm = true;
                if (valid) {
                    writeToDatabase();
                } else {
                    Toast.makeText(getContext(), "Select an image", Toast.LENGTH_SHORT).show();
                    return;
                }
                int fragmentContainerId = ((ViewGroup) requireView().getParent()).getId();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(fragmentContainerId, new AddImageFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        AddImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm = false;
                if (checkPermission()) {
                    openGallery();
                } else {
                    requestPermission();
                }
            }
        });

        return externalView;
    }



    private boolean checkPermission() {
        int permission = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMAGE_PICK);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_IMAGE_PICK) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                // Handle permission denied
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                imageUrl = data.getData();
                if (imageUrl != null) {
                    Picasso.get().load(imageUrl).into(imageView);
                    valid = true;
                    if (confirm) {
                        writeToDatabase(); // Call to write data to database
                        //writeToDatabase(FirebaseWrapper.Auth.getCurrentUser().getUid());
                    }
                }
            }
        }
    }
}
