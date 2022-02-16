package com.recitrack.recitrackrecoleccion.Home;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.app.recitracktransporte.DB.DB;
import com.app.recitracktransporte.Metodos;

public class HomePresenter implements Home.Presenter {
    Context context;
    HomeView homeView;

    Metodos metodos;
    public HomePresenter(HomeView homeView, Context context) {
        this.context=context;
        this.homeView=homeView;
        metodos=new Metodos(context);
    }


    @Override
    public void Salir() {

        try {
            DB base = new DB(context);
            SQLiteDatabase db = base.getWritableDatabase();

            db.execSQL("DELETE from vehiculos ");
            db.close();
        }catch (Exception e){}



        homeView.Salir();

    }

    @Override
    public void ValidarLogin() {
        if(metodos.ValidarLogin()){

        }
    }

    @Override
    public void IniciarRastreoGPS(){
        metodos.PedirPermisoGPS(homeView);
    }
}
