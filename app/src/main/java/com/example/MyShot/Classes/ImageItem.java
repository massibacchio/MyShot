package com.example.MyShot.Classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ImageItem implements Serializable{

    private String ImageUrl;
    private static int ImageId;

    public ImageItem() {}

    public ImageItem(String imageUrl, int imageId) {
        this.ImageUrl = imageUrl;
        this.ImageId = imageId;

    }

    //getter and setter
    public String getImageUrl() {
        return ImageUrl;

    }
    public static int getImageId() {
        return ImageId;

    }


    void setImageUrl(String imageUrl){
        ImageUrl = imageUrl;
    }
    void setImageId(int imageId){
        ImageId =  imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageItem imageItem = (ImageItem) o;
        return ImageUrl.equals(imageItem.ImageUrl);
        }

    @Override
    public int hashCode() {
        return Objects.hash(ImageUrl, ImageId);
    }

    public static class Collection<T extends ImageItem> {
        private static final String TAG = Collection.class.getCanonicalName();
        public final List<T> images;

        public Collection(List<T> images) {
            this.images = images;
        }
    }

}


