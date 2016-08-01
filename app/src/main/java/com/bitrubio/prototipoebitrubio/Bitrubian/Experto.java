package com.bitrubio.prototipoebitrubio.Bitrubian;

/**
 * Created by Mario on 17/02/2016.
 */
public class Experto {
    private int id;
    private String nombre;
    private String direccion;
    private String especialidad;
    private String telefono;
    private int numContactos;

    public Experto(int id, String nombre, String direccion, String especialidad,String telefono, int numContactos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.numContactos = numContactos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNumContactos() {
        return numContactos;
    }

    public void setNumContactos(int numContactos) {
        this.numContactos = numContactos;
    }
}
