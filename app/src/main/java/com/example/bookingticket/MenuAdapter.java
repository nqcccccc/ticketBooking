package com.example.bookingticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MenuAdapter extends ArrayAdapter<ItemMenu> {

    private Context context;
    private int layout;
    private List<ItemMenu> data;

    public MenuAdapter(Context context, int resource, List<ItemMenu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View preView, @NonNull ViewGroup parent) {
        View view;
        Holder holder;

        if(preView == null){
            view = LayoutInflater.from(context).inflate(layout,parent,false);

            holder = new Holder();
            holder.img = view.findViewById(R.id.imgIcon);
            holder.tv =  view.findViewById(R.id.tvIName);

            //save
            view.setTag(holder);
        }else{
            view = preView;

            //read
            holder =(Holder) view.getTag();
        }


        ItemMenu itemMenu = data.get(position);

        holder.img.setImageResource(itemMenu.getItemIcon());
        holder.tv.setText(itemMenu.getItemName());

        return view;
    }

    static class Holder{
        ImageView img;
        TextView tv;
    }


}
