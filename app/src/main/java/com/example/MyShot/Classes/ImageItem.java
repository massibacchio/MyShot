package com.example.MyShot.Classes;

public class ImageItem {

        private final String imagepath; // Il percorso o l'URI dell'immagine dalla galleria

        public ImageItem(String imagePath) {
            this.imagepath = imagePath;
        }

        public String getImagePath() {
            return imagepath;
        }
    }


