package datos; // Asegúrate de que este sea el paquete correcto para tu clase BaseDeDatos

import java.io.*;          // Para manejo de archivos y serialización (FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream)
import java.util.ArrayList; // Para usar la implementación ArrayList de las listas
import java.util.List;     // Para declarar las variables como interfaces List

// Descomentar y ajustar el paquete 'modelo' cuando tus compañeros provean estas clases
// import modelo.Usuario;
// import modelo.Barbero;
// import modelo.Cliente;
// import modelo.Horario;
// import modelo.Reserva;
// import modelo.Servicio;
// import modelo.EstadoReserva; // Si es un enum, también debe ser Serializable si se guarda dentro de un objeto serializable

public class BaseDeDatos {

    // --- Rutas y nombres de archivos para la persistencia ---
    // Puedes definir una carpeta para los datos o dejarlos en la raíz del proyecto
    private static final String DATA_DIR = "data/"; // Crea una carpeta 'data' en la raíz de tu proyecto

    private static final String CLIENTES_FILE = DATA_DIR + "clientes.ser";
    private static final String BARBEROS_FILE = DATA_DIR + "barberos.ser";
    private static final String SERVICIOS_FILE = DATA_DIR + "servicios.ser";
    private static final String RESERVAS_FILE = DATA_DIR + "reservas.ser";
    private static final String USUARIOS_FILE = DATA_DIR + "usuarios.ser";
    // Según el UML, los Horarios se manejan por Barbero, no centralmente en BaseDeDatos.
    // private static final String HORARIOS_FILE = DATA_DIR + "horarios.ser"; // No se usa si Horario está en Barbero

    // --- Colecciones (listas) para almacenar los objetos en memoria ---
    // Temporalmente usamos 'Object' hasta que las clases del modelo estén disponibles.
    // Una vez disponibles, cambia 'Object' por el tipo específico (ej. List<Cliente>)
    private List<Object> usuarios;
    private List<Object> barberos;
    private List<Object> clientes;
    private List<Object> servicios;
    private List<Object> reservas;
    // private List<Object> horarios; // No se usa si Horario está en Barbero

    // --- Contadores para generar IDs únicos (muy básico, para demo) ---
    //esto asegura que se le agregue un id unico y conse cutivo a cada objeto, al cliente1 se le asigna uno y al dos 2 y asi 
    private int nextClienteId = 1;
    private int nextBarberoId = 1;
    private int nextServicioId = 1;
    private int nextReservaId = 1;
    private int nextUsuarioId = 1;


    public BaseDeDatos() {
        // Inicializa las listas al crear una instancia de BaseDeDatos
        this.usuarios = new ArrayList<>();
        this.barberos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.reservas = new ArrayList<>();
        // this.horarios = new ArrayList<>(); // Si no se maneja centralmente

        // Asegura que la carpeta 'data' exista
        File dataFolder = new File(DATA_DIR);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs(); // Crea la carpeta y cualquier carpeta padre necesaria
        }

