package com.recitrack.recitrackrecoleccion.Models;

public class Recolector {
    private String id;
    private String id_planta;
    private String recolector;
    private String mail;
    private String telefono;
    private String pass;
    private String error;

    public Recolector(String id, String id_planta, String recolector, String mail, String telefono, String pass, String error) {
        this.id = id;
        this.id_planta = id_planta;
        this.recolector = recolector;
        this.mail = mail;
        this.telefono = telefono;
        this.pass = pass;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_planta() {
        return id_planta;
    }

    public void setId_planta(String id_planta) {
        this.id_planta = id_planta;
    }

    public String getRecolector() {
        return recolector;
    }

    public void setRecolector(String recolector) {
        this.recolector = recolector;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
