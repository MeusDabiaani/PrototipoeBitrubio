package com.bitrubio.prototipoebitrubio.Entidades;

/**
 * Created by Mario on 17/02/2016.
 */
public class Comunidad {

    private int id;
    private String nombre ;
    private int numbeat;
    private boolean checked = false;


    public Comunidad(int id, String nombre, int numbeat) {
        this.id = id;
        this.nombre = nombre;
        this.numbeat = numbeat;
    }

    public Comunidad(int id, String nombre, int numbeat, boolean checked) {
        this.id = id;
        this.nombre = nombre;
        this.numbeat = numbeat;
        this.checked = checked;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public void toggleChecked()
    {
        checked = !checked;
    }
}
