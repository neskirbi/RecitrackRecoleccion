package com.recitrack.recitrackrecoleccion.Avance;

import android.content.Context;

import com.google.gson.JsonArray;

public class AvancePresenter implements Avance.AvancePresenter {

    Context context;
    Avance.AvanceView avanceView;
    Avance.AvanceInteractor avanceInteractor;

    public AvancePresenter(Context context, Avance.AvanceView avanceView) {
        this.context = context;
        this.avanceView = avanceView;
        avanceInteractor=new AvanceInteractor(context,this);
    }

    @Override
    public void ObternerAvance(String date) {
        avanceInteractor.ObternerAvance(date);
    }

    @Override
    public void LenarLista(JsonArray avance) {
        avanceView.LenarLista(avance);
    }

    @Override
    public void SubirDatos() {
        avanceInteractor.SubirDatos();
    }
}
