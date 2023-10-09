package com.example.week12_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (sensor != null) {
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            } else {

            }
        } else {
            Toast.makeText(this, "Sensor not detected", Toast.LENGTH_SHORT).show();
        }

        Button button = findViewById(R.id.wifiBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkConnection()) {
                    Toast.makeText(getApplicationContext(), "Connected to Internet", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Not connected to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ((TextView) findViewById(R.id.sensorView)).setText("X: " + event.values[0] + ", Y: " + event.values[1] + ", Z+ " + event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo ==null) {
            return false;
        }
        if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            Toast.makeText(this, "WiFi Connection", Toast.LENGTH_SHORT).show();
        }

        if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            Toast.makeText(this, "Mobile Connection", Toast.LENGTH_SHORT).show();
        }
        return networkInfo.isConnected();
    }
}