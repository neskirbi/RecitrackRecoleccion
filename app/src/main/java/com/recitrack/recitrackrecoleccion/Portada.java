package com.recitrack.recitrackrecoleccion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.recitrack.recitrackrecoleccion.Login.LoginView;
import com.recitrack.recitrackrecoleccion.Menu.MenuView;

public class Portada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                //startActivity(new Intent(getApplicationContext(), LoginView.class));
                Intent intent = new Intent(getApplicationContext(), MenuView.class);
                startActivity(intent);
                finish();
            };
        }, 1000);

    }
}