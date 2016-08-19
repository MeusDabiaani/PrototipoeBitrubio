package com.bitrubio.prototipoebitrubio.Entidades;

/**
 * Created by Mario on 22/12/2015.
 */
public class Bitrubian {

    int id;
    String nombre;
    String ape_pat;
    String mail;
    String success;
    String idUsuario;

    public Bitrubian() {}


    public Bitrubian(int id, String nombre, String ape_pat, String mail, String success, String idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.ape_pat = ape_pat;
        this.mail = mail;
        this.success = success;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe_pat() {
        return ape_pat;
    }

    public void setApe_pat(String ape_pat) {
        this.ape_pat = ape_pat;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}

