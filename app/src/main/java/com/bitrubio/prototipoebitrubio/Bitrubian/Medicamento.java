package com.bitrubio.prototipoebitrubio.Bitrubian;

/**
 * Created by Orion on 01/07/2016.
 */
public class Medicamento {
    int id;
    int img;
    String nombre;
    String activo;
    String laboratorio;
    String dosis;
    float cantidad;
    float costo;

    public float getCantidad() {
        return cantidad;
    }

    public float getCosto() {
        return costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public Medicamento(int id, int img, String nombre, String activo, String laboratorio, String dosis) {
        this.id = id;
        this.img = img;
        this.nombre = nombre;
        this.activo = activo;
        this.laboratorio = laboratorio;
        this.dosis = dosis;
    }

    public Medicamento(int id, int img, String nombre, String activo, String laboratorio, float cantidad, float costo) {
        this.id = id;
        this.img = img;
        this.nombre = nombre;
        this.activo = activo;
        this.laboratorio = laboratorio;
        this.cantidad = cantidad;
        this.costo = costo;
    }
}
