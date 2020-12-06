package com.example.bookingticket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {
    private VideoView vvTrailer;
    private TextView tvMName,tvMGenre,tvMlength,tvMDate,tvMLang,tvMDes;
    private Button btnMBook;

    ArrayList<MoiveInfo> arrayMovie = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);


        intit();
        intiData();
        setData();
    }

    private void intiData() {
        Intent intent = getIntent();
        arrayMovie = intent.getParcelableArrayListExtra("arrayMovie");

        Log.d("TAG", arrayMovie.get(0).getName());
    }

    private void setData() {
        Uri uri = Uri.parse(arrayMovie.get(0).getTrailer());
        ProgressDialog progressDialog = new ProgressDialog(MovieActivity.this);
        progressDialog.show();
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vvTrailer);
        vvTrailer.setVideoURI(uri);
        vvTrailer.setMediaController(mediaController);
        vvTrailer.start();
        vvTrailer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
            }
        });

        tvMName.setText("Movie Name: "+arrayMovie.get(0).getName());
        tvMGenre.setText("Genre: "+arrayMovie.get(0).getGenre());
        tvMlength.setText("Length: "+arrayMovie.get(0).getLength());
        tvMDate.setText("Release Date: "+arrayMovie.get(0).getDate());
        tvMLang.setText("Language: "+arrayMovie.get(0).getLang());
        tvMDes.setText("Description: "+arrayMovie.get(0).getDes());
    }

    private void intit() {
        vvTrailer = findViewById(R.id.vvTrailer);
        tvMName = findViewById(R.id.tvMName);
        tvMGenre = findViewById(R.id.tvMGenre);
        tvMlength = findViewById(R.id.tvMlength);
        tvMDate = findViewById(R.id.tvMDate);
        tvMLang = findViewById(R.id.tvMLang);
        tvMDes = findViewById(R.id.tvMDes);
        btnMBook = findViewById(R.id.btnMBook);
    }
}
