package com.bitrubio.prototipoebitrubio.Entidades;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Orion on 29/03/2016.
 */
public class DatosPerfilManager {
    //configuracion del archivo
    SharedPreferences formulario;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String NAME_ARCHIVO = "DatosPerfil";

    //formulario pimera parte
    public static String NOMBRE = "nombre";
    public static String FIRST_APE = "paterno";
    public static String SECOND_APE = "materno";
    public static String SEXO = "sexo";
    public static String FECHA_NAC = "1/1/2000";
    public static String CELULAR = "00-00-00-00";
    public static String MAIL = "mail@gamil.com";
    public static String PASSWORD = "pasword";
    public static String DONACIONES = "no";
    public static String SANGRE = "no";
    public static String ORGANOS = "no";

    // formulario segunda parte
    public String TIPO_SANGUINEO = "tipo_sanguineo";
    public String ESTATURA = "estatura";
    public String PESO = "peso";
    public String PREFERENCIA_SEX = "preferencia";
    public String VACUNAS = "vacunas";
    public String DIABETES = "diabetes";
    public String HIPERTENCION = "hipertencion";
    public String CANCER = "cancer";
    public String OTRO_ANT = "otro_ant";
    public String CIRUGIAS = "cirugias";
    public String PADECIMINETOS = "padecimientos_previos";
    public String PADECIMIENTOS_ACT = "padeciminetos_actuales";
    public String EMBARAZADA = "embarazada";
    public String SEMANAS_EMB = "semanas";
    public String ALERGIAS = "alergias";
    public String SUSTANCIAS = "sustancias";
    public String AMBIENTALES = "ambientales";
    public String EJERCICIO = "ejercicio";
    public String TABACO = "tabaco";
    public String ALCOHOL = "alcochol";
    public String DROGAS = "drogas";

