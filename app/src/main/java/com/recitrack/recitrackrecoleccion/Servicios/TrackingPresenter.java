package com.recitrack.recitrackrecoleccion.Servicios;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.app.recitracktransporte.DB.DB;
import com.app.recitracktransporte.Metodos;
import com.app.recitracktransporte.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class TrackingPresenter implements Traking.Presenter{


    LocationManager lm;
    LocationListener ll;
    ArrayList<Double> lat=new ArrayList<Double>(), lon=new ArrayList<Double>();
    Context context;
    Metodos metodos;
    TrackingInteractor trackingInteractor;
    public TrackingPresenter(Context context) {
        this.context=context;
        metodos=new Metodos(context);
        trackingInteractor=new TrackingInteractor(context,this);
    }

    @Override
    public void GPS(){


        lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);

        ll = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {


                if(lat.size()<20){
                    lat.add(location.getLatitude());
                    lon.add(location.getLongitude());

                    Log.i("Traking","Lat: "+location.getLatitude()+" Lon: "+location.getLongitude());


                }
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 50, 0, ll);
        }



    }
    @Override
    public void DetenerGPS() {

        if(lm!=null){
            lm.removeUpdates(ll);
        }
    }

    @Override
    public void ProcesaCoordenadas() {

        double latprom=0.0,lonprom=0.0;
        for(int i=0;i<lat.size();i++){
            latprom+=lat.get(i);
            lonprom+=lon.get(i);

        }
        latprom=latprom/lat.size();
        lonprom=lonprom/lon.size();


        GuardarCoordenadas(latprom,lonprom);
        ObternerRegistrosCoordnadas();
        lat.clear();
        lon.clear();
    }

   public boolean VerificarGPS(){
       LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
       boolean gps_enabled = false;
       try {
           gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
       } catch(Exception ex) {}


       if(!gps_enabled){
           Log.i("tracking","----------GPS No disponible");

           return false;
       }else{
           Log.i("tracking","-----------GPS  disponible");
           return true;
       }

   }

    public void GuardarCoordenadas(double lat,double lon){
        DB base = new DB(context);
        SQLiteDatabase db = base.getWritableDatabase();
        ContentValues grupo = new ContentValues();

        grupo.put("id", metodos.GetUuid());
        grupo.put("id_vehiculo", metodos.GetIdVehiculo());
        grupo.put("lat", lat);
        grupo.put("lon", lon);
        grupo.put("created_at", metodos.GetDate());
        grupo.put("updated_at", metodos.GetDate());

        db.insert("coordenadas", null, grupo);
        db.close();


    }

    @Override
    public void GPSOff() {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.location_off)
                    .setContentTitle("GPS Apagado")
                    .setContentText("Por favor encienda el GPS")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            // Issue the notification.
            NotificationManager notificationManager =  (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


            //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(2, builder.build());
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String CHANNEL_ID = "my_channel_02";
            // Build the notification and add the action.
            Notification newMessageNotification = new Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.location_off)
                    .setContentTitle("GPS Apagado")
                    .setContentText("Por favor encienda el GPS")
                    .build();




            // Issue the notification.
            NotificationManager notificationManager =  (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    "Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
            //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(2, newMessageNotification);


        }
    }

    public void ObternerRegistrosCoordnadas(){

        ArrayList<String> porborrar = new ArrayList<>();
        if(metodos.NumeroCoordenadas()>5){
            JsonArray coordenadas=new JsonArray();
            try {
                DB base = new DB(context);
                SQLiteDatabase db = base.getWritableDatabase();

                Cursor c =  db.rawQuery("SELECT * from coordenadas ",null);
                c.moveToFirst();
                if(c.getCount()>0){
                    c.moveToFirst();
                    while(c.moveToNext()){

                        porborrar.add(c.getString(c.getColumnIndex("id")));
                        if(c.getString(c.getColumnIndex("lat"))==null || c.getString(c.getColumnIndex("lon"))==null){
                            continue;
                        }
                        JsonObject jsonObject=new JsonObject();
                        jsonObject.addProperty("id",c.getString(c.getColumnIndex("id")));
                        jsonObject.addProperty("id_cita",metodos.GetIdCita());
                        jsonObject.addProperty("id_vehiculo",c.getString(c.getColumnIndex("id_vehiculo")));
                        jsonObject.addProperty("lat",c.getString(c.getColumnIndex("lat")));
                        jsonObject.addProperty("lon",c.getString(c.getColumnIndex("lon")));
                        jsonObject.addProperty("created_at",c.getString(c.getColumnIndex("created_at")));
                        jsonObject.addProperty("updated_at",c.getString(c.getColumnIndex("updated_at")));
                        jsonObject.addProperty("error","");
                        coordenadas.add(jsonObject);

                    }



                }
                c.close();
                db.close();

            }catch (Exception e){
                Log.i("Response",""+e.getMessage());
            }
            trackingInteractor.EnviarCoordenadas(coordenadas,porborrar);
        }




    }
}
