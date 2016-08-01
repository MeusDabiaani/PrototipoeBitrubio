package com.bitrubio.prototipoebitrubio.Bitrubian;

/**
 * Created by Mario on 17/02/2016.
 */
public class Comunidad {

    private int id;
    private String nombre ;
    private int numbeat;

    public Comunidad(int id, String nombre, int numbeat) {
        this.id = id;
        this.nombre = nombre;
        this.numbeat = numbeat;
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

    public int getNumbeat() {
        return numbeat;
    }

    public void setNumbeat(int numbeat) {
        this.numbeat = numbeat;
    }


}
