package com.bitrubio.prototipoebitrubio.Entidades;

/**
 * Created by Orion on 09/08/2016.
 */
public class GlobalMetaPeso {
    private GlobalMetaPeso() {
    }

    private static GlobalMetaPeso instance;


    private int tipoMeta;
    private int pesoActual;
    private int pesoObjetivo;
    private int tiempoMeta;
    private int tipoPrivacidad;


    public static void setIntance(GlobalMetaPeso intance) {
        GlobalMetaPeso.instance = intance;
    }

    public int getTipoMeta() {
        return tipoMeta;
    }

    public void setTipoMeta(int tipoMeta) {
        this.tipoMeta = tipoMeta;
    }

    public int getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(int pesoActual) {
        this.pesoActual = pesoActual;
    }

    public int getPesoObjetivo() {
        return pesoObjetivo;
    }

    public void setPesoObjetivo(int pesoObjetivo) {
        this.pesoObjetivo = pesoObjetivo;
    }

    public int getTiempoMeta() {
        return tiempoMeta;
    }

    public void setTiempoMeta(int tiempoMeta) {
        this.tiempoMeta = tiempoMeta;
    }

    public int getTipoPrivacidad() {
        return tipoPrivacidad;
    }

    public void setTipoPrivacidad(int tipoPrivacidad) {
        this.tipoPrivacidad = tipoPrivacidad;
    }

    public static synchronized GlobalMetaPeso getInstance() {
        if (instance == null) {
            instance = new GlobalMetaPeso();
        }
    return instance;
    }
}
