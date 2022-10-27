package com.recitrack.recitrackrecoleccion.Recoleccion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.recitrack.recitrackrecoleccion.Avance.AvanceView;
import com.recitrack.recitrackrecoleccion.Home.Home;
import com.recitrack.recitrackrecoleccion.Metodos;
import com.recitrack.recitrackrecoleccion.R;

public class RecoleccionView extends AppCompatActivity implements Recoleccion.View {

    String datos;
    Context context;
    RecoleccionPresenter presenter;
    TextView negocio;
    Metodos metodos;
    String[] datosarray;
    EditText cantidad;
    FrameLayout dialogo;
    BottomNavigationView bottom_navigation;
    LinearLayout lista;
    Spinner residuos,contenedores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoleccion_view);
        context=this;
        metodos=new Metodos(context);
        presenter=new RecoleccionPresenter(this,context);
        lista=findViewById(R.id.lista);
        datos=getIntent().getStringExtra("datos");
        datosarray = datos.split("/");
        cantidad=findViewById(R.id.cantidad);
        dialogo=findViewById(R.id.dialogo);
        bottom_navigation=findViewById(R.id.bottom_navigation);

        if(datosarray.length!=2){
            Toast.makeText(context, "Error de QR.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Home.class));
        }
        negocio=findViewById(R.id.negocio);

       setTitle(datosarray[0]);

        dialogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CerrarDialogo();
            }
        });

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.agregar:
                        dialogo.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });

         residuos = (Spinner) findViewById(R.id.residuo);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.residuos, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        residuos.setAdapter(adapter);

        contenedores = (Spinner) findViewById(R.id.contenedor);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.contenedores, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        contenedores.setAdapter(adapter2);
    }

    private void CerrarDialogo() {
        dialogo.setVisibility(View.GONE);
    }

    public void Aceptar(View view){
        metodos.Vibrar(metodos.VibrarPush());
        presenter.Aceptar(datosarray,cantidad.getText().toString());
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


    public void AgregarResiduo(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View item = inflater.inflate(R.layout.item_residuo, null);
        TextView residuo=item.findViewById(R.id.residuo);
        residuo.setText(residuos.getSelectedItem().toString());

        TextView contenedor=item.findViewById(R.id.contenedor);
        contenedor.setText(contenedores.getSelectedItem().toString());

        TextView cantidad=item.findViewById(R.id.cantidad);
        cantidad.setText(this.cantidad.getText());

        lista.addView(item);

        CerrarDialogo();


    }
}