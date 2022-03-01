package com.recitrack.recitrackrecoleccion.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.Log;

import com.recitrack.recitrackrecoleccion.DB.DB;
import com.recitrack.recitrackrecoleccion.Metodos;
import com.recitrack.recitrackrecoleccion.Models.Recolector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginInteractor implements Login.Model{
    private Login.Presenter presenter;
    Context context;
    Metodos metodos;
    public LoginInteractor(Login.Presenter presenter, Context context){
        this.presenter=presenter;
        this.context=context;
        metodos=new Metodos(context);
    }
    @Override
    public void Validar(String telefono,String pass) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(metodos.GetUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AdministradorInterface cliente=retrofit.create(AdministradorInterface.class);

        Call<Recolector> call= cliente.getAdministrador(new Recolector("","","","",telefono,pass,""));

        call.enqueue(new Callback<Recolector>() {
            @Override
            public void onResponse(Call<Recolector> call, Response<Recolector> response) {
                Recolector body= response.body();
                if(body!=null){
                    if(body.getError()==null && body.getId()!=null){
                        Log.i("Login",body.getId()+"");
                        GuardarRecolector(body);
                    }else{
                        Log.i("Login",body.getError()+"");
                        presenter.Error(body.getError());
                    }
                }else{
                    Log.i("Response"," \n\nCodigo:"+response.code()+" \n\nbody:"+body);
                }

            }

            @Override
            public void onFailure(Call<Recolector> call, Throwable t) {
                Log.i("Response",": Error"+t.getMessage());
                presenter.Error("Error de conexion");
            }
        });


    }

    public void GuardarRecolector(Recolector recolector) {
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();
        ContentValues grupo = new ContentValues();

        grupo.put("id", recolector.getId());
        grupo.put("id_planta", recolector.getId_planta());
        grupo.put("recolector", recolector.getRecolector());
        grupo.put("telefono", recolector.getTelefono());
        grupo.put("pass", recolector.getPass());
        grupo.put("mail", recolector.getMail());




        db.insert("recolectores", null, grupo);
        db.close();
        presenter.LoginOk();
    }
}
