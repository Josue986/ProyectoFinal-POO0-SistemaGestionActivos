/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import DAOInterface.ActivoDAO;
import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jotue
 */
public class ActivoDAOImpl implements ActivoDAO {

    // El formato estándar para guardar fechas en SQLite como TEXT
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public boolean guardar(Activo activo) {
        String sqlActivo = "INSERT INTO activos (idActivo, nombreActivo, marca, tipo, costoAdquisicion, costoMantenimiento, estadoActivo, cedula_custodio) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        Connection conn = null;
        PreparedStatement psActivo = null;

        try {
            conn = ConexionSQLite.conectar();
            if (conn == null) {
                return false;
            }

            //Desactivamos el autocommit para manejar la inserción como una sola transacción atómica
            conn.setAutoCommit(false);

            // 1. Insertar en la tabla base 'activos'
            psActivo = conn.prepareStatement(sqlActivo);
            psActivo.setString(1, activo.getIdActivo());
            psActivo.setString(2, activo.getNombreActivo());
            psActivo.setString(3, activo.getMarca());
            psActivo.setString(4, activo.getTipo());
            psActivo.setDouble(5, activo.getCostoAdquisicion());
            psActivo.setDouble(6, activo.getCostoMantenimiento());
            psActivo.setString(7, activo.getEstadoActivo());

            if (activo.getCustodio() != null) {
                psActivo.setString(8, activo.getCustodio().getCedula());
            } else {
                psActivo.setNull(8, java.sql.Types.VARCHAR);
            }

            psActivo.executeUpdate();

            // 2. Identificar el tipo específico de activo mediante polimorfismo y guardar en su tabla correspondiente
            if (activo instanceof Case) {
                Case c = (Case) activo;
                String sqlCase = "INSERT INTO hardware_cases (idActivo, anniosUso, procesador, memoriaRAM, almacenamiento) VALUES (?, ?, ?, ?, ?);";
                try (PreparedStatement psCase = conn.prepareStatement(sqlCase)) {
                    psCase.setString(1, c.getIdActivo());
                    psCase.setInt(2, c.getAnniosUso());
                    psCase.setString(3, c.getProcesador());
                    psCase.setString(4, c.getMemoriaRAM());
                    psCase.setString(5, c.getAlmacenamiento());
                    psCase.executeUpdate();
                }
            } else if (activo instanceof Mouse) {
                Mouse m = (Mouse) activo;
                String sqlMouse = "INSERT INTO perifericos (idActivo, anniosUso, tipoConexion, dpi) VALUES (?, ?, ?, ?);";
                try (PreparedStatement psMouse = conn.prepareStatement(sqlMouse)) {
                    psMouse.setString(1, m.getIdActivo());
                    psMouse.setInt(2, m.getAnniosUso());
                    psMouse.setString(3, m.getTipoConexion());
                    psMouse.setInt(4, m.getDpi());
                    psMouse.executeUpdate();
                }
            } else if (activo instanceof Monitor) {
                Monitor mon = (Monitor) activo;
                String sqlMonitor = "INSERT INTO perifericos (idActivo, anniosUso, tipoConexion, resolucion, tasaDeRefresco) VALUES (?, ?, ?, ?, ?);";
                try (PreparedStatement psMonitor = conn.prepareStatement(sqlMonitor)) {
                    psMonitor.setString(1, mon.getIdActivo());
                    psMonitor.setInt(2, mon.getAnniosUso());
                    psMonitor.setString(3, mon.getTipoConexion());
                    psMonitor.setString(4, mon.getResolucion());
                    psMonitor.setString(5, mon.getTasaDeRefresco());
                    psMonitor.executeUpdate();
                }
            } else if (activo instanceof Licencia) {
                Licencia lic = (Licencia) activo;
                String sqlLicencia = "INSERT INTO licencias (idActivo, fechaExpiracion, costo) VALUES (?, ?, ?);";
                try (PreparedStatement psLicencia = conn.prepareStatement(sqlLicencia)) {
                    psLicencia.setString(1, lic.getIdActivo());
                    psLicencia.setString(2, dateFormat.format(lic.getFechaExpiracion()));
                    psLicencia.setDouble(3, lic.getCosto());
                    psLicencia.executeUpdate();
                }
            }

            // Confirmamos la transacción si todo salió bien
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar el activo: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback(); // Deshace los cambios si hubo error
                } catch (SQLException ex) {
                    System.err.println("Error en el rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            // Cerramos recursos
            try {
                if (psActivo != null) {
                    psActivo.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean actualizar(Activo activo) {
        // TODO: Implementar lógica de actualización con consultas UPDATE
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM activos WHERE idActivo = ?;";
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null) {
                return false;
            }
            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el activo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Activo> obtenerTodos() {
        List<Activo> listaActivos = new ArrayList<>();
        // Usamos LEFT JOIN para traer los datos sin importar en qué tabla hija estén
        String sql = "SELECT a.*, h.anniosUso as h_uso, h.procesador, h.memoriaRAM, h.almacenamiento, "
                + "p.anniosUso as p_uso, p.tipoConexion, p.dpi, p.resolucion, p.tasaDeRefresco, "
                + "l.fechaExpiracion, l.costo as costoLicencia "
                + "FROM activos a "
                + "LEFT JOIN hardware_cases h ON a.idActivo = h.idActivo "
                + "LEFT JOIN perifericos p ON a.idActivo = p.idActivo "
                + "LEFT JOIN licencias l ON a.idActivo = l.idActivo";

        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql); java.sql.ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Activo activo = null;

                // Instanciamos el objeto según el tipo guardado en la BD
                if ("Case".equals(tipo)) {
                    activo = new Case(rs.getString("procesador"), rs.getString("memoriaRAM"),
                            rs.getString("almacenamiento"), rs.getInt("h_uso"),
                            rs.getString("idActivo"), rs.getString("nombreActivo"),
                            rs.getString("marca"), tipo, rs.getDouble("costoAdquisicion"),
                            rs.getDouble("costoMantenimiento"), rs.getString("estadoActivo"), null);
                } else if ("Mouse".equals(tipo)) {
                    activo = new Mouse(rs.getInt("dpi"), rs.getInt("p_uso"), rs.getString("tipoConexion"),
                            rs.getString("idActivo"), rs.getString("nombreActivo"), rs.getString("marca"),
                            tipo, rs.getDouble("costoAdquisicion"), rs.getDouble("costoMantenimiento"),
                            rs.getString("estadoActivo"), null);
                }
                // ... agregar lógica para Monitor y Licencia siguiendo el mismo patrón ...

                if (activo != null) {
                    listaActivos.add(activo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener activos: " + e.getMessage());
        }
        return listaActivos;
    }
}
