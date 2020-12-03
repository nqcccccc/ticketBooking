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

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    private LinearLayout navigationView;
    private ListView lvNavigation,lvNews;
    private ImageButton btnMenu;
    private MenuAdapter menuAdapter;
    private ViewPager vpSlider;
    private ViewPager2 vp2Poster;

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
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

        vpSlider = findViewById(R.id.vpSlider);
        vp2Poster = findViewById(R.id.vp2Poster);

        btnMenu.setOnClickListener(this);
    }
    //End init

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
    }

    private void actionToolbar() {
        setSupportActionBar(toolBar);
        toolBar.setTitle("Movie ticket booking");
    }
    //End menu
    // Start slider
    private void slider() {
        SliderAdapter sliderAdapter = new SliderAdapter(this);
        vpSlider.setAdapter(sliderAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),2000,4000);
    }

    public class SliderTimer extends TimerTask{

        @Override
        public void run() {
            DashboardActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (vpSlider.getCurrentItem() == 0){
                        vpSlider.setCurrentItem(1);
                    }else if (vpSlider.getCurrentItem() == 1){
                        vpSlider.setCurrentItem(2);
                    }else if(vpSlider.getCurrentItem() == 2){
                        vpSlider.setCurrentItem(0);
                    }
                }
            });
        }
    }
    //End slider

    //Start poster
    private void poster() {
        List<ItemPoster> data = new ArrayList<>();
        data.add(new ItemPoster(R.drawable.poster1));
        data.add(new ItemPoster(R.drawable.poster2));
        data.add(new ItemPoster(R.drawable.poster3));

        vp2Poster.setAdapter(new PosterAdapter(data,vp2Poster));

        vp2Poster.setClipToPadding(false);
        vp2Poster.setClipChildren(false);
        vp2Poster.setOffscreenPageLimit(3);
        vp2Poster.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(5));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) {
                    view.setAlpha(0f);

                } else if (position <= 1) {
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationX(horzMargin - vertMargin / 2);
                    } else {
                        view.setTranslationX(-horzMargin + vertMargin / 2);
                    }

                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else {
                    view.setAlpha(0f);
                }
            }
        });

        vp2Poster.setPageTransformer(compositePageTransformer);
    }
    // end poster

    //Start news
    private void news() {
        List<ItemNews> data = new ArrayList<>();
        data.add(new ItemNews("History",R.drawable.ic_baseline_history_24));
        data.add(new ItemNews("Logout",R.drawable.ic_round_reply_24));
        data.add(new ItemNews("App Info",R.drawable.ic_baseline_info_24));
        data.add(new ItemNews("App Info",R.drawable.ic_baseline_info_24));
        data.add(new ItemNews("App Info",R.drawable.ic_baseline_info_24));
        data.add(new ItemNews("App Info",R.drawable.ic_baseline_info_24));

        NewsAdapter newsAdapter = new NewsAdapter(this,R.layout.news_row,data);
        lvNews.setAdapter(newsAdapter);

    }
    //End news
}