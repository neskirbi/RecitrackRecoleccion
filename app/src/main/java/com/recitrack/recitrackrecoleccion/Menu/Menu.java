package com.recitrack.recitrackrecoleccion.Menu;

public interface Menu {

    interface MenuView{
        void IrAlLogin();
    }

    interface MenuPresenter{
        void Salir();
        void IrAlLogin();
    }

    interface MenuInteractor{
        void Salir();
    }
}
