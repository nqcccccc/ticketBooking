package com.example.bookingticket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bookingticket.Retrofit2.APIUtils;
import com.example.bookingticket.Retrofit2.DataClient;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                DataClient dataClient = APIUtils.getData();
                Call<List<MoiveInfo>> callback = dataClient.getMoive(data.get(position).getId());
                callback.enqueue(new Callback<List<MoiveInfo>>() {
                    @Override
                    public void onResponse(Call<List<MoiveInfo>> call, Response<List<MoiveInfo>> response) {
                        ArrayList<MoiveInfo> arrayMovie = (ArrayList<MoiveInfo>) response.body();

                        Intent intent = new Intent(context,MovieActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("arrayMovie",arrayMovie);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<List<MoiveInfo>> call, Throwable t) {
                        Log.d("TAG", "onFailure: "+t.getMessage());
                    }
                });


            }
        });

        container.addView(view);
        return view;
    }
}
