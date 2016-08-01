package com.bitrubio.prototipoebitrubio.Bitrubian;

import java.lang.reflect.Array;

/**
 * Created by Orion on 05/07/2016.
 */
public class Ofertas {

    private int id;
    private int img;
    private String medico,especialidad,fechaPuja,comentarios;
    private float montoPuja;
    private boolean negociar;
    private String modoPago;
    private String aseguradora;
    private String incluye;
    private String tecUtilizada;
    private String[] referencias;

    public Ofertas(int id, int img, String medico, String especialidad, String fechaPuja, String comentarios, float montoPuja, boolean negociar, String modoPago, String aseguradora, String incluye, String tecUtilizada, String[] referencias) {
        this.id = id;
        this.img = img;
        this.medico = medico;
        this.especialidad = especialidad;
        this.fechaPuja = fechaPuja;
        this.comentarios = comentarios;
        this.montoPuja = montoPuja;
        this.negociar = negociar;
        this.modoPago = modoPago;
        this.aseguradora = aseguradora;
        this.incluye = incluye;
        this.tecUtilizada = tecUtilizada;
        this.referencias = referencias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFechaPuja() {
        return fechaPuja;
    }

    public void setFechaPuja(String fechaPuja) {
        this.fechaPuja = fechaPuja;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public float getMontoPuja() {
        return montoPuja;
    }

    public void setMontoPuja(float montoPuja) {
        this.montoPuja = montoPuja;
    }

    public boolean isNegociar() {
        return negociar;
    }

    public void setNegociar(boolean negociar) {
        this.negociar = negociar;
    }

    public String getModoPago() {
        return modoPago;
    }

    public void setModoPago(String modoPago) {
        this.modoPago = modoPago;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public String getTecUtilizada() {
        return tecUtilizada;
    }

    public void setTecUtilizada(String tecUtilizada) {
        this.tecUtilizada = tecUtilizada;
    }

    public String[] getReferencias() {
        return referencias;
    }

    public void setReferencias(String[] referencias) {
        this.referencias = referencias;
    }
}
