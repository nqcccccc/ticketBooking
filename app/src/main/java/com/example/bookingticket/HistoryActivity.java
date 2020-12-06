package com.example.bookingticket;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        List<ItemHistory> arrayHistory = new ArrayList<>();

        HistoryAdapter historyAdapter = new HistoryAdapter(HistoryActivity.this, R.layout.history_row, arrayHistory);

        arrayHistory.add(new ItemHistory("IT", "DDC", "10:30","G12, G13", "wfhsdofu14"));


    }

}
