package com.example.bookingticket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PosterAdapter extends PagerAdapter{

    private List<ItemPoster> data;
    private Context context;
    LayoutInflater layoutInflater;

    public PosterAdapter(List<ItemPoster> data, Context context) {
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
        View view = layoutInflater.inflate(R.layout.poster_layout,container,false);

        RoundedImageView rimgPoster = view.findViewById(R.id.rimgPoster);


        String url = data.get(position).getPoster();
        Picasso.get().load(url).into(rimgPoster);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Poster id:"+data.get(position).getId().toString(),Toast.LENGTH_LONG).show();
            }
        });

        container.addView(view);
        return view;
    }
}
