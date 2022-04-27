package com.recitrack.recitrackrecoleccion.Home;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.recitrack.recitrackrecoleccion.DB.DB;
import com.recitrack.recitrackrecoleccion.Metodos;

public class HomePresenter implements Home.Presenter {
    Context context;
    HomeView homeView;

    Metodos metodos;

    public HomePresenter(HomeView homeView, Context context) {
        this.context = context;
        this.homeView = homeView;
        metodos = new Metodos(context);
    }


}
