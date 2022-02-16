package com.recitrack.recitrackrecoleccion.Login;



import com.recitrack.recitrackrecoleccion.Models.Vehiculo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AdministradorInterface {
    @POST("api/AppLogin")
    Call<Vehiculo> getAdministrador(@Body Vehiculo vehiculo);
}
