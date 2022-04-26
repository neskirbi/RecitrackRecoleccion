package com.recitrack.recitrackrecoleccion.Avance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.recitrack.recitrackrecoleccion.Adapters.AvanceAdapter;
import com.recitrack.recitrackrecoleccion.DB.DB;
import com.recitrack.recitrackrecoleccion.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AvanceView extends AppCompatActivity {
    private ListView listview;
    JsonArray lista=new JsonArray();

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avance_view);
        listview = findViewById(R.id.lista_historial);
        context=this;
        LlenarLista();
    }


    @SuppressLint("Range")
    public void LlenarLista() {
        String algo="";

        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();
        Cursor c =  db.rawQuery("SELECT * from negocios  ",null);

        c.moveToFirst();
        if(c.getCount()>0){
            Toast.makeText(context, "# "+c.getCount(), Toast.LENGTH_SHORT).show();

            while(!c.isAfterLast()){
                algo+=c.getString(c.getColumnIndex("negocio"));
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("negocio",c.getString(c.getColumnIndex("negocio")));
                jsonObject.addProperty("fecha",c.getString(c.getColumnIndex("created_at")));
                lista.add(jsonObject);
                c.moveToNext();
            }


        }

        AvanceAdapter myAdapter = new AvanceAdapter(this, lista);
        listview.setAdapter(myAdapter);
        Toast.makeText(context, ""+algo, Toast.LENGTH_SHORT).show();
    }
}