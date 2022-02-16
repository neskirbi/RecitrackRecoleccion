package com.recitrack.recitrackrecoleccion.Servicios;

import com.google.gson.JsonArray;

import java.util.ArrayList;

public interface Traking {
    interface Presenter{
         void GPS();
         void ProcesaCoordenadas();
         void DetenerGPS();
         void GPSOff();
    }

    interface Interactor{
        void EnviarCoordenadas(JsonArray coordenadas, ArrayList<String> porborrar);
    }
}
