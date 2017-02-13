package com.example.heinhtet.newsreader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heinhtet on 2/13/17.
 */
public class GoogleCustomAdapter extends RecyclerView.Adapter<GoogleCustomAdapter.ViewHolder> {
    Context mContext;
    List<Google> data;

    public GoogleCustomAdapter(Context googleFragmentView, List<Google> googles) {
       data = googles;
        this.mContext = googleFragmentView;

    }

    @Override
    public GoogleCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View googleView = LayoutInflater.from(mContext).inflate(R.layout.items_lists,parent,false);
        ViewHolder holder = new ViewHolder(googleView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        data = new ArrayList<>();
        Google googles = data.get(position);
        holder.title.setText(googles.getmTitle());
        holder.description.setText(googles.getmDescription());
        holder.publishedAt.setText(googles.getmPublishedAt());
        Picasso.with(mContext).load(googles.getmImageUrl()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView author;
        ImageView image;
        TextView publishedAt;


        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.TV_title);
            description = (TextView)itemView.findViewById(R.id.TV_description);
            author = (TextView)itemView.findViewById(R.id.TV_author);
            image = (ImageView)itemView.findViewById(R.id.imageView);
            publishedAt = (TextView)itemView.findViewById(R.id.time);


        }
    }
}
