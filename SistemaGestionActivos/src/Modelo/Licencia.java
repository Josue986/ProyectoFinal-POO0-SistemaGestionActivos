/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.Date;

/**
 *
 * @author jotue
 */
public class Licencia extends Activo{
    private Date fechaExpiracion;
    private double costo;

    public Licencia(Date fechaExpiracion, double costo, String idActivo, String nombreActivo, String marca, String tipo, double costoAdquisicion, double costoMantenimiento, String estadoActivo, Custodio custodio) {
        super(idActivo, nombreActivo, marca, tipo, costoAdquisicion, costoMantenimiento, estadoActivo, custodio);
        this.fechaExpiracion = fechaExpiracion;
        this.costo = costo;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Licencia{" + "fechaExpiracion=" + fechaExpiracion + ", costo=" + costo + '}';
    }
}
