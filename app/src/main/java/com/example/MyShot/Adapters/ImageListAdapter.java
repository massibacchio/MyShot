package com.example.MyShot.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MyShot.Classes.ImageItem;
import com.example.MyShot.R;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    private List<ImageItem> imageData;
    private Context context;

    public ImageListAdapter(List<ImageItem> imageData) {
        this.imageData = imageData;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageItem imageItem = imageData.get(position);
        Glide.with(context)
                .load(imageItem.getImageUrl())
                .override(700, 400)
                .into(holder.imageView);

        //holder.photographerTextView.setText("Photographer: " + imageItem.getPhotographer());  //TODO: fixare
        holder.descriptionTextView.setText("Description: " + imageItem.getImageDescription());
        holder.titleTextView.setText("Title: " + imageItem.getImageTitle());
       // holder.photographerTextView.setText("Photographer: " + ); // Utilizza lo username fornito
        //holder.contactTextView.setText("Contact: " + email); // Utilizza l'email fornita
    }

    @Override
    public int getItemCount() {
        return imageData.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView photographerTextView;
        TextView descriptionTextView;
        TextView contactTextView;
        TextView titleTextView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            //photographerTextView = itemView.findViewById(R.id.photographerTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            //contactTextView = itemView.findViewById(R.id.contactTextView);

        }
    }
}


