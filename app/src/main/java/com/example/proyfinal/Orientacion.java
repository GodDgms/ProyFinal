package com.example.orientacionfinal2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Orientacion extends AppCompatActivity implements SensorEventListener {

    TextView tv1, tv2, tv3;


    private SensorManager sensorManager;

    private Sensor senAccelerometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv1.setText("X: " + x);
        tv2.setText("Y: " + y);
        tv3.setText("Z: " + z);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}