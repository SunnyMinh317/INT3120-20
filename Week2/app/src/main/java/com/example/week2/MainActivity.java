package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    NumberPicker numPick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Donation.1.5");

        numPick = findViewById(R.id.numberPickerNum);
        numPick.setMinValue(0);
        numPick.setMaxValue(1000);
    }
}