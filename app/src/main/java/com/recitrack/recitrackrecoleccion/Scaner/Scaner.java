package com.recitrack.recitrackrecoleccion.Scaner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;
import com.recitrack.recitrackrecoleccion.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scaner extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView escanerZXing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaner);

        escanerZXing = new ZXingScannerView(this);
        // Hacer que el contenido de la actividad sea el escaner
        setContentView(escanerZXing);
    }


    @Override
    public void onResume() {
        super.onResume();
        // El "manejador" del resultado es esta misma clase, por eso implementamos ZXingScannerView.ResultHandler
        escanerZXing.setResultHandler(this);
        escanerZXing.startCamera(); // Comenzar la cámara en onResume
    }

    @Override
    public void onPause() {
        super.onPause();
        escanerZXing.stopCamera(); // Pausar en onPause
    }

    // Estamos sobrescribiendo un método de la interfaz ZXingScannerView.ResultHandler
    @Override
    public void handleResult(Result resultado) {

        // Si quieres que se siga escaneando después de haber leído el código, descomenta lo siguiente:
        // Si la descomentas no recomiendo que llames a finish
//        escanerZXing.resumeCameraPreview(this);
        // Obener código/texto leído
        String url = resultado.getText();
        // Cerrar la actividad. Ahora mira onActivityResult de MainActivity
        String[] id = url.split("/");

        Log.i("ScanerResponse","DescargaDatos view");

        //startActivity(new Intent(this, TransporteView.class).putExtra("id",id[id.length-1]));
        finish();
    }
}
