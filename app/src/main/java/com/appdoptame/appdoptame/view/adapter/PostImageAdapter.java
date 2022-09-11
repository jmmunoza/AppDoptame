package com.appdoptame.appdoptame.view.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.appdoptame.appdoptame.AppDoptameApp;
import com.appdoptame.appdoptame.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class PostImageAdapter extends RecyclerView.Adapter<PostImageAdapter.ViewHolder> {

    private List<String> images;
    private final LayoutInflater inflater;
    private final Context context;

    public PostImageAdapter(Context context, List<String> images) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.images = images;
    }

    public PostImageAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    public void setData(List<String> images){
        this.images = images;
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(this::notifyDataSetChanged);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.post_item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageURL = images.get(position);

        Glide.with(AppDoptameApp.getContext())
                .load(imageURL)
                .placeholder(R.drawable.image_placeholder)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.post_item_image_item);
        }
    }
}