package com.recitrack.recitrackrecoleccion.Home;

public interface Home {
    interface View{
        void Salir();

    }

    interface Presenter{
        void Salir();
        void ValidarLogin();
        void IniciarRastreoGPS();
    }
}
