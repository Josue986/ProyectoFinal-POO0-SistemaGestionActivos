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
public class RegistroMantenimiento {
   private int idMantenimiento;
   private String detallesMantenimiento;
   private Date fechaInicio;
   private Date fechaFinal;
   private double costo;
   private Activo activo;
   private Usuario usuario;

   public RegistroMantenimiento(){
   }

    public RegistroMantenimiento(int idMantenimiento, String detallesMantenimiento, Date fechaInicio, Date fechaFinal, Activo activo, Usuario usuario) {
        this.idMantenimiento = idMantenimiento;
        this.detallesMantenimiento = detallesMantenimiento;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.costo = costo;
        this.activo = activo;
        this.usuario = usuario;
    }
   public int getIdMantenimiento() {
       return idMantenimiento;
   }

   public void setIdMantenimiento(int idMantenimiento) {
       this.idMantenimiento = idMantenimiento;
   }

   public String getDetallesMantenimiento() {
       return detallesMantenimiento;
   }

   public void setDetallesMantenimiento(String detallesMantenimiento) {
       this.detallesMantenimiento = detallesMantenimiento;
   }

   public Date getFechaInicio() {
       return fechaInicio;
   }

   public void setFechaInicio(Date fechaInicio) {
       this.fechaInicio = fechaInicio;
   }

   public Date getFechaFinal() {
       return fechaFinal;
   }

   public void setFechaFinal(Date fechaFinal) {
       this.fechaFinal = fechaFinal;
   }
   public double getCosto() {
       return costo;
   }

   public void setCosto(double costo) {
       this.costo = costo;
   }

   public Activo getActivo() {
       return activo;
   }

   public void setActivo(Activo activo) {
       this.activo = activo;
   }

   public Usuario getUsuario() {
       return usuario;
   }

   public void setUsuario(Usuario usuario) {
       this.usuario = usuario;
   }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RegistroMantenimiento{");
        sb.append("idMantenimiento=").append(idMantenimiento);
        sb.append(", detallesMantenimiento=").append(detallesMantenimiento);
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", fechaFinal=").append(fechaFinal);
        sb.append(", costo=").append(costo);
        sb.append(", activo=").append(activo);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }
}
