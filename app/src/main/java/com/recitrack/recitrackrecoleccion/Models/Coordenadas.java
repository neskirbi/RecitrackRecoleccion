package com.recitrack.recitrackrecoleccion.Models;

public class Coordenadas {
    private String id;
    private String id_cita;
    private String id_vehiculo;
    private String lat;
    private String lon;
    private String created_at;
    private String updated_at;
    private String error;

    public Coordenadas(String id,  String id_cita, String id_vehiculo, String lat, String lon, String created_at, String updated_at, String error) {
        this.id = id;
        this.id_cita = id_cita;
        this.id_vehiculo = id_vehiculo;
        this.lat = lat;
        this.lon = lon;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_cita() {
        return id_cita;
    }

    public void setId_cita(String id_cita) {
        this.id_cita = id_cita;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
