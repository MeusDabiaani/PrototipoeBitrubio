package com.bitrubio.prototipoebitrubio.Entidades;

import android.graphics.drawable.Drawable;

/**
 * Created by Orion on 22/06/2016.
 */
public class Productos {

    int id;
    String producto;
    String descripcion;
    Drawable img;
    float cantidad;
    float precio;
    float descuento;

    public Productos(int id, String producto, String descripcion, Drawable img, float cantidad, float precio, float descuento) {
        this.id = id;
        this.producto = producto;
        this.descripcion = descripcion;
        this.img = img;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
}
