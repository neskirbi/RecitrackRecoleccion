package com.recitrack.recitrackrecoleccion.Recoleccion;

import com.recitrack.recitrackrecoleccion.Models.Negocio;

public interface Recoleccion {
    interface View{
        void Error(String msn);
        void NoCargoInfo();
        void IrAvance();
    }
    interface Presenter{

        void NoCargoInfo();

        void Error(String error_de_conexion);

        void Aceptar(String[] data);
        void IrAvance();

    }
    interface Interactor{
        void Aceptar(String[] data);
    }
}
