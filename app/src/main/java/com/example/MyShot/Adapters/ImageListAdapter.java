package com.example.MyShot.Adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyShot.Classes.ImageItem;
import com.example.MyShot.R;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.MyViewHolder> {

    public static List<ImageItem> pics;

    public ImageListAdapter(List<ImageItem> data) {
        this.pics = pics;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
/*
        // Carica e visualizza l'immagine nell'elemento della RecyclerView
        ImageItem imageitem = pics.get(position);
        String imagePath = getRealPathFromURI(this.imagepath);

 */

        // Carica l'immagine nell'ImageView nell'elemento della RecyclerView
        // Puoi utilizzare librerie come Glide o Picasso per semplificare questo processo.
    }


    @Override
    public int getItemCount() {
        return pics.size();
    }

/*
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
    public void addImage(String imagepath) {
        pics.add(imagepath);
        notifyDataSetChanged(); // Aggiorna la RecyclerView per riflettere i cambiamenti.
    }
}

 */

}