package com.recitrack.recitrackrecoleccion.Servicios;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TrackingService extends Service {

    LocationManager lm;
    LocationListener ll;
    ArrayList<Double> lat=new ArrayList<Double>(), lon=new ArrayList<Double>();

    PowerManager powerManager;
    PowerManager.WakeLock wakeLock;
    boolean bandera=true;
    Timer timer;
    TrackingPresenter trackingPresenter;
    Context context;
    public TrackingService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        trackingPresenter=new TrackingPresenter(context);


        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText("").build();

            startForeground(1, notification);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyApp::MyWakelockTag");
        wakeLock.acquire();


        //GPS();

        final Handler handler = new Handler();
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            //Ejecuta tu AsyncTask!
                            if(trackingPresenter.VerificarGPS()){
                                Localiza loc=new Localiza();
                                loc.execute();
                            }else{

                                trackingPresenter.GPSOff();

                            }
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
                    }
                });
            }
        };

        timer.schedule(task, 0, 60000);  //ejecutar en intervalo de 60  segundos.


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        bandera=false;
        if(wakeLock!=null){
            wakeLock.release();

        }
        if(lm!=null){
            lm.removeUpdates(ll);

        }
        timer.cancel();
        timer.purge();

    }

    class Localiza extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            trackingPresenter.GPS();
        }

        @Override
        protected Object doInBackground(Object... objects) {


            Thread tr=new Thread();
            try {
                tr.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            trackingPresenter.ProcesaCoordenadas();
            trackingPresenter.DetenerGPS();
        }
    }






}