package com.bitrubio.prototipoebitrubio.Entidades;

/**
 * Created by Orion on 09/08/2016.
 */
public class GlobalMetaPeso {
    private GlobalMetaPeso() {
    }

    private static GlobalMetaPeso instance;

    private String nombre;
    private int idfoto;
    private int tipoMeta;
    private int pesoActual;
    private int pesoObjetivo;
    private int tipoTiempo;
    private int tiempoMeta;
    private int tipoPrivacidad;
    private StringBuilder retaAmigos;
    private StringBuilder equipoAmigos;




    public static void setIntance(GlobalMetaPeso intance) {
        GlobalMetaPeso.instance = intance;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(int idfoto) {
        this.idfoto = idfoto;
    }

    public StringBuilder getRetaAmigos() {
        return retaAmigos;
    }
    public StringBuilder getEquipoAmigos() {
        return equipoAmigos;
    }

    public void setRetaAmigos(StringBuilder retaAmigos) {
        this.retaAmigos = retaAmigos;
    }
    public void setEquipoAmigos(StringBuilder equipoAmigos) {
        this.equipoAmigos = equipoAmigos;
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

    public int getTipoTiempo() {
        return tipoTiempo;
    }

    public void setTipoTiempo(int tipoTiempo) {
        this.tipoTiempo = tipoTiempo;
    }

    public static synchronized GlobalMetaPeso getInstance() {
        if (instance == null) {
            instance = new GlobalMetaPeso();
        }
    return instance;
    }
}
