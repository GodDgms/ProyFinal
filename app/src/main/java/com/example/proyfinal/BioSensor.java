package com.example.proyfinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class BioSensor extends AppCompatActivity {

    //Elementos
    Button btnAt;
    TextView tvEs;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_sensor);

        btnAt = findViewById(R.id.btnAt);
        tvEs = findViewById(R.id.tvEs);

        //Iniciar Valores
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(BioSensor.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                //Prevencion de errores durante la autenticacion
                tvEs.setText("Error:" +errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //Autenticacion Exitosa
                tvEs.setText("Autenticacion Exitosa");
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //Autenticacion Fallida
                tvEs.setText("Autenticacion Fallida");
            }
        });

        //Dialogo de autenticacion
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticacion Biometrica")
                .setSubtitle("Entrar mediante la huella dactilar o reconocimiento facil")
                .setNegativeButtonText("Cancelar")
                .build();
    }

    //Boton
    public void Validar(View view){
        tvEs.setText("Status");
        //Validar mediante un dialog
        biometricPrompt.authenticate(promptInfo);

    }
}