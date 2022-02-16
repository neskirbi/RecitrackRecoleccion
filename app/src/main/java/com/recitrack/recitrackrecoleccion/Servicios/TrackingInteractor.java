package com.recitrack.recitrackrecoleccion.Servicios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.Log;

import com.app.recitracktransporte.DB.DB;
import com.app.recitracktransporte.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrackingInteractor implements Traking.Interactor {
    private Context context;
    private TrackingPresenter trackingPresenter;

    public TrackingInteractor(Context context, TrackingPresenter trackingPresenter) {
        this.context = context;
        this.trackingPresenter = trackingPresenter;
    }

    @Override
    public void EnviarCoordenadas(JsonArray coordenadas, ArrayList<String> porborrar) {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrackingInterface cliente=retrofit.create(TrackingInterface.class);

        Call<JsonArray> call= cliente.SetCoordenadas(coordenadas);

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray body= response.body();
                if(body!=null){

                    if(body.size()>0){
                        JsonObject j= body.get(0).getAsJsonObject();
                        if(j.has("Correcto")){
                            BorrarCoordenadas(porborrar);
                        }
                    }


                }else{

                }
            }

            private void BorrarCoordenadas(ArrayList<String> porborrar) {
                for(int i =0 ; i<porborrar.size();i++){
                    try {
                        DB base = new DB(context);
                        SQLiteDatabase db = base.getWritableDatabase();

                        db.execSQL("DELETE from coordenadas where id='"+porborrar.get(i)+"' ");
                        db.close();
                    }catch (Exception e){
                        Log.i("Response","Error:"+e.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("Response"," problerma"+t);
            }
        });
    }


}
