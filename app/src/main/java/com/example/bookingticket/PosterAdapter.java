package com.example.bookingticket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHoler>{

    private List<ItemPoster> data;
    private ViewPager2 viewPager2;

    public PosterAdapter(List<ItemPoster> data, ViewPager2 viewPager2) {
        this.data = data;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public PosterViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHoler(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.poster_layout,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHoler holder, int position) {
        holder.setPoster(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PosterViewHoler extends RecyclerView.ViewHolder{

        private RoundedImageView rimgPoster;

        PosterViewHoler(@NonNull View itemView) {
            super(itemView);
            rimgPoster = itemView.findViewById(R.id.rimgPoster);
        }

        void setPoster(ItemPoster itemPoster) {
            rimgPoster.setImageResource(itemPoster.getPoster());
        }
    }
}
