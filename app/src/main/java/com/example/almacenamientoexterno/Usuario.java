package com.example.almacenamientoexterno;

public class Usuario {

    private int id_usuario;
    private String nombre_usuario;
    private String apellidos_usuario;
    private String email;
    private int edad;
    private int telefono;


    public Usuario(String nombre_usuario, String apellidos_usuario, String email, int edad, int telefono) {

        this.nombre_usuario = nombre_usuario;
        this.apellidos_usuario = apellidos_usuario;
        this.email = email;
        this.edad = edad;
        this.telefono = telefono;
    }

    public Usuario(){

    }

    public Usuario(int id_usuario){
        this.id_usuario = id_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellidos_usuario() {
        return apellidos_usuario;
    }

    public void setApellidos_usuario(String apellidos_usuario) {
        this.apellidos_usuario = apellidos_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre_usuario='" + nombre_usuario + '\'' +
                ", apellidos_usuario='" + apellidos_usuario + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", telefono=" + telefono +
                '}';
    }
}
