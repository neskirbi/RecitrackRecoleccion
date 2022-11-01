package com.recitrack.recitrackrecoleccion.Recoleccion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    ArrayList<com.recitrack.recitrackrecoleccion.Models.Recoleccion> recoleccions=new ArrayList<>();


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
                    case R.id.guardar:

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                        alertDialog.setTitle("Guardar");
                        alertDialog.setMessage("¿Desea guardar estos datos?");



                        //alertDialog.setIcon(R.drawable.img_quitar);

                        alertDialog.setPositiveButton("Sí",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        presenter.GuardarRecoleccion(datosarray[0],recoleccions);

                                    }
                                });

                        alertDialog.setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        alertDialog.show();


                    break;
                }
                return false;
            }
        });

        residuos = findViewById(R.id.residuos);


        contenedores =  findViewById(R.id.contenedores);

    }

    private void CerrarDialogo() {
        dialogo.setVisibility(View.GONE);
        residuos.setSelection(0);
        contenedores.setSelection(0);
        cantidad.setText("");
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

        if(residuos.getSelectedItemPosition()==0){
            Toast.makeText(context, "Debe seleccionar un tipo de residuo.", Toast.LENGTH_SHORT).show();

            return;
        }

        if(contenedores.getSelectedItemPosition()==0){
            Toast.makeText(context, "Debe seleccionar un tipo de contenedor.", Toast.LENGTH_SHORT).show();

            return;
        }

        if(cantidad.length()==0){
            Toast.makeText(context, "Debe poner que la cantidad.", Toast.LENGTH_SHORT).show();

            return;
        }

        String id=metodos.GetUuid();
        recoleccions.add(new com.recitrack.recitrackrecoleccion.Models.Recoleccion(id,datosarray[1],residuos.getSelectedItemPosition()+"",contenedores.getSelectedItemPosition()+"",this.cantidad.getText().toString()));
        LayoutInflater inflater = getLayoutInflater();
        View item = inflater.inflate(R.layout.item_residuo, null);

        TextView residuo=item.findViewById(R.id.residuo);
        residuo.setText(residuos.getSelectedItem().toString());

        TextView contenedor=item.findViewById(R.id.contenedor);
        contenedor.setText(contenedores.getSelectedItem().toString());

        TextView cantidad=item.findViewById(R.id.cantidad);
        cantidad.setText(this.cantidad.getText());

        Log.i("Removiendo","Tamanio:"+recoleccions.size());
        ImageView menu =item.findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.Vibrar(metodos.VibrarPush());

                Log.i("Removiendo","Tamanio:"+recoleccions.size());
                for(int i=0;i<recoleccions.size();i++){
                    Log.i("Removiendo",id+"   "+recoleccions.get(i).getId());
                    if(recoleccions.get(i).getId().equals(id)){
                        Log.i("Removiendo",id+"   "+recoleccions.get(i).getId());
                        recoleccions.remove(i);
                        ((ViewManager)item.getParent()).removeView(item);
                    }
                }



            }
        });

        lista.addView(item);

        CerrarDialogo();


    }

    @Override
    public void onBackPressed() {
        if(recoleccions.size()==0){
            super.onBackPressed();
        }else{
            Toast.makeText(context, "Para salir debe guardar la recolección.", Toast.LENGTH_SHORT).show();
        }
    }
}