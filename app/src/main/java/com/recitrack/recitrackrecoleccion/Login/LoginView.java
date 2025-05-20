package com.recitrack.recitrackrecoleccion.Login;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.recitrack.recitrackrecoleccion.Menu.MenuView;
import com.recitrack.recitrackrecoleccion.Metodos;
import com.recitrack.recitrackrecoleccion.R;

public class LoginView extends AppCompatActivity implements Login.View {

    EditText telefono,pass;
    private Login.Presenter presenter;
    TextInputLayout laymail,laypass;
    Context context;

    Metodos metodos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        laymail=findViewById(R.id.laytelefono);
        laypass=findViewById(R.id.laypass);
        telefono=findViewById(R.id.telefono);
        pass=findViewById(R.id.pass);
        context=this;

        metodos=new Metodos(this);
        presenter=new LoginPresenter(this,this);
        presenter.ValidarLogin();

        Button btnRegistrarse = findViewById(R.id.btnRegistrarse);

        // Configurar el evento onClick para el botón "Registrarse"
        btnRegistrarse.setOnClickListener(v -> {
            String url = metodos.GetUrl()+"registrot"; // URL a abrir
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)); // Crear un Intent para abrir la URL
            startActivity(intent); // Iniciar la actividad (navegador web)
        });


    }

    public void Login(View view){

        metodos.Vibrar(metodos.VibrarPush());
        PedirPermisoGPS();
    }


    public void LoginOK(){
        startActivity(new Intent(context, MenuView.class));
        finish();
    }


    @Override
    public void Error(String msn) {
        // Set error text
        Toast.makeText(context, msn, Toast.LENGTH_SHORT).show();

    }

    public void PedirPermisoGPS() {
        presenter.Validar(telefono.getText().toString(),pass.getText().toString());
    }


}