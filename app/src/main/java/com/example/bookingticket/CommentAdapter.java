package com.example.bookingticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends ArrayAdapter<ItemComment> {
    private Context context;
    private int layout;
    private List<ItemComment> data;

    public CommentAdapter(@NonNull Context context, int resource, @NonNull List<ItemComment> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        CommentAdapter.Holder holder;

        if(convertView == null){
            view = LayoutInflater.from(context).inflate(layout,parent,false);

            holder = new Holder();
            holder.img = view.findViewById(R.id.imgCAva);
            holder.name =  view.findViewById(R.id.tvName);
            holder.cmt = view.findViewById(R.id.tvCMT);
            holder.rating = view.findViewById(R.id.rtbCmt);

            //save
            view.setTag(holder);
        }else{
            view = convertView;

            //read
            holder = (Holder) view.getTag();
        }


        ItemComment itemComment = data.get(position);
        Picasso.get().load(itemComment.getAva()).into(holder.img);
        holder.name.setText(itemComment.getName());
        holder.rating.setRating(itemComment.getRating());
        holder.cmt.setText(itemComment.getComment());

        return view;
    }

    static class Holder{
        CircleImageView img;
        TextView cmt,name;
        RatingBar rating;
    }
}
