package com.recitrack.recitrackrecoleccion.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.Log;

import com.recitrack.recitrackrecoleccion.DB.DB;
import com.recitrack.recitrackrecoleccion.Models.Vehiculo;
import com.recitrack.recitrackrecoleccion.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginInteractor implements Login.Model{
    private Login.Presenter presenter;
    Context context;
    public LoginInteractor(Login.Presenter presenter, Context context){
        this.presenter=presenter;
        this.context=context;
    }
    @Override
    public void Validar(String telefono,String pass) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AdministradorInterface cliente=retrofit.create(AdministradorInterface.class);

        Call<Vehiculo> call= cliente.getAdministrador(new Vehiculo("","","","","","","","","","",telefono,pass,"",""));

        call.enqueue(new Callback<Vehiculo>() {
            @Override
            public void onResponse(Call<Vehiculo> call, Response<Vehiculo> response) {
                Vehiculo body= response.body();
                if(body!=null){
                    if(body.getError()==null && body.getId()!=null){
                        Log.i("Login",body.getId()+"");
                        GuardarVehiculo(body);
                    }else{
                        Log.i("Login",body.getError()+"");
                        presenter.Error(body.getError());
                    }
                }else{
                    Log.i("Response"," \n\nCodigo:"+response.code()+" \n\nbody:"+body);
                }

            }

            @Override
            public void onFailure(Call<Vehiculo> call, Throwable t) {
                Log.i("Response",": Error"+t.getMessage());
                presenter.Error("Error de conexion");
            }
        });


    }

    public void GuardarVehiculo(Vehiculo vehiculo) {
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();
        ContentValues grupo = new ContentValues();

        grupo.put("id", vehiculo.getId());
        grupo.put("id_empresatrasnporte", vehiculo.getId_empresatrasnporte());
        grupo.put("vehiculo", vehiculo.getVehiculo());
        grupo.put("marca", vehiculo.getMarca());
        grupo.put("modelo", vehiculo.getModelo());
        grupo.put("matricula", vehiculo.getMatricula());
        grupo.put("combustible", vehiculo.getCombustible());
        grupo.put("nombres", vehiculo.getNombres());
        grupo.put("apellidos", vehiculo.getApellidos());
        grupo.put("licencia", vehiculo.getLicencia());
        grupo.put("telefono", vehiculo.getTelefono());
        grupo.put("pass", vehiculo.getPass());
        grupo.put("detalle", vehiculo.getDetalle());


        db.insert("vehiculos", null, grupo);
        db.close();
        presenter.LoginOk();
    }
}
