/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jotue
 */
public class Custodio {
    private String cedula;
    private String nombre;
    private String rol;

    public Custodio() {
    }

    public Custodio(String cedula, String nombre, String rol) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Custodio{");
        sb.append("cedula=").append(cedula);
        sb.append(", nombre=").append(nombre);
        sb.append(", rol=").append(rol);
        sb.append('}');
        return sb.toString();
    }
}
