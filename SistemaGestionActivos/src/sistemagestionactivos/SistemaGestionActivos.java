package sistemagestionactivos;

import Conexion.ActivoDAOImpl;
import DAOInterface.ActivoDAO;

public class SistemaGestionActivos {

    public static void main(String[] args) {
        // 1. Instanciamos el DAO (la persistencia)
        ActivoDAO dao = new ActivoDAOImpl();     
        // 2. Instanciamos la Vista (cuando la tengas creada)      
        // 3. Instanciamos el Controlador, pasando la vista y el dao       
        // 4. Arrancamos    
        System.out.println("Sistema inicializado. Falta conectar la Vista y el Controlador.");
    }
}
