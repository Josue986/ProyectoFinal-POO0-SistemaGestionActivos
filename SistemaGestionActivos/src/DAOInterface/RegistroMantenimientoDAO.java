/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOInterface;
import Modelo.RegistroMantenimiento;
import java.util.List;
/**
 *
 * @author jotue
 */
public interface RegistroMantenimientoDAO {
    public boolean guardar(RegistroMantenimiento registroMantenimiento);
    public boolean actualizar(RegistroMantenimiento registroMantenimiento);
    public boolean eliminar(int idRegistroMantenimiento);
    public List<RegistroMantenimiento> obtenerTodos();
}
