package com.recitrack.recitrackrecoleccion.Recoleccion;

import java.util.ArrayList;

public interface Recoleccion {
    interface View{
        void Error(String msn);
        void NoCargoInfo();
        void IrAvance();
    }
    interface Presenter{
        void GuardarRecoleccion(ArrayList<com.recitrack.recitrackrecoleccion.Models.Recoleccion> recoleccion);
        void NoCargoInfo();

        void Error(String error_de_conexion);

        void IrAvance();

    }
    interface Interactor{
        void GuardarRecoleccion(ArrayList<com.recitrack.recitrackrecoleccion.Models.Recoleccion> recoleccion);
    }
}
