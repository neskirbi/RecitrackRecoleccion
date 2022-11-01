package com.recitrack.recitrackrecoleccion.Recoleccion;

import android.content.Context;

import com.recitrack.recitrackrecoleccion.Metodos;

import java.util.ArrayList;

public class RecoleccionPresenter implements Recoleccion.Presenter {
    RecoleccionView view;
    Context context;
    Metodos metodos;
    RecoleccionInteractor interactor;

    public RecoleccionPresenter(RecoleccionView view, Context context) {
        this.view = view;
        this.context = context;
        metodos=new Metodos(context);
        interactor=new RecoleccionInteractor(this,context);
    }

    @Override
    public void GuardarRecoleccion(String negocio,ArrayList<com.recitrack.recitrackrecoleccion.Models.Recoleccion> recoleccion) {
        interactor.GuardarRecoleccion(negocio,recoleccion);
    }

    @Override
    public void NoCargoInfo() {
        view.NoCargoInfo();
    }
    @Override
    public void Error(String error) {
        view.Error(error);
    }

    public void Aceptar(String[] data,String cantidad) {

        if(cantidad.trim().length()==0){
            Error("La cantidad no puede ser 0."+cantidad);
        }else{


        }
    }


    @Override
    public void IrAvance() {
        view.IrAvance();
    }
}
