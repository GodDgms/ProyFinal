package com.example.proyfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton bvoz, bcara, bhuella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bvoz=findViewById(R.id.bvoz);

        bvoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivityVoz.class);
                startActivity(intent);
                }


        });
    }

    public void BioFace(View view){
        Intent bf = new Intent(getApplicationContext(),BioSensor.class);
        startActivity(bf);
    }
}
//Ahora si sirve XD