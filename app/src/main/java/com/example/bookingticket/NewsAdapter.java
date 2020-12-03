package com.example.bookingticket;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<ItemNews> {

    private Context context;
    private int layout;
    private List<ItemNews> data;

    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<ItemNews> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        NewsAdapter.Holder holder;

        if(convertView == null){
            view = LayoutInflater.from(context).inflate(layout,parent,false);

            holder = new NewsAdapter.Holder();
            holder.img = view.findViewById(R.id.imgNews);
            holder.tittle =  view.findViewById(R.id.tvTittleNews);

            //save
            view.setTag(holder);
        }else{
            view = convertView;

            //read
            holder =(NewsAdapter.Holder) view.getTag();
        }


        ItemNews itemNews = data.get(position);

        holder.img.setImageResource(itemNews.getImg());
        holder.tittle.setText(itemNews.getTittle());

        return view;
    }

    static class Holder{
        ImageView img;
        TextView tittle;
    }
}
