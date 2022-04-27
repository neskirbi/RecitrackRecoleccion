package com.recitrack.recitrackrecoleccion.Recoleccion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.recitrack.recitrackrecoleccion.Avance.AvanceView;
import com.recitrack.recitrackrecoleccion.Metodos;
import com.recitrack.recitrackrecoleccion.R;

public class RecoleccionView extends AppCompatActivity implements Recoleccion.View {

    String datos;
    Context context;
    RecoleccionPresenter presenter;
    TextView negocio;
    Metodos metodos;
    String[] datosarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoleccion_view);
        context=this;
        metodos=new Metodos(context);
        presenter=new RecoleccionPresenter(this,context);
        datos=getIntent().getStringExtra("datos");
        datosarray = datos.split("/");
        negocio=findViewById(R.id.negocio);

        this.negocio.setText(datosarray[0]);
    }

    public void Aceptar(View view){
        metodos.Vibrar(metodos.VibrarPush());
        presenter.Aceptar(datosarray);
    }


    @Override
    public void Error(String msn) {
        Toast.makeText(context, ""+msn, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void NoCargoInfo() {
        Toast.makeText(context, "Error al cargar los datos.", Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public void IrAvance() {

        startActivity(new Intent(context, AvanceView.class));
    }
}