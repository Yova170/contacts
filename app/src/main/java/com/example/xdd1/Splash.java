package com.example.xdd1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    // Duración del sppash en milisegundos
    private static final int SPLASH_DURATION = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // Configurar una espera para mostrar el splash y luego iniciar la actividad principal
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Este método se ejecutará después de que termine el splash
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);

                // Cerrar la actividad de splash para que no se vuelva a mostrar al retroceder
                finish();
            }
        }, SPLASH_DURATION);
    }
}
