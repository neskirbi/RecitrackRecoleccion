package com.recitrack.recitrackrecoleccion.Recoleccion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.recitrack.recitrackrecoleccion.DB.DB;
import com.recitrack.recitrackrecoleccion.Metodos;
import com.recitrack.recitrackrecoleccion.Models.Negocio;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecoleccionInteractor implements Recoleccion.Interactor {


    RecoleccionPresenter presenter;
    Context context;
    Metodos metodos;
    public RecoleccionInteractor(RecoleccionPresenter presenter, Context context) {
        this.context=context;
        this.presenter=presenter;
        metodos=new Metodos(context);
    }


    public void GuardarRecoleccion(String negocio,ArrayList<com.recitrack.recitrackrecoleccion.Models.Recoleccion> recoleccions){
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();

        for(int i=0;i<recoleccions.size();i++){
            ContentValues recoleccion = new ContentValues();

            recoleccion.put("id",recoleccions.get(i).getId());
            recoleccion.put("id_negocio", recoleccions.get(i).getId_negocio());
            recoleccion.put("negocio", negocio);
            recoleccion.put("capacidad", recoleccions.get(i).getCapacidad());
            recoleccion.put("contenedor",recoleccions.get(i).getContenedor());
            recoleccion.put("cantidad",recoleccions.get(i).getCantidad());
            recoleccion.put("created_at", metodos.GetDateTime());
            recoleccion.put("updated_at", metodos.GetDateTime());


            db.insert("recolecciones", null, recoleccion);
        }


        db.close();
        presenter.IrAvance();

    }
}
