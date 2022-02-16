package com.recitrack.recitrackrecoleccion.Servicios;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.app.recitracktransporte.Metodos;

public class Lanzador extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Metodos metodos=new Metodos(context);
        if(metodos.ValidarLogin()){
            metodos.IniciarServicioTracking();

        }else{


        }

    }
}
