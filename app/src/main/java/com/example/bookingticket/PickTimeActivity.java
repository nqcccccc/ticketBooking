package com.example.bookingticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingticket.Retrofit2.APIUtils;
import com.example.bookingticket.Retrofit2.DataClient;
import com.github.badoualy.datepicker.DatePickerTimeline;
import com.github.badoualy.datepicker.MonthView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickTimeActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePickerTimeline dptDate;
    private ListView lvTime;
    private int id_movies,id_show;
    private GridLayout gridLayout;
    private Button btnSA1,btnSA2,btnSA3,btnSB1,btnSB2,btnSB3,btnSC1,btnSC2,btnSC3,btnBookNow;
    private int A1=0,A2=0,A3=0,B1=0,B2=0,B3=0,C1=0,C2=0,C3 = 0;
    private List<String> selected = new ArrayList<>();
    private LinearLayout linearLayout;
    private TextView tvQuantity,tvSeat,tvTotal;
    private String date,finalTime;
    private ArrayList<Account> arrayUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_time);

        Intent intent = getIntent();

        id_movies = Integer.parseInt(intent.getStringExtra("id_movie"));
        arrayUser = intent.getParcelableArrayListExtra("arrayUser");

        init();
        getDate();

    }

    private void setData() {
        Collections.sort(selected);
        String seat = String.valueOf(selected);
        String seatnew = seat.substring(1, seat.length()-1);
        int total = (selected.size()) * 50000;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        tvQuantity.setText("Quantity: "+selected.size());
        tvSeat.setText("Seat : "+seatnew);
        tvTotal.setText("Quantity: "+formatter.format(total)+"VNĐ");
    }

    private void getDate() {
        dptDate.setFirstVisibleDate(2020,Calendar.DECEMBER,6);
        dptDate.setLastVisibleDate(2020, Calendar.DECEMBER,20);

        dptDate.setDateLabelAdapter(new MonthView.DateLabelAdapter() {
            @Override
            public CharSequence getLabel(Calendar calendar, int index) {
                return Integer.toString(calendar.get(Calendar.MONTH) + 1) + "/" + (calendar.get(Calendar.YEAR) % 2000);
            }
        });

        dptDate.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                int m = month+1;
                date = day+"/"+m+"/"+year;

                DataClient dataClient = APIUtils.getData();
                Call<List<Show>> callback = dataClient.getShow(date,id_movies);
                callback.enqueue(new Callback<List<Show>>() {
                    @Override
                    public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                        ArrayList<Show> arrayShow = (ArrayList<Show>) response.body();
                        List<String> time = new ArrayList<String>();
                        if (arrayShow.size()>0) {
                            for(int i = 0;i<arrayShow.size();i++){
                                time.add(arrayShow.get(i).getTime());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(PickTimeActivity.this,android.R.layout.simple_list_item_1, time);
                            lvTime.setAdapter(adapter);
                            gridLayout.setVisibility(View.INVISIBLE);
                            linearLayout.setVisibility(View.INVISIBLE);
                            btnBookNow.setVisibility(View.INVISIBLE);

                        }
                        // set db for seat
                        lvTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                id_show = Integer.parseInt(arrayShow.get(position).getId());
                                A1=0;A2=0;A3=0;B1=0;B2=0;B3=0;C1=0;C2=0;C3=0;
                                Log.d("TAG", "onItemClick: "+id_show);
                                //Query !!!!
                                DataClient dataClient1 = APIUtils.getData();
                                Call<List<BookInfo>> callback = dataClient.getBookInfo(id_show);
                                callback.enqueue(new Callback<List<BookInfo>>() {
                                    @Override
                                    public void onResponse(Call<List<BookInfo>> call, Response<List<BookInfo>> response) {
                                        ArrayList<BookInfo> bookInfoArrayList = (ArrayList<BookInfo>) response.body();

                                        if (bookInfoArrayList.size()>0){
                                            List<String> list = new ArrayList<>();
                                            for (int i = 0 ; i<bookInfoArrayList.size();i++){
                                                String tempSeat = bookInfoArrayList.get(i).getSeat();
                                                //tempSeat.replace(" ","");
                                                String[] bookedSeat = tempSeat.split(",", 0);
                                                for(String s: bookedSeat) {
                                                    list.add(s);
                                                }
                                                for (int z = 0 ; z < list.size();z++){
                                                    Log.d("TAG", list.get(z));
                                                    if(list.get(z).trim().equals("A1")) {
                                                        A1 = 1;
                                                        Log.d("TAG", "A1: "+A1);
                                                    }
                                                    else if(list.get(z).trim().equals("A2")) {
                                                        A2 = 1;
                                                        Log.d("TAG", "A2: "+A2);
                                                    }
                                                    else if(list.get(z).trim().equals("A3")) {
                                                        A3 = 1;
                                                    }
                                                    else if(list.get(z).trim().equals("B1")) {
                                                        B1 = 1;
                                                    }
                                                    else if(list.get(z).trim().equals("B2")) {
                                                        B2 = 1;
                                                    }
                                                    else if(list.get(z).trim().equals("B3")) {
                                                        B3 = 1;
                                                    }
                                                    else if(list.get(z).trim().equals("C1")) {
                                                        C1 = 1;
                                                    }
                                                    else if(list.get(z).trim().equals("C2")) {
                                                        C2 = 1;
                                                    }
                                                    else if(list.get(z).trim().equals("C3")) {
                                                        C3 = 1;
                                                    }
                                                }
                                            }
                                            Log.d("TAG", "onResponse: Success");
                                            gridLayout.setVisibility(View.VISIBLE);
                                            linearLayout.setVisibility(View.INVISIBLE);
                                            btnBookNow.setVisibility(View.INVISIBLE);
                                            selected.clear();
                                            checkSeat();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<BookInfo>> call, Throwable t) {
                                        Log.d("TAG", "onResponse: Fail Failure");
                                        A1=0;A2=0;A3=0;B1=0;B2=0;B3=0;C1=0;C2=0;C3=0;
                                        gridLayout.setVisibility(View.VISIBLE);
                                        linearLayout.setVisibility(View.INVISIBLE);
                                        btnBookNow.setVisibility(View.INVISIBLE);
                                        selected.clear();
                                        checkSeat();
                                    }
                                });



                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<Show>> call, Throwable t) {
                        Log.d("TAG", "onResponse: 0 suất");
                        List<String> time = new ArrayList<String>();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PickTimeActivity.this,android.R.layout.simple_list_item_1, time);
                        lvTime.setAdapter(adapter);
                        gridLayout.setVisibility(View.INVISIBLE);
                        Toast.makeText(PickTimeActivity.this,"Please pick another day !",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void init() {
        dptDate = findViewById(R.id.dptDate);
        lvTime = findViewById(R.id.lvTime);
        gridLayout = findViewById(R.id.gridLayout);

        btnSA1 = findViewById(R.id.btnSA1);
        btnSA2 = findViewById(R.id.btnSA2);
        btnSA3 = findViewById(R.id.btnSA3);
        btnSB1 = findViewById(R.id.btnSB1);
        btnSB2 = findViewById(R.id.btnSB2);
        btnSB3 = findViewById(R.id.btnSB3);
        btnSC1 = findViewById(R.id.btnSC1);
        btnSC2 = findViewById(R.id.btnSC2);
        btnSC3 = findViewById(R.id.btnSC3);

        linearLayout = findViewById(R.id.linearLayout);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvSeat = findViewById(R.id.tvSeat);
        tvTotal = findViewById(R.id.tvTotal);

        btnBookNow = findViewById(R.id.btnBookNow);
        btnBookNow.setOnClickListener(this);

        checkSeat();

        gridLayout.setVisibility(View.INVISIBLE);
        btnSA1.setOnClickListener(this);
        btnSA2.setOnClickListener(this);
        btnSA3.setOnClickListener(this);
        btnSB1.setOnClickListener(this);
        btnSB2.setOnClickListener(this);
        btnSB3.setOnClickListener(this);
        btnSC1.setOnClickListener(this);
        btnSC2.setOnClickListener(this);
        btnSC3.setOnClickListener(this);

        linearLayout.setVisibility(View.INVISIBLE);
        btnBookNow.setVisibility(View.INVISIBLE);


    }

    private void checkSeat() {
        if (A1 == 1){
            btnSA1.setEnabled(false);
            btnSA1.setBackgroundColor(Color.GRAY);
        }else if (A1 == 0){
            btnSA1.setEnabled(true);
            btnSA1.setBackgroundColor(getResources().getColor(R.color.light_orange));
        }

        if (A2 == 1){
            btnSA2.setEnabled(false);
            btnSA2.setBackgroundColor(Color.GRAY);
        }else if (A2 == 0){
            btnSA2.setEnabled(true);
            btnSA2.setBackgroundColor(getResources().getColor(R.color.light_orange));
        }

        if (A3 == 1){
            btnSA3.setEnabled(false);
            btnSA3.setBackgroundColor(Color.GRAY);
        }else if (A3 == 0){
            btnSA3.setEnabled(true);
            btnSA3.setBackgroundColor(getResources().getColor(R.color.light_orange));
        }

        if (B1 == 1){
            btnSB1.setEnabled(false);
            btnSB1.setBackgroundColor(Color.GRAY);
        }else if (B1 == 0){
            btnSB1.setEnabled(true);
            btnSB1.setBackgroundColor(getResources().getColor(R.color.light_orange));
        }

        if (B2 == 1){
            btnSB2.setEnabled(false);
            btnSB2.setBackgroundColor(Color.GRAY);
        }else if (B2 == 0){
            btnSB2.setEnabled(true);
            btnSB2.setBackgroundColor(getResources().getColor(R.color.light_orange));
        }

        if (B3 == 1){
            btnSB3.setEnabled(false);
            btnSB3.setBackgroundColor(Color.GRAY);
        }else if (B3 == 0){
            btnSB3.setEnabled(true);
            btnSB3.setBackgroundColor(getResources().getColor(R.color.light_orange));
        }

        if (C1 == 1){
            btnSC1.setEnabled(false);
            btnSC1.setBackgroundColor(Color.GRAY);
        }else if (C1 == 0){
            btnSC1.setEnabled(true);
            btnSC1.setBackgroundColor(getResources().getColor(R.color.light_orange));
        }

        if (C2 == 1){
            btnSC2.setEnabled(false);
            btnSC2.setBackgroundColor(Color.GRAY);
        }else if (C2 == 0){
            btnSC2.setEnabled(true);
            btnSC2.setBackgroundColor(getResources().getColor(R.color.light_orange));
        }

        if (C3 == 1){
            btnSC3.setEnabled(false);
            btnSC3.setBackgroundColor(Color.GRAY);
        }else if (C3 == 0){
            btnSC3.setEnabled(true);
            btnSC3.setBackgroundColor(getResources().getColor(R.color.light_orange));
        }
    }

    @Override
    public void onClick(View v) {
        linearLayout.setVisibility(View.VISIBLE);
        btnBookNow.setVisibility(View.VISIBLE);

        switch (v.getId()){
            case R.id.btnSA1:
                if(A1 == 0){
                    A1 =2;
                    btnSA1.setBackgroundColor(Color.RED);
                    selected.add(btnSA1.getText().toString());
                    Log.d("TAG", String.valueOf(selected));
                }else{
                    A1=0;
                    btnSA1.setBackgroundColor(getResources().getColor(R.color.light_orange));
                    selected.remove(btnSA1.getText().toString());
                    Log.d("TAG", String.valueOf(selected));
                }
                break;
            case R.id.btnSA2:
                if(A2 == 0){
                    A2 =2;
                    selected.add(btnSA2.getText().toString());
                    btnSA2.setBackgroundColor(Color.RED);
                }else{
                    A2=0;
                    selected.remove(btnSA2.getText().toString());
                    btnSA2.setBackgroundColor(getResources().getColor(R.color.light_orange));
                }
                break;
            case R.id.btnSA3:
                if(A3 == 0){
                    A3 =2;
                    selected.add(btnSA3.getText().toString());
                    btnSA3.setBackgroundColor(Color.RED);
                }else{
                    A3=0;
                    selected.remove(btnSA3.getText().toString());
                    btnSA3.setBackgroundColor(getResources().getColor(R.color.light_orange));
                }
                break;
            case R.id.btnSB1:
                if(B1 == 0){
                    B1 =2;
                    selected.add(btnSB1.getText().toString());
                    btnSB1.setBackgroundColor(Color.RED);
                }else{
                    B1=0;
                    selected.remove(btnSB1.getText().toString());
                    btnSB1.setBackgroundColor(getResources().getColor(R.color.light_orange));
                }
                break;
            case R.id.btnSB2:
                if(B2 == 0){
                    B2 =2;
                    selected.add(btnSB2.getText().toString());
                    btnSB2.setBackgroundColor(Color.RED);
                }else{
                    B2=0;
                    selected.remove(btnSB2.getText().toString());
                    btnSB2.setBackgroundColor(getResources().getColor(R.color.light_orange));
                }
                break;
            case R.id.btnSB3:
                if(B3 == 0){
                    B3 =2;
                    selected.add(btnSB3.getText().toString());
                    btnSB3.setBackgroundColor(Color.RED);
                }else{
                    B3=0;
                    selected.remove(btnSB3.getText().toString());
                    btnSB3.setBackgroundColor(getResources().getColor(R.color.light_orange));
                }
                break;
            case R.id.btnSC1:
                if(C1 == 0){
                    C1 =2;
                    selected.add(btnSC1.getText().toString());
                    btnSC1.setBackgroundColor(Color.RED);
                }else{
                    C1=0;
                    selected.remove(btnSC1.getText().toString());
                    btnSC1.setBackgroundColor(getResources().getColor(R.color.light_orange));
                }
                break;
            case R.id.btnSC2:
                if(C2 == 0){
                    C2 =2;
                    selected.add(btnSC2.getText().toString());
                    btnSC2.setBackgroundColor(Color.RED);
                }else{
                    C2=0;
                    selected.remove(btnSC2.getText().toString());
                    btnSC2.setBackgroundColor(getResources().getColor(R.color.light_orange));
                }
                break;
            case R.id.btnSC3:
                if(C3 == 0){
                    C3 =2;
                    selected.add(btnSC3.getText().toString());
                    btnSC3.setBackgroundColor(Color.RED);
                }else{
                    C3=0;
                    selected.remove(btnSC3.getText().toString());
                    btnSC3.setBackgroundColor(getResources().getColor(R.color.light_orange));
                }
                break;
            case R.id.btnBookNow:
                int id_user = Integer.parseInt(arrayUser.get(0).getId());
                Log.d("TAG", "onClick book: "+selected);
                // có user id , id_show chờ làm file up load !!!
                break;

        }
        setData();
    }
}