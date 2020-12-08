package com.example.bookingticket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bookingticket.Retrofit2.APIUtils;
import com.example.bookingticket.Retrofit2.DataClient;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryAdapter extends PagerAdapter {
    private List<History> data;
    private Context context;
    LayoutInflater layoutInflater;

    public HistoryAdapter(List<History> data, Context context) {
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.card_item,container,false);

        ImageView imgHistory = view.findViewById(R.id.imgHistory);
        TextView tvHName = view.findViewById(R.id.tvHName);
        TextView tvHDate = view.findViewById(R.id.tvHDate);
        TextView tvHSeat = view.findViewById(R.id.tvHSeat);

        tvHDate.setText("Date: "+data.get(position).getDate());
        tvHName.setText("Movie: "+data.get(position).getMovie_name());
        tvHSeat.setText("Seat: "+data.get(position).getSeat());
        Picasso.get().load(data.get(position).getImg()).into(imgHistory);


        container.addView(view);
        return view;
    }

}
