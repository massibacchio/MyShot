package com.example.MyShot.Activities;


import static com.example.MyShot.Adapters.ImageListAdapter.pics;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyShot.Adapters.ImageListAdapter;
import com.example.MyShot.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //riferimento a recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //definisco un layout manager (linear)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<String> data = new ArrayList<>();
        ImageListAdapter adapter = new ImageListAdapter(pics);
        recyclerView.setAdapter(adapter);
    }


    public String getRealPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();

            // Il percorso potrebbe essere già il percorso del file
        } else {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String filePath = cursor.getString(column_index);
            cursor.close();
            return filePath;
        }
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            String imagePath = getRealPathFromURI(selectedImageUri);
            // Ora hai il percorso del file dell'immagine selezionata.

            // Passa imagePath all'adapter ImageListAdapter per visualizzare l'immagine nella RecyclerView.
            ImageListAdapter.addImage(imagePath); // Supponendo che l'adapter abbia un metodo per aggiungere un'immagine.
        }
    }
    /*

     */


}








