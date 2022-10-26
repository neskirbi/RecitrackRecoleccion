package com.recitrack.recitrackrecoleccion.Recoleccion;

import android.os.StrictMode;
import android.util.Log;

import com.recitrack.recitrackrecoleccion.BuildConfig;
import com.recitrack.recitrackrecoleccion.Models.Negocio;
import com.recitrack.recitrackrecoleccion.Models.Recolector;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RecoleccionInterface {
    @Headers(BuildConfig.APP_KEY)
    @POST("api/DatosNegocio")
    Call<Negocio> getData(@Body Negocio negocio);

}
