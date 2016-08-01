package com.bitrubio.prototipoebitrubio.Entidades;

import android.graphics.drawable.Drawable;
import android.util.StringBuilderPrinter;

/**
 * Created by Orion on 28/03/2016.
 */
public class Metas {
    public int id;
    public int imagen;
    public String titulo;

    public Metas(int id, int imagen, String titulo) {
        this.id = id;
        this.imagen = imagen;
        this.titulo = titulo;
    }
    public Metas(int id, String titulo){
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
