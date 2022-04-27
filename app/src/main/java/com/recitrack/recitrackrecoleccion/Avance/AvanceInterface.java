package com.recitrack.recitrackrecoleccion.Avance;

import com.google.gson.JsonArray;
import com.recitrack.recitrackrecoleccion.Models.Negocio;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AvanceInterface {

    @POST("api/CargarRecoleccion")
    Call<JsonArray> CargarRecoleccion(@Body JsonArray data);
}
