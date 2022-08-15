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
    public void Aceptar(String[] data,String cantidad) {

        GuardarRecoleccion(data,cantidad);
        presenter.IrAvance();

    }

    public void GuardarRecoleccion(String[] data, String cantidad){
        if(metodos.YaEsta(data[1])){
            Toast.makeText(context, "Ya se capturo anteriormente:"+data[0], Toast.LENGTH_SHORT).show();
            return ;
        }
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();
        ContentValues recoleccion = new ContentValues();

        recoleccion.put("id", data[1]);
        recoleccion.put("negocio", data[0]);
        recoleccion.put("cantidad",cantidad);
        recoleccion.put("created_at", metodos.GetDateTime());
        recoleccion.put("updated_at", metodos.GetDateTime());


        db.insert("recolecciones", null, recoleccion);
        db.close();

    }
}
