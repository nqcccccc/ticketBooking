package com.example.bookingticket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingticket.Retrofit2.APIUtils;
import com.example.bookingticket.Retrofit2.DataClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView vvTrailer;
    private TextView tvMName,tvMGenre,tvMlength,tvMDate,tvMLang,tvMDes;
    private Button btnMBook;
    private CircleImageView imgAva;
    private int user;
    private ArrayList<Account> arrayUser = null;

    ArrayList<MoiveInfo> arrayMovie = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);


        intit();
        intiData();
        getUser();
        setData();
    }

    private void getUser() {
        DataClient dataClient = APIUtils.getData();
        Call<List<Account>> callback = dataClient.getUser(user);
        callback.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                arrayUser = (ArrayList<Account>) response.body();

                if(arrayUser.size()>0){
                    String url = arrayUser.get(0).getAvatar();
                    Log.d("TAG", url);
                    Picasso.get().load(arrayUser.get(0).getAvatar()).into(imgAva);
                }else{
                    Log.d("TAG", "onResponse: ");
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.d("TAG", "onFailure: lỗi ");
            }
        });
    }

    private void intiData() {
        Intent intent = getIntent();
        arrayMovie = intent.getParcelableArrayListExtra("arrayMovie");
        user = intent.getIntExtra("user",1);

        Log.d("TAG", ""+user);
    }

    private void setData() {
        Uri uri = Uri.parse(arrayMovie.get(0).getTrailer());
        ProgressDialog progressDialog = new ProgressDialog(MovieActivity.this);
        //progressDialog.show();
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vvTrailer);
        vvTrailer.setVideoURI(uri);
        vvTrailer.setMediaController(mediaController);
        vvTrailer.start();
        vvTrailer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //progressDialog.dismiss();
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
        imgAva = findViewById(R.id.imgAva);
        btnMBook = findViewById(R.id.btnMBook);

        btnMBook.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MovieActivity.this,PickTimeActivity.class);
        intent.putExtra("arrayUser",arrayUser);
        intent.putExtra("id_movie",arrayMovie.get(0).getIdMovie());

        startActivity(intent);
    }
}
