package com.bitrubio.prototipoebitrubio;

import android.graphics.drawable.Drawable;

/**
 * Created by Orion on 09/06/2016.
 */
public class Anuncios {
    private int id;
    private String nom_proveedor;
    private String titulo;
    private Drawable imagen;
    private float monto_ini;
    private float monto_fin;
    private String fecha_ini;
    private String fecha_fin;
    private int calificacion ;
    private int num_compras;
    private int aplausos;
    private int mensajes;
    private Drawable logo;

    public Anuncios(int id, String nom_proveedor, String titulo, Drawable imagen, float monto_ini, float monto_fin, String fecha_ini, String fecha_fin, int calificacion, int num_compras, int aplausos, int mensajes, Drawable logo) {
        this.id = id;
        this.nom_proveedor = nom_proveedor;
        this.titulo = titulo;
        this.imagen = imagen;
        this.monto_ini = monto_ini;
        this.monto_fin = monto_fin;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
        this.calificacion = calificacion;
        this.num_compras = num_compras;
        this.aplausos = aplausos;
        this.mensajes = mensajes;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_proveedor() {
        return nom_proveedor;
    }

    public void setNom_proveedor(String nom_proveedor) {
        this.nom_proveedor = nom_proveedor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }

    public float getMonto_ini() {
        return monto_ini;
    }

    public void setMonto_ini(float monto_ini) {
        this.monto_ini = monto_ini;
    }

    public float getMonto_fin() {
        return monto_fin;
    }

    public void setMonto_fin(float monto_fin) {
        this.monto_fin = monto_fin;
    }

    public String getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(String fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getNum_compras() {
        return num_compras;
    }

    public void setNum_compras(int num_compras) {
        this.num_compras = num_compras;
    }

    public int getAplausos() {
        return aplausos;
    }

    public void setAplausos(int aplausos) {
        this.aplausos = aplausos;
    }

    public int getMensajes() {
        return mensajes;
    }

    public void setMensajes(int mensajes) {
        this.mensajes = mensajes;
    }

    public Drawable getLogo() {
        return logo;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
    }
}
