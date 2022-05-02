package com.recitrack.recitrackrecoleccion.Avance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;
import com.recitrack.recitrackrecoleccion.BuildConfig;
import com.recitrack.recitrackrecoleccion.DB.DB;
import com.recitrack.recitrackrecoleccion.Home.HomeView;
import com.recitrack.recitrackrecoleccion.Login.LoginView;
import com.recitrack.recitrackrecoleccion.Menu.MenuView;
import com.recitrack.recitrackrecoleccion.Metodos;
import com.recitrack.recitrackrecoleccion.R;

public class AvanceView extends AppCompatActivity implements Avance.AvanceView {
    private ListView listview;

    Context context;
    AvancePresenter avancePresenter;
    Metodos metodos;
    DatePicker datePicker;
    BottomNavigationView bottom_navigation;
    LinearLayout fondo;
    FloatingActionButton borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avance_view);
        context=this;
        metodos=new Metodos(this);
        avancePresenter=new AvancePresenter(this,this);
        listview = findViewById(R.id.lista_historial);
        bottom_navigation=findViewById(R.id.bottom_navigation);
        datePicker=findViewById(R.id.datepicker);
        fondo=findViewById(R.id.fondo);
        borrar=findViewById(R.id.borrar);
        avancePresenter.ObternerAvance(metodos.GetDate());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                    metodos.Vibrar(metodos.VibrarPush());
                    avancePresenter.ObternerAvance(year+"-"+(month+1)+"-"+day);

                    datePicker.setVisibility(View.GONE);
                    fondo.setVisibility(View.GONE);
                }
            });
        }


        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                metodos.Vibrar(metodos.VibrarPush());





                Log.i("Bottom",id+"");
                if(R.id.escanear==id){
                   startActivity(new Intent(context, HomeView.class));
                }


                if(R.id.fecha==id){
                    datePicker.setVisibility(View.VISIBLE);
                    fondo.setVisibility(View.VISIBLE);
                }

                if(R.id.subir==id){
                    if(metodos.ValidarLogin()){
                        avancePresenter.SubirDatos();
                    }else{
                        Toast.makeText(context, "Primero debe de iniciar sesión", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, LoginView.class));
                    }
                }
                return false;
            }
        });

        if(BuildConfig.DEBUG){
            borrar.setVisibility(View.VISIBLE);
            borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    metodos.Vibrar(metodos.VibrarPush());
                    DesConfirmar();
                }
            });
        }
    }


    @Override
    public void LenarLista(JsonArray lista) {
        AvanceAdapter myAdapter = new AvanceAdapter(this, lista);
        listview.setAdapter(myAdapter);
    }

    @Override
    public void AbreDialogo() {
        metodos.AbreDialogo("Sincronizando...");
    }

    @Override
    public void CierraDialogo() {
        metodos.CierraDialogo();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(context, MenuView.class));
    }

    void DesConfirmar() {
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();
        String strSQL = "UPDATE negocios SET uploaded = 0 ";
        db.execSQL(strSQL);
        avancePresenter.ObternerAvance(metodos.GetDate());
    }
}