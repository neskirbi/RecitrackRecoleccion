package com.recitrack.recitrackrecoleccion.Login;




import com.recitrack.recitrackrecoleccion.BuildConfig;
import com.recitrack.recitrackrecoleccion.Models.Recolector;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AdministradorInterface {
    @Headers(BuildConfig.APP_KEY)
    @POST("api/RecolectorLogin")
    Call<Recolector> getAdministrador(@Body Recolector recolector);
}