package com.example.bookingticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bookingticket.Retrofit2.APIUtils;
import com.example.bookingticket.Retrofit2.DataClient;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    private LinearLayout navigationView;
    private ListView lvNavigation,lvNews;
    private ImageButton btnMenu;
    private MenuAdapter menuAdapter;
    private ImageSlider imgSlider;
    private ImageView imgAva;
    private TextView tvCName;
    private HorizontalInfiniteCycleViewPager vp2Poster;
    private ArrayList<Account> arrayUser = null;
    private int user;
    private ArrayList<Poster> arrayPoster = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
        initData();
        actionToolbar();
        initMenuData();
        slider();
        poster();
        news();
    }
    //Start init
    private void init() {
        drawerLayout= findViewById(R.id.drawerLayout);

        toolBar = findViewById(R.id.toolBar);

        navigationView = findViewById(R.id.navigationView);

        lvNavigation = findViewById(R.id.lvNavigation);
        lvNews = findViewById(R.id.lvNews);

        btnMenu = findViewById(R.id.btnMenu);

        imgSlider = findViewById(R.id.imgSlider);
        imgAva = findViewById(R.id.imgAva);

        tvCName = findViewById(R.id.tvCName);

        vp2Poster = (HorizontalInfiniteCycleViewPager) findViewById(R.id.vp2Poster);

        btnMenu.setOnClickListener(this);
    }
    //End init

    //Get Intent data
    private void initData() {
        Intent intent = getIntent();
        arrayUser = intent.getParcelableArrayListExtra("arrayUser");

        user = Integer.parseInt(arrayUser.get(0).getId());

        tvCName.setText(arrayUser.get(0).getUser());
        Picasso.get().load(arrayUser.get(0).getAvatar()).into(imgAva);
    }
    // Click event
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMenu:
                openMenu();
                break;
        }
    }
    // End click event

    //Start menu
    private void openMenu() {
        drawerLayout.openDrawer(GravityCompat.END);
    }
    private void initMenuData() {
        List<ItemMenu> arrayList = new ArrayList<>();
        arrayList.add(new ItemMenu("History",R.drawable.ic_baseline_history_24));
        arrayList.add(new ItemMenu("Logout",R.drawable.ic_round_reply_24));
        arrayList.add(new ItemMenu("App Info",R.drawable.ic_baseline_info_24));

        menuAdapter = new MenuAdapter(this,R.layout.item_row,arrayList);
        lvNavigation.setAdapter(menuAdapter);

        lvNavigation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (arrayList.get(i).getItemName().equals("History") ){
                    Intent intent = new Intent(DashboardActivity.this,HistoryActivity.class);
                    intent.putExtra("arrayPoster",arrayPoster);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }else if (arrayList.get(i).getItemName().equals("Logout") ){
                    finish();
                    Intent intent = new Intent(DashboardActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(DashboardActivity.this,AppInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void actionToolbar() {
        setSupportActionBar(toolBar);
        toolBar.setTitle("Movie ticket booking");
    }
    //End menu
    // Start slider
    private void slider() {
        DataClient dataClient = APIUtils.getData();
        Call<List<Slider>> callback = dataClient.getSlider(1);
        callback.enqueue(new Callback<List<Slider>>() {
            @Override
            public void onResponse(Call<List<Slider>> call, Response<List<Slider>> response) {
                ArrayList<Slider> arraySlider = (ArrayList<Slider>) response.body();
                List<SlideModel> data = new ArrayList<>();
                if(arraySlider.size()>0){
                    for(int i=0;i<arraySlider.size();i++){
                        data.add(new SlideModel(arraySlider.get(i).getBanner()));
                    }
                }else{
                    Log.d("TAG", "Empty ");
                }
                imgSlider.setImageList(data,true);
            }
            @Override
            public void onFailure(Call<List<Slider>> call, Throwable t) {
                Log.d("TAG", t.getMessage());
            }
        });
    }
    //End slider

    //Start poster
    private void poster() {
        DataClient dataClient = APIUtils.getData();
        Call<List<Poster>> callback = dataClient.getPoster(1);
        callback.enqueue(new Callback<List<Poster>>() {
            @Override
            public void onResponse(Call<List<Poster>> call, Response<List<Poster>> response) {
                arrayPoster = (ArrayList<Poster>) response.body();
                List<ItemPoster> data = new ArrayList<>();
                if(arrayPoster.size()>0){
                    for(int i=0;i<arrayPoster.size();i++){
                        data.add(new ItemPoster(arrayPoster.get(i).getPoster(),Integer.parseInt(arrayPoster.get(i).getId())));
                    }
                }else{
                    Log.d("TAG","Empty ");
                }
                PosterAdapter adapter = new PosterAdapter(data,user,getBaseContext());
                vp2Poster.setAdapter(adapter);
                vp2Poster.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Poster>> call, Throwable t) {

            }
        });
    }
    // end poster

    //Start news
    private void news() {
        DataClient dataClient = APIUtils.getData();
        Call<List<News>> call = dataClient.getNews(1);
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                ArrayList<News> arrayList = (ArrayList<News>) response.body();
                List<ItemNews> data = new ArrayList<>();
                if(arrayList.size()>0){
                    for (int i = 0 ; i<arrayList.size();i++){
                        data.add(new ItemNews(arrayList.get(i).getTittle(),arrayList.get(i).getImg()));
                    }
                }
                NewsAdapter newsAdapter = new NewsAdapter(DashboardActivity.this,R.layout.news_row,data);
                lvNews.setAdapter(newsAdapter);
                lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(DashboardActivity.this,NewsActivity.class);
                        intent.putExtra("arrayList",arrayList);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });





    }
    //End news
}