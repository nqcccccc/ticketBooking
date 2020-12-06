package com.example.bookingticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<ItemHistory> {

    private Context context;
    private int resource;
    private List<ItemHistory> itemHistories;

    public HistoryAdapter(@NonNull Context context, int resource, @NonNull List<ItemHistory> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.itemHistories = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.history_row,parent, true);

        TextView tvName = convertView.findViewById(R.id.tvMName);
        TextView tvCinema = convertView.findViewById(R.id.tvCinema);
        TextView tvTime = convertView.findViewById(R.id.tvTime);
        TextView tvSeat = convertView.findViewById(R.id.tvSeat);
        TextView tvTicketID = convertView.findViewById(R.id.tvTicketID);

        ItemHistory itemHistory = itemHistories.get(position);

        return convertView;
    }
}
