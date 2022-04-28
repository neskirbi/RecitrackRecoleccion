package com.recitrack.recitrackrecoleccion.Avance;

import com.google.gson.JsonArray;

public interface Avance {
    interface AvanceView{
        void LenarLista(JsonArray lista);
        void AbreDialogo();
        void CierraDialogo();
    }
    interface AvancePresenter{
        void ObternerAvance(String date);
        void LenarLista(JsonArray lista);
        void SubirDatos();
        void AbreDialogo();
        void CierraDialogo();
    }
    interface AvanceInteractor{
        void ObternerAvance(String date);
        void SubirDatos();
    }
}
