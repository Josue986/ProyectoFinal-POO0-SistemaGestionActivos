/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jotue
 */
public abstract class Activo {
   private String idActivo;
   private String nombreActivo;
   private String marca;
   private String tipo;
   private double costoAdquisicion;
   private double costoMantenimiento;
   private String estadoActivo;
   private Custodio custodio;

   public Activo() {
   }

    public Activo(String idActivo, String nombreActivo, String marca, String tipo, double costoAdquisicion, double costoMantenimiento, String estadoActivo, Custodio custodio) {
        this.idActivo = idActivo;
        this.nombreActivo = nombreActivo;
        this.marca = marca;
        this.tipo = tipo;
        this.costoAdquisicion = costoAdquisicion;
        this.costoMantenimiento = costoMantenimiento;
        this.estadoActivo = estadoActivo;
        this.custodio = custodio;
    }

   public String getIdActivo() {
       return idActivo;
   }

   public void setIdActivo(String idActivo) {
       this.idActivo = idActivo;
   }

   public String getNombreActivo() {
       return nombreActivo;
   }

   public void setNombreActivo(String nombreActivo) {
       this.nombreActivo = nombreActivo;
   }

   public String getMarca() {
       return marca;
   }

   public void setMarca(String marca) {
       this.marca = marca;
   }

   public String getTipo() {
       return tipo;
   }

   public void setTipo(String tipo) {
       this.tipo = tipo;
   }

   public double getCostoAdquisicion() {
       return costoAdquisicion;
   }

   public void setCostoAdquisicion(double costoAdquisicion) {
       this.costoAdquisicion = costoAdquisicion;
   }

   public double getCostoMantenimiento() {
       return costoMantenimiento;
   }

   public void setCostoMantenimiento(double costoMantenimiento) {
       this.costoMantenimiento = costoMantenimiento;
   }

   public String getEstadoActivo() {
       return estadoActivo;
   }

   public void setEstadoActivo(String estadoActivo) {
       this.estadoActivo = estadoActivo;
   }

   public Custodio getCustodio() {
       return custodio;
   }

   public void setCustodio(Custodio custodio) {
       this.custodio = custodio;
   }
}
