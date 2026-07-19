/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import DAOInterface.RegistroMantenimientoDAO;
import Modelo.RegistroMantenimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jotue
 */
public class RegistroMantenimientoDAOImpl implements RegistroMantenimientoDAO {
    // Para guardar y leer las fechas en SQLite de manera consistente como TEXT
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public boolean guardar(RegistroMantenimiento rm) {
        String sql = "INSERT INTO registros_mantenimiento (idMantenimiento, idActivo, fechaMantenimiento, costo) VALUES (?, ?, ?, ?);";
        
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;
            
            ps.setInt(1, rm.getIdMantenimiento());
            ps.setString(2, rm.getActivo().getIdActivo()); // Relación con el Activo asignado
            ps.setString(3, dateFormat.format(rm.getFechaFinal()));
            ps.setDouble(4, rm.getCosto());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al guardar registro de mantenimiento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(RegistroMantenimiento rm) {
        String sql = "UPDATE registros_mantenimiento SET idActivo = ?, detalle = ?, fechaMantenimiento = ?, WHERE idMantenimiento = ?;";
        
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;
            
            ps.setString(1, rm.getActivo().getIdActivo());
            ps.setString(2, dateFormat.format(rm.getFechaFinal()));
            ps.setDouble(3, rm.getCosto());
            ps.setInt(4, rm.getIdMantenimiento());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar registro de mantenimiento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idRegistroMantenimiento) {
        String sql = "DELETE FROM registros_mantenimiento WHERE idRegistroMantenimiento = ?;";
        
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;
            
            ps.setInt(1, idRegistroMantenimiento);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar registro de mantenimiento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<RegistroMantenimiento> obtenerTodos() {
        List<RegistroMantenimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM registros_mantenimiento;";
        
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (conn == null) return lista;
            
            while (rs.next()) {
                RegistroMantenimiento rm = new RegistroMantenimiento();
                rm.setIdMantenimiento(rs.getInt("idMantenimiento"));
                rm.setDetallesMantenimiento(rs.getString("detallesMantenimiento"));
                rm.setCosto(rs.getDouble("costo"));
                
                // Parseo de la fecha desde TEXT a objeto Date de Java
                String strFecha = rs.getString("fechaMantenimiento");
                if (strFecha != null) {
                    try {
                        rm.setFechaFinal(dateFormat.parse(strFecha));
                    } catch (Exception e) {
                        System.err.println("Error parseando fecha de mantenimiento: " + e.getMessage());
                    }
                }
                
                // NOTA: Para rellenar el objeto Activo completo dentro de rm, 
                // se puede usar un ActivoDAO en el controlador o hacer una consulta combinada (JOIN).
                // Por ahora, le asignamos una instancia básica con el ID obtenido para evitar NullPointerException.
                String idActivoAsignado = rs.getString("idActivo");
                if (idActivoAsignado != null) {
                    // Aquí asumimos que tu clase Activo o sus hijas se instancian o buscan de forma polimórfica
                    // rm.setActivo(activoDAO.buscarPorId(idActivoAsignado));
                }
                
                lista.add(rm);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener registros de mantenimiento: " + e.getMessage());
        }
        
        return lista;
    }
}
