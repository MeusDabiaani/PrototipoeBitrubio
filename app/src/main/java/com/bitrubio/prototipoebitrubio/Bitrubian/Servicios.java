package com.bitrubio.prototipoebitrubio.Bitrubian;

/**
 * Created by Orion on 15/03/2016.
 */
public class Servicios {
    private int id;
    private String nombre;
    private int orden;

    public Servicios(int id, String nombre, int orden) {
        this.id = id;
        this.nombre = nombre;
        this.orden = orden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}