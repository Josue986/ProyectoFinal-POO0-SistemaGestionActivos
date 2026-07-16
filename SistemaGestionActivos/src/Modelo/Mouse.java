/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jotue
 */
public class Mouse extends Periferico{
   private int dpi;

    public Mouse(int dpi, int anniosUso, String tipoConexion, String idActivo, 
            String nombreActivo, String marca, String tipo, double costoAdquisicion, 
            double costoMantenimiento, String estadoActivo, Custodio custodio) {
        super(anniosUso, tipoConexion, idActivo, nombreActivo, marca, tipo, 
                costoAdquisicion, costoMantenimiento, estadoActivo, custodio);
        this.dpi = dpi;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mouse{");
        sb.append("dpi=").append(dpi);
        sb.append('}');
        return sb.toString();
    }
}