    // formulario terecera parte
    public String SEGURO_MEDICO ="seguro_medico";
    public String NUM_IMSS="imss";
    public String NUM_ISSTE = "isste";
    public String NUM_POPULAR = "popular";
    public String OTRO_SEGURO ="otro_seguro";
    public String COMPANIA = "compania";
    public String POLIZA ="poliza";
    public String VIGENCIA ="vigencia";
    public String SEGURO_VIDA="seguro de vida";
    public String COMPANIA_VIDA = "compania_vida";
    public String POLIZA_VIDA ="poliza_vida";
    public String VIGENCIA_VIDA ="vigencia_vida";


//incializa los tipos de datos  todos los datos
    public HashMap<String, String> getDatosPerfil() {
        HashMap<String, String> datos = new HashMap<>();
        datos.put(NOMBRE, formulario.getString(NOMBRE, null));
        datos.put(FIRST_APE, formulario.getString(FIRST_APE, null));
        datos.put(SECOND_APE, formulario.getString(SECOND_APE, null));
        datos.put(SEXO, formulario.getString(SEXO, null));
        datos.put(FECHA_NAC, formulario.getString(FECHA_NAC, null));
        datos.put(CELULAR, formulario.getString(CELULAR, null));
        datos.put(MAIL, formulario.getString(MAIL, null));
        datos.put(PASSWORD, formulario.getString(PASSWORD, null));
        datos.put(DONACIONES, formulario.getString(DONACIONES, null));
        datos.put(SANGRE, formulario.getString(SANGRE, null));
        datos.put(ORGANOS, formulario.getString(ORGANOS, null));
        datos.put(TIPO_SANGUINEO, formulario.getString(TIPO_SANGUINEO, null));
        datos.put(ESTATURA, formulario.getString(ESTATURA, null));
        datos.put(PESO, formulario.getString(PESO, null));
        datos.put(PREFERENCIA_SEX, formulario.getString(PREFERENCIA_SEX, null));
        datos.put(VACUNAS, formulario.getString(VACUNAS, null));
        datos.put(DIABETES, formulario.getString(DIABETES, null));
        datos.put(HIPERTENCION, formulario.getString(HIPERTENCION, null));
        datos.put(CANCER, formulario.getString(CANCER, null));
        datos.put(PADECIMINETOS, formulario.getString(PADECIMINETOS, null));
        datos.put(PADECIMIENTOS_ACT, formulario.getString(PADECIMIENTOS_ACT, null));
        datos.put(CIRUGIAS, formulario.getString(CIRUGIAS, null));
        datos.put(EMBARAZADA, formulario.getString(EMBARAZADA, null));
        datos.put(SEMANAS_EMB, formulario.getString(SEMANAS_EMB, null));
        datos.put(ALERGIAS, formulario.getString(ALERGIAS, null));
        datos.put(SUSTANCIAS, formulario.getString(SUSTANCIAS, null));
        datos.put(AMBIENTALES, formulario.getString(AMBIENTALES, null));
        datos.put(EJERCICIO, formulario.getString(EJERCICIO, null));
        datos.put(TABACO, formulario.getString(TABACO, null));
        datos.put(ALCOHOL, formulario.getString(ALCOHOL, null));
        datos.put(DROGAS, formulario.getString(DROGAS, null));
        datos.put(SEGURO_MEDICO,formulario.getString(SEGURO_MEDICO,null));
        datos.put(NUM_IMSS,formulario.getString(NUM_IMSS,null));
        datos.put(NUM_ISSTE,formulario.getString(NUM_ISSTE,null));
        datos.put(NUM_POPULAR,formulario.getString(NUM_POPULAR,null));
        datos.put(OTRO_SEGURO,formulario.getString(OTRO_SEGURO,null));
        datos.put(COMPANIA,formulario.getString(COMPANIA,null));
        datos.put(POLIZA,formulario.getString(POLIZA,null));
        datos.put(VIGENCIA,formulario.getString(VIGENCIA,null));
        datos.put(SEGURO_VIDA,formulario.getString(SEGURO_VIDA,null));
        datos.put(COMPANIA_VIDA,formulario.getString(COMPANIA_VIDA,null));
        datos.put(POLIZA_VIDA,formulario.getString(POLIZA_VIDA,null));
        datos.put(VIGENCIA_VIDA,formulario.getString(VIGENCIA_VIDA,null));
        return datos;
    }
// guarda los datos de la la primera seccion
    public void insertDatosA(String nombre, String f_ape, String s_ape, String sexo, String fecha_nac,
                             String celular, String mail, String password, String donaciones, String sangre, String organos) {

        editor.putString(NOMBRE, nombre);
        editor.putString(FIRST_APE, f_ape);
        editor.putString(SECOND_APE, s_ape);
        editor.putString(SEXO, sexo);
        editor.putString(FECHA_NAC, fecha_nac);
        editor.putString(CELULAR, celular);
        editor.putString(MAIL, mail);
        editor.putString(PASSWORD, password);
        editor.putString(DONACIONES, donaciones);
        editor.putString(SANGRE, sangre);
        editor.putString(ORGANOS, organos);
        editor.commit();
    }
// guarda todos los datos de la parte fisica
    public void insertDatosB(String tipo_sanguineo, String estatura, String peso, String preferecnia_sex,
                             String vacunas, String diabetes, String hipertencion, String cancer, String otro_ant,
                             String padecimientos, String cirugias, String padecimientos_act, String embarazada,
                             String semanas_emb, String alergias, String sustancias, String ambientales,
                             String ejercicio, String tabaco, String alcohol, String drogas) {

        editor.putString(TIPO_SANGUINEO, tipo_sanguineo);
        editor.putString(ESTATURA, estatura);
        editor.putString(PESO, peso);
        editor.putString(PREFERENCIA_SEX, preferecnia_sex);
        editor.putString(VACUNAS, vacunas);
        editor.putString(DIABETES, diabetes);
        editor.putString(HIPERTENCION, hipertencion);
        editor.putString(CANCER, cancer);
        editor.putString(OTRO_ANT, otro_ant);
        editor.putString(PADECIMINETOS, padecimientos);
        editor.putString(CIRUGIAS, cirugias);
        editor.putString(PADECIMIENTOS_ACT, padecimientos_act);
        editor.putString(EMBARAZADA, embarazada);
        editor.putString(SEMANAS_EMB, semanas_emb);
        editor.putString(ALERGIAS, alergias);
        editor.putString(SUSTANCIAS, sustancias);
        editor.putString(AMBIENTALES, ambientales);
        editor.putString(EJERCICIO, ejercicio);
        editor.putString(TABACO, tabaco);
        editor.putString(ALCOHOL, alcohol);
        editor.putString(DROGAS, drogas);
        editor.commit();
    }
    // guarda todos los datos de la seccion de seguros
    public void insertDatosC(String segMedico,String imss,String isste,String popular,
                             String otroSeg,String compania , String poliza,String vigencia,
                             String segVida,String compVida,String polizaVida,String vigenciaVida){
        editor.putString(SEGURO_MEDICO,segMedico);
        editor.putString(NUM_IMSS,imss);
        editor.putString(NUM_ISSTE,isste);
        editor.putString(NUM_POPULAR,popular);
        editor.putString(OTRO_SEGURO,otroSeg);
        editor.putString(COMPANIA,compania);
        editor.putString(POLIZA,poliza);
        editor.putString(VIGENCIA,vigencia);
        editor.putString(SEGURO_VIDA,segVida);
        editor.putString(COMPANIA_VIDA,compVida);
        editor.putString(POLIZA_VIDA,polizaVida) ;
        editor.putString(VIGENCIA_VIDA,vigenciaVida);
        editor.commit();
    }


    public void editPadecimientoPrev(String  padPrev){
        editor.putString(PADECIMIENTOS_ACT,padPrev);
        editor.commit();
    }
    public void editPadecimientoActual(String  pad){
        editor.putString(PADECIMINETOS,pad);
        editor.commit();
    }



    public DatosPerfilManager(Context context) {
        this._context = context;
        formulario = _context.getSharedPreferences(NAME_ARCHIVO, PRIVATE_MODE);
        editor = formulario.edit();

    }





}
