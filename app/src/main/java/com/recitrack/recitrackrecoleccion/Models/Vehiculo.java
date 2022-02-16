package com.recitrack.recitrackrecoleccion.Models;

public class Vehiculo {
    private String id;
    private String id_empresatrasnporte;
    private String vehiculo;
    private String marca;
    private String modelo;
    private String matricula;
    private String combustible;
    private String nombres;
    private String apellidos;
    private String licencia;
    private String telefono;
    private String pass;
    private String detalle;
    private String error;

    public Vehiculo(String id, String id_empresatrasnporte, String vehiculo, String marca, String modelo, String matricula, String combustible, String nombres, String apellidos, String licencia, String telefono, String pass, String detalle, String error) {
        this.id = id;
        this.id_empresatrasnporte = id_empresatrasnporte;
        this.vehiculo = vehiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.combustible = combustible;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.licencia = licencia;
        this.telefono = telefono;
        this.pass = pass;
        this.detalle = detalle;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_empresatrasnporte() {
        return id_empresatrasnporte;
    }

    public void setId_empresatrasnporte(String id_empresatrasnporte) {
        this.id_empresatrasnporte = id_empresatrasnporte;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
