/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jotue
 */
public class Hardware extends Activo {
   private int anniosUso;

    public Hardware() {
    }

    public Hardware(int anniosUso, String idActivo, String nombreActivo, String marca, String tipo, double costoAdquisicion, double costoMantenimiento, String estadoActivo, Custodio custodio) {
        super(idActivo, nombreActivo, marca, tipo, costoAdquisicion, costoMantenimiento, estadoActivo, custodio);
        this.anniosUso = anniosUso;
    }

    public int getAnniosUso() {
        return anniosUso;
    }

    public void setAnniosUso(int anniosUso) {
        this.anniosUso = anniosUso;
    }
}
