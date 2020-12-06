package com.example.bookingticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookingticket.Retrofit2.APIUtils;
import com.example.bookingticket.Retrofit2.DataClient;
import com.github.badoualy.datepicker.DatePickerTimeline;
import com.github.badoualy.datepicker.MonthView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickTimeActivity extends AppCompatActivity {

    private DatePickerTimeline dptDate;
    private ListView lvTime;
    private int id_movies = 1;
//    private List<String> time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_time);

        init();
        getDate();


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
                String date = day+"/"+m+"/"+year;

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
                        }

                        lvTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String id_show = arrayShow.get(position).getId();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<Show>> call, Throwable t) {
                        Log.d("TAG", "onResponse: 0 suất");
                        List<String> time = new ArrayList<String>();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PickTimeActivity.this,android.R.layout.simple_list_item_1, time);
                        lvTime.setAdapter(adapter);
                        Toast.makeText(PickTimeActivity.this,"Please pick another day !",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void init() {
        dptDate = findViewById(R.id.dptDate);
        lvTime = findViewById(R.id.lvTime);
    }

}