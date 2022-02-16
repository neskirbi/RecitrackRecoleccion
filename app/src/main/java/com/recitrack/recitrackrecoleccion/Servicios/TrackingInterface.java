package com.recitrack.recitrackrecoleccion.Servicios;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TrackingInterface {
    @POST("api/Coordenadas")
    Call<JsonArray> SetCoordenadas(@Body JsonArray coordenadas);
}
