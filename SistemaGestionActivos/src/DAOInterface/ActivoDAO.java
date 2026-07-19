/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOInterface;
import Modelo.Activo;
import java.util.List;
/**
 *
 * @author jotue
 */
public interface ActivoDAO {
    public boolean guardar(Activo activo);
    public boolean actualizar(Activo activo);
    public boolean eliminar(int id);
    public List<Activo> obtenerTodos();
}
