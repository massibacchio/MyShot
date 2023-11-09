package com.example.MyShot.Classes;

import android.net.Uri;

public class ImageItem {

    private final Uri imageResource; // Riferimento all'immagine (ad esempio, un ID di risorsa)
    private final String text; // Testo associato all'immagine

    public ImageItem(Uri imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
    }

    public Uri getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }

}


