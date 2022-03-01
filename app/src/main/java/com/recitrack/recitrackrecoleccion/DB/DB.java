package com.recitrack.recitrackrecoleccion.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    private static String Recolectores = "",Coordenadas = "";
    private static  String DB_NAME = "Recitrack.sqlite";
    private static  int DB_VERSION = 1;

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Recolectores= "CREATE TABLE IF NOT EXISTS  recolectores(" +
                "id text DEFAULT ''," +
                "id_planta text DEFAULT ''," +
                "recolector text DEFAULT ''," +
                "telefono text DEFAULT ''," +
                "pass text DEFAULT ''," +
                "mail text DEFAULT '')"  ;

        sqLiteDatabase.execSQL(Recolectores);

        Coordenadas= "CREATE TABLE IF NOT EXISTS  coordenadas(" +
                "id text DEFAULT ''," +
                "id_recolector text DEFAULT ''," +
                "lat text DEFAULT ''," +
                "lon text DEFAULT ''," +
                "created_at text DEFAULT ''," +
                "updated_at text DEFAULT '')"  ;

        sqLiteDatabase.execSQL(Coordenadas);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
