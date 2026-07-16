/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jotue
 */
public class Case extends Hardware {
   private String Procesador;
   private String memoriaRAM;
   private String almacenamiento;

    public Case(String Procesador, String memoriaRAM, String almacenamiento, int anniosUso, String idActivo, String nombreActivo, String marca, String tipo, double costoAdquisicion, double costoMantenimiento, String estadoActivo, Custodio custodio) {
        super(anniosUso, idActivo, nombreActivo, marca, tipo, costoAdquisicion, costoMantenimiento, estadoActivo, custodio);
        this.Procesador = Procesador;
        this.memoriaRAM = memoriaRAM;
        this.almacenamiento = almacenamiento;
    }
    public String getProcesador() {
        return Procesador;
    }

    public void setProcesador(String Procesador) {
        this.Procesador = Procesador;
    }

    public String getMemoriaRAM() {
        return memoriaRAM;
    }

    public void setMemoriaRAM(String memoriaRAM) {
        this.memoriaRAM = memoriaRAM;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Case{");
        sb.append("Procesador=").append(Procesador);
        sb.append(", memoriaRAM=").append(memoriaRAM);
        sb.append(", almacenamiento=").append(almacenamiento);
        sb.append('}');
        return sb.toString();
    }
}
