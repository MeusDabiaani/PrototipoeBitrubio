package com.bitrubio.prototipoebitrubio.Entidades;

/**
 * Created by Orion on 05/07/2016.
 */
public class Subastas {

    private int id;
    private int tipo;
    private String nombre;
    private String tipoNombre;
    private String fechaIni;
    private String fechaFin;
    private String comentario;
    private int imagen;
    private int comentarios;

    public Subastas(int id, int tipo, String nombre, String tipoNombre, String fechaIni, String fechaFin, String comentario, int imagen, int comentarios) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.tipoNombre = tipoNombre;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.comentario = comentario;
        this.imagen = imagen;
        this.comentarios = comentarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getComentarios() {
        return comentarios;
    }

    public void setComentarios(int comentarios) {
        this.comentarios = comentarios;
    }
}
