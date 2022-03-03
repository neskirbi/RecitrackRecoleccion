package com.recitrack.recitrackrecoleccion.Recoleccion;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

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
    public void Aceptar(String id) {

    }
}
