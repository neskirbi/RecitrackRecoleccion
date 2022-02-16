package com.recitrack.recitrackrecoleccion.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    private static String Vehiculos = "",Coordenadas = "",Citas = "";
    private static  String DB_NAME = "Recitrack.sqlite";
    private static  int DB_VERSION = 1;

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Vehiculos= "CREATE TABLE IF NOT EXISTS  vehiculos(" +
                "id text DEFAULT ''," +
                "id_empresatrasnporte text DEFAULT ''," +
                "vehiculo text DEFAULT ''," +
                "marca text DEFAULT ''," +
                "modelo text DEFAULT ''," +
                "matricula text DEFAULT ''," +
                "combustible text DEFAULT ''," +
                "nombres text DEFAULT ''," +
                "apellidos text DEFAULT ''," +
                "licencia text DEFAULT ''," +
                "telefono text DEFAULT ''," +
                "pass text DEFAULT ''," +
                "detalle text DEFAULT '')"  ;

        sqLiteDatabase.execSQL(Vehiculos);

        Coordenadas= "CREATE TABLE IF NOT EXISTS  coordenadas(" +
                "id text DEFAULT ''," +
                "id_vehiculo text DEFAULT ''," +
                "lat text DEFAULT ''," +
                "lon text DEFAULT ''," +
                "created_at text DEFAULT ''," +
                "updated_at text DEFAULT '')"  ;

        sqLiteDatabase.execSQL(Coordenadas);

        Citas= "CREATE TABLE IF NOT EXISTS  citas(" +
                "id text DEFAULT ''," +
                "obra text DEFAULT ''," +
                "material text DEFAULT ''," +
                "cantidad text DEFAULT ''," +
                "planta text DEFAULT '')"  ;

        sqLiteDatabase.execSQL(Citas);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
