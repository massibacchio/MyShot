package com.example.MyShot.Classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ImageItem implements Serializable{

    private String ImageUrl;
    private static int ImageId;
    // private static int ImageId;
    private String Description;
    private String Author;

    public ImageItem() {}

public ImageItem(String imageUrl, int imageId, String description, String author) {
        this.ImageUrl = imageUrl;
        this.ImageId = imageId;
        this.Description = description;
        this.Author = author;

    }

    //getter and setter
    public String getImageUrl() {
        return ImageUrl;

    }
    public static int getImageId() {
        return ImageId;

    }
    public String getDescription() {
        return Description;

    }
    public String getAuthor() {
        return Author;

    }


    void setImageUrl(String imageUrl){

        ImageUrl = imageUrl;
    }
    void setImageId(int imageId){

        ImageId =  imageId;
    }
    void setDescription(String description){

        Description =  description;
    }
    void setAuthor(String author){
        Author =  author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageItem imageItem = (ImageItem) o;
        return ImageUrl.equals(imageItem.ImageUrl) &&
                Author.equals(imageItem.Author) &&
                Description.equals(imageItem.Description) &&
                ImageId == imageItem.ImageId;
        }

    @Override
    public int hashCode() {
        return Objects.hash(ImageUrl, ImageId, Description, Author);
    }

    public static class Collection<T extends ImageItem> {
        private static final String TAG = Collection.class.getCanonicalName();
        public final List<T> images;

        public Collection(List<T> images) {
            this.images = images;
        }
    }

}


