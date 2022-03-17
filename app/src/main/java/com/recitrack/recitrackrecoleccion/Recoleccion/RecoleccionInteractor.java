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


    @Override
    public void Aceptar(String[] data) {
        GuardarRecoleccion(data);

    }

    public void GuardarRecoleccion(String[] data){
        if(metodos.YaEsta(data[1])){
            Toast.makeText(context, "Ya se capturo anteriormente:"+data[0], Toast.LENGTH_SHORT).show();
            return ;
        }
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();
        ContentValues negocio = new ContentValues();

        negocio.put("id", data[1]);
        negocio.put("negocio", data[0]);
        negocio.put("created_at", metodos.GetDate());
        negocio.put("updated_at", metodos.GetDate());


        db.insert("negocios", null, negocio);
        db.close();

    }
}
