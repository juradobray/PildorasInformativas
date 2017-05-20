package com.parcial.brayan.com.parcial.brayan.entity;

import java.util.Date;

/**
 * Created by Brayan on 03/05/2017.
 */

public class Publicacion {

    private int id_publicacion;
    private String titulo_publicacion;
    private String contenido_publicacion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private int id_usuario;
    private String imagen;
    private int id_tipo_publicacion;

    public Publicacion() {
    }

    public Publicacion(int id_publicacion, String titulo_publicacion, String contenido_publicacion, Date fecha_inicio, Date fecha_fin, int id_usuario, String imagen, int id_tipo_publicacion) {
        this.id_publicacion = id_publicacion;
        this.titulo_publicacion = titulo_publicacion;
        this.contenido_publicacion = contenido_publicacion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.id_usuario = id_usuario;
        this.imagen = imagen;
        this.id_tipo_publicacion = id_tipo_publicacion;
    }

    public int getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getTitulo_publicacion() {
        return titulo_publicacion;
    }

    public void setTitulo_publicacion(String titulo_publicacion) {
        this.titulo_publicacion = titulo_publicacion;
    }

    public String getContenido_publicacion() {
        return contenido_publicacion;
    }

    public void setContenido_publicacion(String contenido_publicacion) {
        this.contenido_publicacion = contenido_publicacion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId_tipo_publicacion() {
        return id_tipo_publicacion;
    }

    public void setId_tipo_publicacion(int id_tipo_publicacion) {
        this.id_tipo_publicacion = id_tipo_publicacion;
    }
}
