package com.recitrack.recitrackrecoleccion.Avance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.recitrack.recitrackrecoleccion.DB.DB;
import com.recitrack.recitrackrecoleccion.Metodos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AvanceInteractor implements Avance.AvanceInteractor {

    Context context;
    Avance.AvancePresenter avancePresenter;
    Metodos metodos;

    public AvanceInteractor(Context context, Avance.AvancePresenter avancePresenter) {
        this.context = context;
        this.avancePresenter = avancePresenter;
        metodos=new Metodos(context);
    }

    @SuppressLint("Range")
    @Override
    public void ObternerAvance(String date) {
        JsonArray avance=new JsonArray();
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date= dateFormat.format(dateFormat.parse(date));

            Cursor c =  db.rawQuery("SELECT negocio,sum(uploaded) as uploaded,max(created_at) as created_at  from recolecciones where DATE(created_at) = '"+date+"' group by negocio ",null);

            c.moveToFirst();

            Toast.makeText(context,c.getCount()+" registros.", Toast.LENGTH_SHORT).show();
            if(c.getCount()>0){

                while(!c.isAfterLast()){
                    JsonObject jsonObject=new JsonObject();
                    jsonObject.addProperty("negocio",c.getString(c.getColumnIndex("negocio")));
                    jsonObject.addProperty("fecha",c.getString(c.getColumnIndex("created_at")));
                    jsonObject.addProperty("uploaded",c.getInt(c.getColumnIndex("uploaded")));
                    avance.add(jsonObject);
                    c.moveToNext();

                }


            }
            avancePresenter.LenarLista(avance);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @SuppressLint("Range")
    @Override
    public void SubirDatos() {

        JsonArray data=new JsonArray();
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();

        Cursor c =  db.rawQuery("SELECT * from recolecciones where uploaded = 0 ",null);


        if(c.getCount()>0){

            avancePresenter.AbreDialogo();

            c.moveToFirst();
            while(!c.isAfterLast()){
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("id_recolector",metodos.GetIdRecolector());
                jsonObject.addProperty("id",c.getString(c.getColumnIndex("id")));
                jsonObject.addProperty("id_negocio",c.getString(c.getColumnIndex("id_negocio")));
                jsonObject.addProperty("negocio",c.getString(c.getColumnIndex("negocio")));
                jsonObject.addProperty("contenedor",c.getString(c.getColumnIndex("contenedor")));
                jsonObject.addProperty("residuo",c.getString(c.getColumnIndex("residuo")));
                jsonObject.addProperty("cantidad",c.getString(c.getColumnIndex("cantidad")));
                jsonObject.addProperty("created_at",c.getString(c.getColumnIndex("created_at")));
                jsonObject.addProperty("updated_at",c.getString(c.getColumnIndex("updated_at")));
                data.add(jsonObject);
                c.moveToNext();

            }

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(metodos.GetUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AvanceInterface cliente=retrofit.create(AvanceInterface.class);

            Call<JsonArray> call= null;
            call = cliente.CargarRecoleccion(data);
            Log.i("Response",""+data);
            call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    JsonArray body= response.body();
                    if(body!=null){
                        Log.i("Response", "Body "+response.body());

                        Confirmar(body);

                    }else{
                        Log.i("Response"," \n\nCodigo:"+response.code()+" \n\nbody:"+body);
                        Toast.makeText(context, "Error de conexión "+response.code(), Toast.LENGTH_SHORT).show();

                    }
                    avancePresenter.CierraDialogo();

                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Log.i("avancePresenter.CierraDialogo();",": Error"+t.getMessage());
                    Toast.makeText(context, "Error de conexión "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    avancePresenter.CierraDialogo();
                }
            });



        }
    }

    private void Confirmar(JsonArray recolecciones) {
        for(int i=0;i<recolecciones.size();i++){
            Log.i("Response"," \n\nRegistro: "+recolecciones.get(i));
            DB base = new DB(context);
            SQLiteDatabase db = base.getWritableDatabase();
            String strSQL = "UPDATE recolecciones SET uploaded = 1 WHERE id = "+recolecciones.get(i)+" ";
            db.execSQL(strSQL);
        }
        avancePresenter.ObternerAvance(metodos.GetDate());
    }
}
