package com.example.bookingticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private ImageView imgNew;
    private TextView tvContent,tvTT;
    private ArrayList<News> arrayNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();
        arrayNews = intent.getParcelableArrayListExtra("arrayList");

        init();
        setData();
    }

    private void setData() {
        Picasso.get().load(arrayNews.get(0).getImg()).into(imgNew);
        tvContent.setText(arrayNews.get(0).getContent());
        tvTT.setText(arrayNews.get(0).getTittle());
    }

    private void init() {
        imgNew = findViewById(R.id.imgNew);
        tvContent = findViewById(R.id.tvContent);
        tvTT = findViewById(R.id.tvTT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}