package com.recitrack.recitrackrecoleccion.Models;

public class Recoleccion {

    String id;
    String id_negocio;
    String residuo;
    String contenedor;
    String cantidad;

    public Recoleccion(String id, String id_negocio, String residuo, String contenedor, String cantidad) {
        this.id = id;
        this.id_negocio = id_negocio;
        this.residuo = residuo;
        this.contenedor = contenedor;
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_negocio() {
        return id_negocio;
    }

    public void setId_negocio(String id_negocio) {
        this.id_negocio = id_negocio;
    }

    public String getResiduo() {
        return residuo;
    }

    public void setResiduo(String residuo) {
        this.residuo = residuo;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
