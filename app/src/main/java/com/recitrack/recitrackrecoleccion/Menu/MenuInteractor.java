package com.recitrack.recitrackrecoleccion.Menu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.recitrack.recitrackrecoleccion.DB.DB;

public class MenuInteractor implements Menu.MenuInteractor {

    Context context;
    MenuPresenter menuPresenter;

    public MenuInteractor(Context context, MenuPresenter menuPresenter) {
        this.context = context;
        this.menuPresenter = menuPresenter;
    }

    @Override
    public void Salir() {
        try {
            DB base = new DB(context);
            SQLiteDatabase db = base.getWritableDatabase();

            db.execSQL("DELETE from recolectores ");
            db.close();
            menuPresenter.IrAlLogin();
        }catch (Exception e){}


    }
}
