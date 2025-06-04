package datos;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos {
    //Atributos
    private List <Object> clientes;
    private List <Object> barberos;
    private List <Object> reservas;
    private List <Object> servicios;
    private List <Object> usuarios;

    //definir donde se van a guardar los archivos para la serialización.
    private static final String CLIENTES_FILE = "clientes.ser";
    private static final String BARBEROS_FILE = "barberos.ser";
    private static final String SERVICIOS_FILE = "servicios.ser";
    private static final String RESERVAS_FILE = "reservas.ser";
    private static final String USUARIOS_FILE = "usuarios.ser";


}
