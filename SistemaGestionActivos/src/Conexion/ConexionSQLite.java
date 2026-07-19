/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author jotue
 */
public class ConexionSQLite {
    private static final String DIRECTORIO = "db";
    private static final String ARCHIVO_DB = "activos_tecnologicos.db";
    private static final String URL = "jdbc:sqlite:" + DIRECTORIO + "/" + ARCHIVO_DB;

    public static Connection conectar() {
        try {
            File directorio = new File(DIRECTORIO);
            if (!directorio.exists()) {
                directorio.mkdirs(); 
            }
            
            Class.forName("org.sqlite.JDBC");
            
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            System.err.println("Error!: No se encontro el driver JDBC de SQLite en las librerias: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Error!: No se pudo establecer la conexion con SQLite: " + e.getMessage());
            return null;
        }
    }
}
