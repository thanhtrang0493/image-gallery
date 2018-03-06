package com.mobiclixgroup.image_gallery.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mobiclixgroup.image_gallery.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListImageAdapter extends RecyclerView.Adapter<ListImageAdapter.ListImageHolder> {

    List<String> imageList = new ArrayList<>();
    Context context;

    public ListImageAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
        if (this.imageList == null)
            this.imageList = new ArrayList<>();
    }

    public void updateAdapter(List<String> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @Override
    public ListImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_image, parent, false);
        return new ListImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ListImageHolder holder, int position) {
        Picasso.with(context).load(imageList.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ListImageHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ListImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}



