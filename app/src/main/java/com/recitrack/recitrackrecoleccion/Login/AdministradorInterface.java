package com.recitrack.recitrackrecoleccion.Login;




import com.recitrack.recitrackrecoleccion.Models.Recolector;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AdministradorInterface {
    @Headers("APP-KEY: cefa31bbcb2e11ec81768030496e73b4")
    @POST("api/RecolectorLogin")
    Call<Recolector> getAdministrador(@Body Recolector recolector);
}