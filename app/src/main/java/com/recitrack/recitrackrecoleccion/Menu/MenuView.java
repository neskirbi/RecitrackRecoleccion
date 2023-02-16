package com.recitrack.recitrackrecoleccion.Menu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.recitrack.recitrackrecoleccion.Avance.AvanceView;
import com.recitrack.recitrackrecoleccion.DB.DB;
import com.recitrack.recitrackrecoleccion.Home.HomeView;
import com.recitrack.recitrackrecoleccion.Login.LoginView;
import com.recitrack.recitrackrecoleccion.Metodos;
import com.recitrack.recitrackrecoleccion.Scaner.Scaner;
import com.recitrack.recitrackrecoleccion.databinding.ActivityMenuViewBinding;
import com.recitrack.recitrackrecoleccion.R;

public class MenuView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, com.recitrack.recitrackrecoleccion.Menu.Menu.MenuView {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuViewBinding binding;
    Metodos metodos;
    Context context;
    private NavigationView navigationView;
    private TextView nombre;
    MenuPresenter menuPresenter;
    MenuView menuView;
    FloatingActionButton floescanear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        menuView=this;
        metodos=new Metodos(context);
        //ValidarLogin();
        menuPresenter=new MenuPresenter(this,this);
        binding = ActivityMenuViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        nombre=findViewById(R.id.nombres);
        nombre.setText(metodos.GetNombre());
        floescanear=findViewById(R.id.floescanear);
        setSupportActionBar(binding.appBarMenuView.toolbar);

        DrawerLayout drawer = binding.drawerLayout;

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(metodos.ValidarLogin()){
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_login).setVisible(false);
        }


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_view);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);

        floescanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Escanear();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_view);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if(R.id.nav_login==id){
            metodos.Vibrar(metodos.VibrarPush());
            startActivity(new Intent(this, LoginView.class));
        }


        if(R.id.nav_escanear==id){
            Escanear();
        }

        if(R.id.nav_avance==id){
            metodos.Vibrar(metodos.VibrarPush());
            startActivity(new Intent(context, AvanceView.class));
        }

        if(R.id.nav_salir==id){
            metodos.Vibrar(metodos.VibrarPush());
            MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(context);
            builder.setTitle("Salir");
            builder.setMessage("¿Esta seguro de salir de su cuenta?");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    metodos.Vibrar(metodos.VibrarPush());
                    menuPresenter.Salir();
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    metodos.Vibrar(metodos.VibrarPush());

                }
            });
            builder.show();
        }

        return true;
    }






    @Override
    public void IrAlLogin() {
        //startActivity(new Intent(context, LoginView.class));
        finish();
        System.exit(0);
    }

    public void Escanear(){
        metodos.Vibrar(metodos.VibrarPush());
        metodos.PedirPermisoCamara(this);
    }
}