package com.bitrubio.prototipoebitrubio.Bitrubian;

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
    public Bitrubian(int id, String nombre, String ape_pat, String mail, String success,String idUsuario) {
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

    public String getNombre() {
        return nombre;
    }

    public String getApe_pat() {
        return ape_pat;
    }

    public String getMail() {
        return mail;
    }

    public String getSuccess() {
        return success;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

}
