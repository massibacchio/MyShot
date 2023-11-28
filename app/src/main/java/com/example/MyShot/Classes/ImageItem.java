package com.example.MyShot.Classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ImageItem implements Serializable{

    private String ImageUrl;
    private static int ImageId;
    private String ImageDescription;
    private String Photographer;
    private String ImageTitle;

    public ImageItem() {}

public ImageItem(String imageUrl, int imageId, String imageDescription, String title) {
        this.ImageUrl = imageUrl;
        ImageId = imageId;
        this.ImageDescription = imageDescription;
        this.ImageTitle = title;

    }

    //getter and setter
    public String getImageUrl() {
        return ImageUrl;

    }

    public String getImageTitle() {
        return ImageTitle;

    }
    public static int getImageId() {
        return ImageId;

    }
    public String getImageDescription() {
        return ImageDescription;

    }
    public String getPhotographer() {
        return Photographer;

    }
    public void setImageUrl(String imageUrl){

        ImageUrl = imageUrl;
    }

    public void setImageTitle(String imageTitle){

        ImageTitle = imageTitle;
    }
    public void setImageId(int imageId){

        ImageId =  imageId;
    }
    public void setDescription(String imageDescription){

        ImageDescription =  imageDescription;
    }
    public void setPhotographer(String photographer){
        Photographer =  photographer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageItem imageItem = (ImageItem) o;
        return ImageUrl.equals(imageItem.ImageUrl) &&
                Photographer.equals(imageItem.Photographer) &&
                ImageDescription.equals(imageItem.ImageDescription) &&
                ImageId == imageItem.ImageId &&
                ImageTitle.equals(imageItem.ImageTitle);
        }

    @Override
    public int hashCode() {
        return Objects.hash(ImageUrl, ImageId, ImageDescription, Photographer, ImageTitle);
    }

    public static class Collection<T extends ImageItem> {
        private static final String TAG = Collection.class.getCanonicalName();
        public final List<T> images;

        public Collection(List<T> images) {
            this.images = images;
        }
    }

}


