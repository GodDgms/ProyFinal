package com.example.proyfinal;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class StepDetector extends AppCompatActivity implements SensorEventListener {

    private TextView tvstepcounter, tvstepdetector;
    private SensorManager sensormanager;
    private Sensor mStepCounter;
    private boolean iscountersensorpresent;
    int stepCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        //Boton
        public void Orientacion(View view){

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){ //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        tvstepcounter = findViewById(R.id.tvstepcounter);
        tvstepdetector = findViewById(R.id.tvstepdetector);

        sensormanager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensormanager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            mStepCounter = sensormanager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            iscountersensorpresent = true;

        } else {
            tvstepcounter.setText("Counter senser is not present");
            iscountersensorpresent = false;
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor == mStepCounter){
            stepCount = (int)sensorEvent.values[0];
            tvstepcounter.setText(String.valueOf(stepCount));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensormanager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensormanager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensormanager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensormanager.unregisterListener(this, mStepCounter);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
