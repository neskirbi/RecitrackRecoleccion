package com.recitrack.recitrackrecoleccion.Home;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.recitrack.recitrackrecoleccion.Login.LoginView;
import com.recitrack.recitrackrecoleccion.Metodos;
import com.recitrack.recitrackrecoleccion.R;
import com.recitrack.recitrackrecoleccion.Scaner.Scaner;


public class HomeView extends AppCompatActivity implements Home.View {

    Button escanear;
    Metodos metodos;
    HomePresenter homePresenter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        escanear=findViewById(R.id.escanear);
        context=this;
        homePresenter=new HomePresenter(this,context);
        metodos=new Metodos(this);



    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void Escanear(View view){
        metodos.Vibrar(metodos.VibrarPush());
        if(PedirPermiso()) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //homePresenter.IniciarRastreoGPS();

    }

    public void LogOut(){
        homePresenter.Salir();
    }



    public Boolean PedirPermiso() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int permsRequestCode = 100;
            String[] perms = {Manifest.permission.CAMERA};

            int camara = checkSelfPermission(Manifest.permission.CAMERA);

            if (camara == PackageManager.PERMISSION_GRANTED ) {
                startActivity(new Intent(this, Scaner.class));
            } else {

                requestPermissions(perms, permsRequestCode);
                return false;
            }

        }
        return true;
    }

    @Override
    public void Salir() {
        startActivity(new Intent(context, LoginView.class));
        finish();
    }


}