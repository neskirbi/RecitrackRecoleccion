package com.recitrack.recitrackrecoleccion.Avance;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.recitrack.recitrackrecoleccion.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AvanceAdapter extends BaseAdapter {
    private Context context;
    private JsonArray citas;
    public AvanceAdapter(Context context, JsonArray citas){
        this.context = context;
        this.citas = citas;
    }

    @Override
    public int getCount() {
        return this.citas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.citas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Copiamos la vista
        View v = convertView;

        //Inflamos la vista con nuestro propio layout
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);

        v= layoutInflater.inflate(R.layout.item_avance, null);
        // Valor actual según la posición

        Log.i("lista",""+citas.get(position).toString());

        try {
            JSONObject jsonObject=new JSONObject(citas.get(position).toString());
            // Referenciamos el elemento a modificar y lo rellenamos


            TextView material = v.findViewById(R.id.negocio);
            material.setText(jsonObject.getString("negocio"));

            TextView cantidad = v.findViewById(R.id.fecha);
            cantidad.setText(jsonObject.getString("fecha"));
            Log.i("uploaded"," \n\nRegistro: "+jsonObject.getInt("uploaded"));
            if(jsonObject.getInt("uploaded")==0){
                v.findViewById(R.id.uploaded_danger).setVisibility(View.VISIBLE);
            }
            if(jsonObject.getInt("uploaded")>0){
                v.findViewById(R.id.uploaded_success).setVisibility(View.VISIBLE);
            }



            //Devolvemos la vista inflada
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("lista",""+e.getMessage());
        }

        return v;
    }



}
