package com.bitrubio.prototipoebitrubio.Bitrubian;

/**
 * Created by Mario on 11/01/2016.
 */
public class Mensajes {

    private int id;
    private String nommbre;
    private String mensaje ;
    private String fecha;
    private String idContacto;
 public Mensajes(){}
    public Mensajes(int id,String nombre, String mensaje, String fecha,String idContacto) {
        this.id = id;
        this.nommbre= nombre;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.idContacto = idContacto;
    }
    public String getIdContacto() {
        return idContacto;
    }

    public String getNommbre() {
        return nommbre;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getMensaje() {
        return mensaje;
    }

    public String getFecha() {
        return fecha;
    }


}
