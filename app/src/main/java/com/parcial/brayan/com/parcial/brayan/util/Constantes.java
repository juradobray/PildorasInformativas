package com.parcial.brayan.com.parcial.brayan.util;

/**
 * Clase que contiene los códigos usados en "I Wish" para
 * mantener la integridad en las interacciones entre actividades
 * y fragmentos
 */
public class Constantes {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;
    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "";
    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "bd-android-juradobray1.c9users.io";
    /**
     * URLs del Web Service
     */

    public static final String GET_LOGIN = "http://" + IP + PUERTO_HOST + "/usario.php";
    public static final String GET_BY_ID = "http://" + IP + PUERTO_HOST + "/obtener_meta_por_id.php";
    public static final String UPDATE = "http://" + IP + PUERTO_HOST + "/actualizar_meta.php";
    public static final String DELETE = "http://" + IP + PUERTO_HOST + "/borrar_meta.php";
    public static final String INSERT = "http://" + IP + PUERTO_HOST + "/insertar_meta.php";

    /**
     * Clave para el valor extra que representa al identificador de una meta
     */
    public static final String EXTRA_ID = "IDEXTRA";

    public static final String MAILFROM= "juradogbray@gmail.com";
    public static final String PASSFROM="sapohpta.";

}
