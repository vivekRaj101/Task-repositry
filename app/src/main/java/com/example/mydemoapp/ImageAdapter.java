package com.example.mydemoapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
//    private Context context;

    private List<String> imagePathList;

    public ImageAdapter(List<String> imagePathList) {
        this.imagePathList= imagePathList;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "getItemCount: imagepathSize11. "+imagePathList.get(position));
        holder.image.setImageURI(Uri.parse(imagePathList.get(position)));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: imagepathSize11. "+imagePathList.size());
        return imagePathList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
        }
    }
}

