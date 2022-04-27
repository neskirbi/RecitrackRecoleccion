package com.recitrack.recitrackrecoleccion.Avance;

import com.google.gson.JsonArray;

public interface Avance {
    interface AvanceView{
        void LenarLista(JsonArray lista);
    }
    interface AvancePresenter{
        void ObternerAvance(String date);
        void LenarLista(JsonArray lista);
        void SubirDatos();
    }
    interface AvanceInteractor{
        void ObternerAvance(String date);
        void SubirDatos();
    }
}
