package com.recitrack.recitrackrecoleccion.Models;

public class Negocio {

    String id;
    String id_negocio;
    String negocio;
    String error;

    public Negocio(String id, String id_negocio, String negocio, String error) {
        this.id = id;
        this.id_negocio = id_negocio;
        this.negocio = negocio;
        this.error = error;
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

    public String getNegocio() {
        return negocio;
    }

    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
