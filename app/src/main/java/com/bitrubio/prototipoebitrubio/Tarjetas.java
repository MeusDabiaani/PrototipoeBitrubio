package com.bitrubio.prototipoebitrubio;

import android.graphics.drawable.Drawable;

/**
 * Created by Orion on 17/06/2016.
 */
public class Tarjetas {

    public int id;
    public String nombre_tarjeta;
    public String num1;
    public String num2;
    public String num3;
    public String num4;
    public String vigencia;
    public Drawable fondoColor;

    public Tarjetas(int id, String nombre_tarjeta, String num1, String num2, String num3, String num4, String vigencia, Drawable fondoColor) {
        this.id = id;
        this.nombre_tarjeta = nombre_tarjeta;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.vigencia = vigencia;
        this.fondoColor = fondoColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_tarjeta() {
        return nombre_tarjeta;
    }

    public void setNombre_tarjeta(String nombre_tarjeta) {
        this.nombre_tarjeta = nombre_tarjeta;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }

    public String getNum4() {
        return num4;
    }

    public void setNum4(String num4) {
        this.num4 = num4;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public Drawable getFondoColor() {
        return fondoColor;
    }

    public void setFondoColor(Drawable fondoColor) {
        this.fondoColor = fondoColor;
    }
}
