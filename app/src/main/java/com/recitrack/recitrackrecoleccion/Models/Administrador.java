package com.recitrack.recitrackrecoleccion.Models;

public class Administrador {

    private String id;
    private String mail;
    private String pass;
    private String administrador;

    public Administrador(String id,String administrador,String mail, String pass){
        this.id=id;
        this.administrador=administrador;
        this.mail=mail;
        this.pass=pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