        // Intenta cargar los datos persistidos al iniciar la aplicación
        cargarDatos();
    }

    // --- Métodos de Serialización (Guardar datos en archivos .ser) ---

    public void guardarDatos() {
        try (ObjectOutputStream oosClientes = new ObjectOutputStream(new FileOutputStream(CLIENTES_FILE));
             ObjectOutputStream oosBarberos = new ObjectOutputStream(new FileOutputStream(BARBEROS_FILE));
             ObjectOutputStream oosServicios = new ObjectOutputStream(new FileOutputStream(SERVICIOS_FILE));
             ObjectOutputStream oosReservas = new ObjectOutputStream(new FileOutputStream(RESERVAS_FILE));
             ObjectOutputStream oosUsuarios = new ObjectOutputStream(new FileOutputStream(USUARIOS_FILE))) {

            oosClientes.writeObject(clientes);
            oosBarberos.writeObject(barberos);
            oosServicios.writeObject(servicios);
            oosReservas.writeObject(reservas);
            oosUsuarios.writeObject(usuarios);
            // Si tuvieras Horarios centralizados, también iría aquí:
            // oosHorarios.writeObject(horarios);

            System.out.println("DEBUG: Datos guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("ERROR: Error al guardar los datos: " + e.getMessage());
            // Para depuración más detallada: e.printStackTrace();
        }
    }

    // --- Métodos de Deserialización (Cargar datos desde archivos .ser) ---

    // La anotación suprime advertencias del compilador sobre el casting genérico
    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        System.out.println("DEBUG: Intentando cargar datos...");
        try (ObjectInputStream oisClientes = new ObjectInputStream(new FileInputStream(CLIENTES_FILE))) {
            clientes = (List<Object>) oisClientes.readObject();
            System.out.println("DEBUG: Clientes cargados: " + clientes.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de clientes no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar clientes: " + e.getMessage());
            // e.printStackTrace();
        }

        try (ObjectInputStream oisBarberos = new ObjectInputStream(new FileInputStream(BARBEROS_FILE))) {
            barberos = (List<Object>) oisBarberos.readObject();
            System.out.println("DEBUG: Barberos cargados: " + barberos.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de barberos no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar barberos: " + e.getMessage());
            // e.printStackTrace();
        }

        try (ObjectInputStream oisServicios = new ObjectInputStream(new FileInputStream(SERVICIOS_FILE))) {
            servicios = (List<Object>) oisServicios.readObject();
            System.out.println("DEBUG: Servicios cargados: " + servicios.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de servicios no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar servicios: " + e.getMessage());
            // e.printStackTrace();
        }

        try (ObjectInputStream oisReservas = new ObjectInputStream(new FileInputStream(RESERVAS_FILE))) {
            reservas = (List<Object>) oisReservas.readObject();
            System.out.println("DEBUG: Reservas cargadas: " + reservas.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de reservas no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar reservas: " + e.getMessage());
            // e.printStackTrace();
        }

        try (ObjectInputStream oisUsuarios = new ObjectInputStream(new FileInputStream(USUARIOS_FILE))) {
            usuarios = (List<Object>) oisUsuarios.readObject();
            System.out.println("DEBUG: Usuarios cargados: " + usuarios.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de usuarios no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar usuarios: " + e.getMessage());
            // e.printStackTrace();
        }

        // Si tuvieras Horarios centralizados, también iría aquí:
        /*
        try (ObjectInputStream oisHorarios = new ObjectInputStream(new FileInputStream(HORARIOS_FILE))) {
            horarios = (List<Object>) oisHorarios.readObject();
            System.out.println("DEBUG: Horarios cargados: " + horarios.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de horarios no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar horarios: " + e.getMessage());
            // e.printStackTrace();
        }
        */

        // Lógica para establecer los nextId a partir de los IDs más altos cargados
        // Esto es crucial para evitar IDs duplicados después de cargar datos
        if (!clientes.isEmpty()) {
            nextClienteId = clientes.stream()
                                    .mapToInt(obj -> {
                                        // TODO: Cuando Cliente esté definido, castear a Cliente y obtener su ID.
                                        // Cliente c = (Cliente) obj; return c.getId();
                                        return 0; // Valor por defecto si no se puede obtener el ID real
                                    })
                                    .max().orElse(0) + 1;
        }
        // Repetir para barberos, servicios, reservas, usuarios
        // ... (Este es un TODO importante para cuando las clases del modelo estén disponibles)
        System.out.println("DEBUG: nextClienteId establecido a: " + nextClienteId);
    }

    // --- Métodos CRUD (Crear, Leer, Actualizar, Eliminar) ---

    // Métodos para USUARIO
    public void agregarUsuario(Object usuario) { // Cambiar Object por Usuario cuando esté listo
        // TODO: Cuando Usuario esté listo, asignarle el nextUsuarioId
        // Usuario u = (Usuario) usuario; u.setId(nextUsuarioId++);
        usuarios.add(usuario);
        guardarDatos(); // Guarda los datos después de cada cambio
        System.out.println("DEBUG: Usuario agregado. Total: " + usuarios.size());
    }

    public List<Object> obtenerTodosLosUsuarios() { // Cambiar Object por Usuario cuando esté listo
        return usuarios;
    }

    public Object buscarUsuarioPorId(int id) { // Cambiar Object por Usuario cuando esté listo
        for (Object obj : usuarios) {
            // TODO: Cuando Usuario esté listo, castear y comparar su ID
            // Usuario u = (Usuario) obj; if (u.getId() == id) return u;
        }
        System.out.println("DEBUG: Usuario con ID " + id + " no encontrado.");
        return null;
    }

    public boolean eliminarUsuario(int id) {
        // TODO: Implementar lógica de eliminación usando el ID real
        // boolean removed = usuarios.removeIf(obj -> ((Usuario)obj).getId() == id);
        // if (removed) guardarDatos();
        System.out.println("DEBUG: Intento de eliminar Usuario con ID " + id);
        return false; // Por ahora, siempre retorna false
    }

    // Métodos para CLIENTE (Estructura similar a Usuario)
    public void agregarCliente(Object cliente) { // Cambiar Object por Cliente
        // TODO: Cuando Cliente esté listo, asignar ID: Cliente c = (Cliente) cliente; c.setId(nextClienteId++);
        clientes.add(cliente);
        guardarDatos();
        System.out.println("DEBUG: Cliente agregado. Total: " + clientes.size());
    }

    public List<Object> obtenerTodosLosClientes() { // Cambiar Object por Cliente
        return clientes;
    }

    public Object buscarClientePorId(int id) { // Cambiar Object por Cliente
        for (Object obj : clientes) {
            // TODO: Castear a Cliente y comparar su ID
        }
        return null;
    }

    public boolean eliminarCliente(int id) {
        // TODO: Lógica de eliminación
        return false;
    }

    // Métodos para BARBERO (Estructura similar)
    public void agregarBarbero(Object barbero) { // Cambiar Object por Barbero
        // TODO: Asignar ID
        barberos.add(barbero);
        guardarDatos();
        System.out.println("DEBUG: Barbero agregado. Total: " + barberos.size());
    }

    public List<Object> obtenerTodosLosBarberos() { // Cambiar Object por Barbero
        return barberos;
    }

    public Object buscarBarberoPorId(int id) { // Cambiar Object por Barbero
        for (Object obj : barberos) {
            // TODO: Castear a Barbero y comparar su ID
        }
        return null;
    }

    public boolean eliminarBarbero(int id) {
        // TODO: Lógica de eliminación
        return false;
    }

    // Métodos para SERVICIO (Estructura similar)
    public void agregarServicio(Object servicio) { // Cambiar Object por Servicio
        // TODO: Asignar ID
        servicios.add(servicio);
        guardarDatos();
        System.out.println("DEBUG: Servicio agregado. Total: " + servicios.size());
    }

    public List<Object> obtenerTodosLosServicios() { // Cambiar Object por Servicio
        return servicios;
    }

    public Object buscarServicioPorId(int id) { // Cambiar Object por Servicio
        for (Object obj : servicios) {
            // TODO: Castear a Servicio y comparar su ID
        }
        return null;
    }

    public boolean eliminarServicio(int id) {
        // TODO: Lógica de eliminación
        return false;
    }

    // Métodos para RESERVA (Estructura similar)
    public void agregarReserva(Object reserva) { // Cambiar Object por Reserva
        // TODO: Asignar ID
        reservas.add(reserva);
        guardarDatos();
        System.out.println("DEBUG: Reserva agregada. Total: " + reservas.size());
    }

    public List<Object> obtenerTodasLasReservas() { // Cambiar Object por Reserva
        return reservas;
    }

    public Object buscarReservaPorId(int id) { // Cambiar Object por Reserva
        for (Object obj : reservas) {
            // TODO: Castear a Reserva y comparar su ID
        }
        return null;
    }

    public boolean eliminarReserva(int id) {
        // TODO: Lógica de eliminación
        return false;
    }

    // Nota: Los Horarios se gestionan dentro de cada Barbero, no directamente aquí.
    // Si necesitas acceder a todos los horarios disponibles de todos los barberos,
    // tendrías que iterar sobre la lista de barberos y sus horarios.
    // public List<Horario> obtenerTodosLosHorariosDisponibles() {
    //     List<Horario> todos = new ArrayList<>();
    //     for (Object barberoObj : barberos) {
    //         // Barbero barbero = (Barbero) barberoObj; // Castear a Barbero
    //         // todos.addAll(barbero.getHorariosTrabajo()); // Asumiendo que Barbero tiene este método
    //     }
    //     return todos;
    // }
}