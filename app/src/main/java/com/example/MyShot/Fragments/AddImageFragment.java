package com.example.MyShot.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.MyShot.Classes.FirebaseWrapper;
import com.example.MyShot.Classes.ImageItem;
import com.example.MyShot.R;


public class AddImageFragment extends LogFragment {

    View externalView;
    private static final int REQUEST_IMAGE_PICK = 1001;
    private FirebaseWrapper.Auth.RTDatabase rtDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        externalView = inflater.inflate(R.layout.fragment_add_image, container, false);

        Button AddImageButton = externalView.findViewById(R.id.AddImageButton);
        AddImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    openGallery();
                } else {
                    requestPermission();
                }
            }
        });

        return externalView;
    }

    // Verifica se il permesso di accesso alla galleria è stato concesso
    private boolean checkPermission() {

        int permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    // Richiedi il permesso di accesso alla galleria
    private void requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMAGE_PICK);
    }

    // Apri la galleria per selezionare un'immagine
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    // Gestisci la risposta alla richiesta dei permessi
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_IMAGE_PICK) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                // Permesso negato
                // Puoi gestire questo caso come preferisci
            }
        }
    }

    // Gestisci il risultato dell'apertura della galleria
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    // Ottieni l'immagine selezionata dall'URI
                    // Esegui il caricamento nel database
                    // Assicurati di avere l'oggetto RTDatabase inizializzato
                    ImageItem imageItem = new ImageItem(selectedImageUri,""); // Crea un oggetto ImageItem con i dati dell'immagine
                    rtDatabase.writeDbData(imageItem);
                }
            }
        }
    }

}