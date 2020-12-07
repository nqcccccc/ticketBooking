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

public class HistoryAdapter extends ArrayAdapter<ItemHistory> {

    private Context context;
    private int layout;
    private List<ItemHistory> itemHistories;

    public HistoryAdapter(@NonNull Context context, int resource, @NonNull List<ItemHistory> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.itemHistories = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        Holder holder;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(layout, parent, true);

            holder = new HistoryAdapter.Holder();
            holder.tvMName = view.findViewById(R.id.tvMName);
            holder.tvCinema = view.findViewById(R.id.tvCinema);
            holder.tvTime = view.findViewById(R.id.tvTime);
            holder.tvSeat = view.findViewById(R.id.tvSeat);
            holder.tvTicketID = view.findViewById(R.id.tvTicketID);

            //save
            view.setTag(holder);
        } else {
            view = convertView;

            //read
            holder = (HistoryAdapter.Holder) view.getTag();
        }


        ItemHistory itemHistory = itemHistories.get(position);

        holder.tvMName.setText(itemHistory.getMName());
        holder.tvCinema.setText(itemHistory.getCinema());
        holder.tvTime.setText(itemHistory.getTime());
        holder.tvSeat.setText(itemHistory.getSeat());
        holder.tvTicketID.setText(itemHistory.getTicketID());

        return convertView;
    }

    class Holder{
        TextView tvMName;
        TextView tvCinema;
        TextView tvTime;
        TextView tvSeat;
        TextView tvTicketID;
    }
}

