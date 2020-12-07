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

    private ListView lvHistory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lvHistory = findViewById(R.id.lvHistory);

        List<ItemHistory> arrayHistory = new ArrayList<>();

        arrayHistory.add(new ItemHistory("IT", "DDC", "10:30","G12, G13", "wfhsdofu14"));

        HistoryAdapter historyAdapter = new HistoryAdapter(HistoryActivity.this, R.layout.history_row, arrayHistory);
        lvHistory.setAdapter(historyAdapter);


    }

}
