package com.recitrack.recitrackrecoleccion.Models;

public class Cita {
    private String id;
    private String id_vehiculo;
    private String obra;
    private String material;
    private String cantidad;
    private String planta;
    private String error;


    public Cita(String id,String id_vehiculo, String obra, String material, String cantidad, String planta, String error) {
        this.id = id;
        this.id_vehiculo = id_vehiculo;
        this.obra = obra;
        this.material = material;
        this.cantidad = cantidad;
        this.planta = planta;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
