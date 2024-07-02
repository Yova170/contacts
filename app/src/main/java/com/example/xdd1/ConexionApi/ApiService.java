package com.example.xdd1.ConexionApi;

import com.example.xdd1.ListElements;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/CrearContacto")
    Call<ListElements> crearContacto(@Body ListElements contacto);

    @GET("/ListaContacto")
    Call<List<ListElements>> listaContactos();

    @DELETE("/EliminarContacto/{id}")
    Call<Void> eliminarContacto(@Path("id") Long id);

    @PUT("/ActualizarContacto/{id}")
    Call<ListElements> actualizarContacto(@Path("id") Long id, @Body ListElements contacto);
}
