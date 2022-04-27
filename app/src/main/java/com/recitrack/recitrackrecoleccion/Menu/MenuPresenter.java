package com.recitrack.recitrackrecoleccion.Menu;

import android.content.Context;

public class MenuPresenter implements Menu.MenuPresenter {
    Context context;
    MenuView menuView;
    MenuInteractor menuInteractor;

    public MenuPresenter(Context context, MenuView menuView) {
        this.context = context;
        this.menuView = menuView;
        menuInteractor=new MenuInteractor(context,this);
    }

    @Override
    public void Salir() {
        menuInteractor.Salir();
    }

    @Override
    public void IrAlLogin() {
        menuView.IrAlLogin();
    }
}
