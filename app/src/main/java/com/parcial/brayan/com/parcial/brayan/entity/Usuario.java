package com.parcial.brayan.com.parcial.brayan.entity;

import java.io.Serializable;

/**
 * Created by Brayan on 03/05/2017.
 */

public class Usuario implements Serializable {

    private int id_usuario;
    private String email;
    private String contrasena;
    private String nombre_completo;
    private int id_facultad;
    private int id_perfil;

    public Usuario() {
    }

    public Usuario(int id_usuario, String email, String contrasena, String nombre_completo, int id_facultad, int id_perfil) {
        this.id_usuario = id_usuario;
        this.email = email;
        this.contrasena = contrasena;
        this.nombre_completo = nombre_completo;
        this.id_facultad = id_facultad;
        this.id_perfil = id_perfil;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public int getId_facultad() {
        return id_facultad;
    }

    public void setId_facultad(int id_facultad) {
        this.id_facultad = id_facultad;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }
}
