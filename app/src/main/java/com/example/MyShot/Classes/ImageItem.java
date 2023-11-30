package com.example.MyShot.Classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ImageItem implements Serializable{

    private String ImageUrl;
    private static int ImageId;
    private String Photographer;
    private String ImageTitle;

    private String ImageContact;
    private String ImageDescr;

    public ImageItem() {}

public ImageItem(String imageUrl, int imageId, String title, String contact, String description, String photographer) {
        this.ImageUrl = imageUrl;
        ImageId = imageId;
        this.ImageDescr = description;
        this.ImageTitle = title;
        this.ImageContact = contact;
        this.Photographer = photographer;

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
        return ImageDescr;

    }
    public String getPhotographer() {
        return Photographer;

    }
    public String getImageContact() {
        return ImageContact;

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
    public void setImageDescription(String imageDescription){

        ImageDescr =  imageDescription;
    }
    public void setImageContact(String imageContact){

        ImageContact =  imageContact;
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
                ImageId == imageItem.ImageId &&
                ImageTitle.equals(imageItem.ImageTitle) &&
                ImageContact.equals(imageItem.ImageContact) &&
                ImageDescr.equals(imageItem.ImageDescr);
        }

    @Override
    public int hashCode() {
        return Objects.hash(ImageUrl, ImageId, Photographer, ImageTitle, ImageContact, ImageDescr);
    }

    public static class Collection<T extends ImageItem> {
        private static final String TAG = Collection.class.getCanonicalName();
        public final List<T> images;

        public Collection(List<T> images) {
            this.images = images;
        }
    }

}


