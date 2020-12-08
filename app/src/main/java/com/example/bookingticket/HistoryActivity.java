package com.example.bookingticket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingticket.Retrofit2.APIUtils;
import com.example.bookingticket.Retrofit2.DataClient;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    private HorizontalInfiniteCycleViewPager vpHistory;
    private int user_id;
    private ArrayList<Poster> arrayPoster = null;
    private List<History> data = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        vpHistory = findViewById(R.id.vpHistory);

        Intent intent = getIntent();
        arrayPoster = intent.getParcelableArrayListExtra("arrayPoster");
        user_id = intent.getIntExtra("user",0);

        Log.d("TAG", "onCreate: "+user_id);
        Log.d("TAG", "onCreate: "+arrayPoster.get(0).getPoster());


        setHistory();
    }

    private void setHistory() {
        DataClient dataClient = APIUtils.getData();
        Call<List<BookInfo>> callback = dataClient.getBookID(user_id);
        callback.enqueue(new Callback<List<BookInfo>>() {
            @Override
            public void onResponse(Call<List<BookInfo>> call, Response<List<BookInfo>> response) {
                ArrayList<BookInfo> arrayBook = (ArrayList<BookInfo>) response.body();
                if(arrayBook.size()>0){
                    for(int i=0;i<arrayBook.size();i++){
                        int finalI = i;
                        DataClient dataClient1 = APIUtils.getData();
                        Call<List<Poster>> callback = dataClient1.getHistoryPoster(arrayBook.get(finalI).getMovieName());
                        callback.enqueue(new Callback<List<Poster>>() {
                            @Override
                            public void onResponse(Call<List<Poster>> call, Response<List<Poster>> response) {
                                ArrayList<Poster> arrayPoster = (ArrayList<Poster>) response.body();
                                data.add(new History(arrayBook.get(finalI).getMovieName(),arrayBook.get(finalI).getSeat(),arrayBook.get(finalI).getDate(),arrayPoster.get(0).getPoster()));
                                Log.d("TAG", "onResponse: "+arrayPoster.get(0).getPoster());
                                Log.d("TAG", "onResponse: "+data);
                                HistoryAdapter adapter = new HistoryAdapter(data,getBaseContext());
                                vpHistory.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Call<List<Poster>> call, Throwable t) {

                            }
                        });
                    }
                }else{
                    Log.d("TAG","Empty ");
                }
            }

            @Override
            public void onFailure(Call<List<BookInfo>> call, Throwable t) {

            }
        });

    }

}
