package com.bitrubio.prototipoebitrubio.Entidades;

/**
 * Created by Orion on 14/06/2016.
 */
public class Consultas {
    private int id;
    private int idMedico;
    private int idEspecialidad;
    private String diagnostico;
    private String tratamiento;
    private String fechaConsulta;
    private String observaciones;
    private float montoConsulta;
    private int formaPago;
    private int calificacion;

    public Consultas(int id, int idMedico, int idEspecialidad, String diagnostico, String tratamiento, String fechaConsulta, String observaciones, float montoConsulta, int formaPago, int calificacion) {
        this.id = id;
        this.idMedico = idMedico;
        this.idEspecialidad = idEspecialidad;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.fechaConsulta = fechaConsulta;
        this.observaciones = observaciones;
        this.montoConsulta = montoConsulta;
        this.formaPago = formaPago;
        this.calificacion = calificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public float getMontoConsulta() {
        return montoConsulta;
    }

    public void setMontoConsulta(float montoConsulta) {
        this.montoConsulta = montoConsulta;
    }

    public int getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(int formaPago) {
        this.formaPago = formaPago;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}

