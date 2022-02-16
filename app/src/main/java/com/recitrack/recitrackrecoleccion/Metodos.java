package com.recitrack.recitrackrecoleccion;

import static android.content.Context.ACTIVITY_SERVICE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.recitrack.recitrackrecoleccion.DB.DB;
import com.recitrack.recitrackrecoleccion.Servicios.TrackingService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Metodos {

    private Context context;

    public Metodos(Context context) {
        this.context=context;
    }


    public String GetUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public String GetDate(){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return timeStamp;
    }

    public boolean ValidarLogin() {
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();
        Cursor c =  db.rawQuery("SELECT * from vehiculos ",null);
        int filas=c.getCount();
        db.close();
        if(filas>0){
            return true;
        }
        return false;


    }


    public boolean Transportando() {
        try{
            DB base = new DB(context);
            SQLiteDatabase db = base.getWritableDatabase();
            Cursor c =  db.rawQuery("SELECT * from citas ",null);
            int filas=c.getCount();
            db.close();
            if(filas>0){

                Log.i("Transportando",filas+"");
                return true;
            }
            Log.i("Transportando",filas+"");
            return false;
        }catch (Exception e){
            return false;
        }



    }
    public void PedirPermisoGPS(Activity view) {

        /*
        * Para la Android 7  menor
        * */


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N){

            IniciarServicioTracking();
        }

        /*
        * Para la Android 7 al 9
        * */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
            int permsRequestCode = 10;
            String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};

            int location = context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

            if (location == PackageManager.PERMISSION_GRANTED ) {

                IniciarServicioTracking();
            } else {

                view.requestPermissions(perms, permsRequestCode);

            }

        }

        /*
         * Para la Android  10 o mayor
         * */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){


            int permsRequestCode = 1;
            String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION};

            int location = context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);

            if (location == PackageManager.PERMISSION_GRANTED ) {

                int permsRequestCode2 = 2;
                String[] perms2 = {Manifest.permission.ACCESS_BACKGROUND_LOCATION};

                int location2 = context.checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION);

                if (location2 == PackageManager.PERMISSION_GRANTED ) {
                    IniciarServicioTracking();
                } else {
                    view.requestPermissions(perms2, permsRequestCode2);
                }
            } else {

                view.requestPermissions(perms, permsRequestCode);

            }




        }


    }

    public void IniciarServicioTracking() {


        if (!isMyServiceRunning(TrackingService.class, context)) {

            Intent service1 = new Intent(context, TrackingService.class);
            service1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ContextCompat.startForegroundService(context,service1);
            }else{

                context.startService(service1);
            }


        }
    }

    public void DetenerServicioTracking() {
        Intent service1 = new Intent(context, TrackingService.class);
        context.stopService(service1);
    }

    public boolean isMyServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public void Vibrar(long[] pattern) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milisegundos
        //pattern = { 0, milli};
        v.vibrate(pattern, -1);
    }

    public long[] VibrarPush() {
        long[] pattern = {0, 70};
        return pattern;
    }

    @SuppressLint("Range")
    public String GetNombres(){
        String nombres="";
        try {
            DB base = new DB(context);
            SQLiteDatabase db = base.getWritableDatabase();

            Cursor c =  db.rawQuery("SELECT * from vehiculos ",null);
            c.moveToFirst();
            if(c.getCount()>0){
                c.moveToFirst();

                nombres=c.getString(c.getColumnIndex("nombres"));

            }
            c.close();
            db.close();
        }catch (Exception e){}

        return nombres;
    }

    @SuppressLint("Range")
    public String GetApellidos(){
        String apellidos="";
        try {
            DB base = new DB(context);
            SQLiteDatabase db = base.getWritableDatabase();

            Cursor c =  db.rawQuery("SELECT * from vehiculos ",null);
            c.moveToFirst();
            if(c.getCount()>0){
                c.moveToFirst();

                apellidos=c.getString(c.getColumnIndex("apellidos"));

            }
            c.close();
            db.close();
        }catch (Exception e){}

        return apellidos;
    }

    @SuppressLint("Range")
    public String GetIdVehiculo() {
        String id_vehiculo="";
        try {
            DB base = new DB(context);
            SQLiteDatabase db = base.getWritableDatabase();

            Cursor c =  db.rawQuery("SELECT * from vehiculos ",null);
            c.moveToFirst();
            if(c.getCount()>0){
                c.moveToFirst();

                id_vehiculo=c.getString(c.getColumnIndex("id"));

            }
            c.close();
            db.close();
        }catch (Exception e){}

        return id_vehiculo;
    }

    public int NumeroCoordenadas() {
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();
        Cursor c =  db.rawQuery("SELECT * from coordenadas ",null);
        int filas=c.getCount();
        db.close();

        return filas;


    }

    public String GetIdCita() {
        String id="";
        try {
            DB base = new DB(context);
            SQLiteDatabase db = base.getWritableDatabase();

            Cursor c =  db.rawQuery("SELECT * from citas ",null);
            c.moveToFirst();
            if(c.getCount()>0){
                c.moveToFirst();
                id=c.getString(c.getColumnIndex("id"));
            }
            c.close();
            db.close();
        }catch (Exception e){

            return "";
        }
        return id;
    }
}
