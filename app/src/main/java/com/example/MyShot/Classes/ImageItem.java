package com.example.MyShot.Classes;

public class ImageItem {

    private final int imageResource; // Riferimento all'immagine (ad esempio, un ID di risorsa)
    private final String text; // Testo associato all'immagine

    public ImageItem(int imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }

}


