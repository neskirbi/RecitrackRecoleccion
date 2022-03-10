package com.recitrack.recitrackrecoleccion.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.recitrack.recitrackrecoleccion.Metodos;
import com.recitrack.recitrackrecoleccion.R;

public class RegistroView extends AppCompatActivity {

    WebView registro;
    Metodos metodos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_view);
        metodos=new Metodos(this);

        WebView myWebView = (WebView) findViewById(R.id.registro);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(metodos.GetUrl()+"RegistroChofer");
    }
}