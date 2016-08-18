package com.bitrubio.prototipoebitrubio.Entidades;

import android.graphics.drawable.Drawable;

/**
 * Created by Orion on 27/06/2016.
 */
public class Recetas {
    private int id;
    private int img;
    private String medico;
    private String especialidad;
    private String fecha;
    private String nomMedicamento;
    private String dosis;
    private String vigencia;

    public Recetas(int id, int img, String medico, String especialidad, String fecha, String nomMedicamento, String dosis, String vigencia) {
        this.id = id;
        this.img = img;
        this.medico = medico;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.nomMedicamento = nomMedicamento;
        this.dosis = dosis;
        this.vigencia = vigencia;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNomMedicamento() {
        return nomMedicamento;
    }

    public void setNomMedicamento(String nomMedicamento) {
        this.nomMedicamento = nomMedicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }
}
