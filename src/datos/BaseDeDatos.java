package datos; 

import java.io.*;
import java.util.ArrayList; 
import java.util.List;

import modelo.Usuario;
import modelo.Barbero;
import modelo.Cliente;
import modelo.Reserva;
import modelo.Servicio;

public class BaseDeDatos implements Serializable {

    // Rutas y nombres de archivos para la persistencia 
    private static final String DATA_DIR = "data/"; 
    private static final String CLIENTES_FILE = DATA_DIR + "clientes.ser";
    private static final String BARBEROS_FILE = DATA_DIR + "barberos.ser";
    private static final String SERVICIOS_FILE = DATA_DIR + "servicios.ser";
    private static final String RESERVAS_FILE = DATA_DIR + "reservas.ser";
    private static final String USUARIOS_FILE = DATA_DIR + "usuarios.ser";

    //Colecciones (listas) para almacenar los objetos en memoria 
    private List<Usuario> usuarios;
    private List<Barbero> barberos;
    private List<Cliente> clientes;
    private List<Servicio> servicios;
    private List<Reserva> reservas;

    // Contadores para generar IDs únicos 
    private int nextClienteId = 1;
    private int nextBarberoId = 1;
    private int nextServicioId = 3;
    private int nextReservaId = 1;
    private int nextUsuarioId = 1;

    public int getNextReservaId() {
    return nextReservaId;
    }

    public void incrementarReservaId() {
        nextReservaId++;
    }

    public int getNextClienteId(){
        return nextClienteId;
    }

    public void incrementarClienteId(){
        nextClienteId++;
    }

      public int getNextServicioId(){
        return nextServicioId;
    }

    public void incrementarServicioId(){
        nextServicioId++;
    }



    public BaseDeDatos() {
        
        this.usuarios = new ArrayList<>();
        this.barberos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.reservas = new ArrayList<>();

        File dataFolder = new File(DATA_DIR);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs(); 
        }

        cargarDatos();
    }

    //Métodos de Serialización

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

            System.out.println("DEBUG: Datos guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("ERROR: Error al guardar los datos: " + e.getMessage());
            // Para depuración más detallada: e.printStackTrace();
        }
    }

    //Métodos de Deserialización

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        //System.out.println("DEBUG: Intentando cargar datos...");
        try (ObjectInputStream oisClientes = new ObjectInputStream(new FileInputStream(CLIENTES_FILE))) {
            clientes = (List<Cliente>) oisClientes.readObject();
            //System.out.println("DEBUG: Clientes cargados: " + clientes.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de clientes no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar clientes: " + e.getMessage());
            // e.printStackTrace();
        }

        try (ObjectInputStream oisBarberos = new ObjectInputStream(new FileInputStream(BARBEROS_FILE))) {
            barberos = (List<Barbero>) oisBarberos.readObject();
            //System.out.println("DEBUG: Barberos cargados: " + barberos.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de barberos no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar barberos: " + e.getMessage());
            // e.printStackTrace();
        }

        try (ObjectInputStream oisServicios = new ObjectInputStream(new FileInputStream(SERVICIOS_FILE))) {
            servicios = (List<Servicio>) oisServicios.readObject();
            //System.out.println("DEBUG: Servicios cargados: " + servicios.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de servicios no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar servicios: " + e.getMessage());
            // e.printStackTrace();
        }

        try (ObjectInputStream oisReservas = new ObjectInputStream(new FileInputStream(RESERVAS_FILE))) {
            reservas = (List<Reserva>) oisReservas.readObject();
            //System.out.println("DEBUG: Reservas cargadas: " + reservas.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de reservas no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar reservas: " + e.getMessage());
            // e.printStackTrace();
        }

        try (ObjectInputStream oisUsuarios = new ObjectInputStream(new FileInputStream(USUARIOS_FILE))) {
            usuarios = (List<Usuario>) oisUsuarios.readObject();
            //System.out.println("DEBUG: Usuarios cargados: " + usuarios.size());
        } catch (FileNotFoundException e) {
            System.out.println("INFO: Archivo de usuarios no encontrado. Iniciando con lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Error al cargar usuarios: " + e.getMessage());
            // e.printStackTrace();
        }

         if (!clientes.isEmpty()) {
                nextClienteId = clientes.stream()
                                        .mapToInt(Cliente::getId)
                                        .max()
                                        .orElse(0) + 1;
        }
        //System.out.println("DEBUG: nextClienteId establecido a: " + nextClienteId);

        if (!barberos.isEmpty()) {
            nextBarberoId = barberos.stream()
                                    .mapToInt(obj -> {
                                        Barbero barberoActual = (Barbero) obj;
                                        return barberoActual.getId();
                                    })
                                    .max().orElse(0) + 1;
        }
        //System.out.println("DEBUG: nextBarberoId establecido a: " + nextBarberoId);


        if (!servicios.isEmpty()) {
            nextServicioId = servicios.stream()
                                    .mapToInt(obj -> {
                                        Servicio servicioActual = (Servicio) obj;
                                        return servicioActual.getId();
                                    })
                                    .max().orElse(0) + 1;
        }
        //System.out.println("DEBUG: nextServicioId establecido a: " + nextServicioId);

        if (!reservas.isEmpty()) {
            nextReservaId = reservas.stream()
                                    .mapToInt(obj -> {
                                        Reserva reservaActual = (Reserva) obj;
                                        return reservaActual.getIdReserva();
                                    })
                                    .max().orElse(0) + 1;
        }
        //System.out.println("DEBUG: nextReservaId establecido a: " + nextReservaId);

        if (!usuarios.isEmpty()) {
            nextUsuarioId = usuarios.stream()
                                    .mapToInt(obj -> {
                                        Usuario usuarioActual = (Usuario) obj;
                                        return usuarioActual.getId();
                                    })
                                    .max().orElse(0) + 1;
        }
        //System.out.println("DEBUG: nextUsuarioId establecido a: " + nextUsuarioId);

    }

    //Métodos CRUD

    // Métodos para USUARIO
    public void agregarUsuario(Usuario usuario) { 
        usuario.setId(nextUsuarioId++);
        usuarios.add(usuario);
        guardarDatos();
        System.out.println("DEBUG: Usuario agregado. Total: " + usuarios.size());
    }

    public List<Usuario> obtenerTodosLosUsuarios() { 
        return usuarios;
    }

    public Usuario buscarUsuarioPorId(int id) { 
        for (Usuario usuarioActual : usuarios) {
            if (usuarioActual.getId() == id){
                return usuarioActual;
            } 
        }
        System.out.println("DEBUG: Usuario con ID " + id + " no encontrado.");
        return null;
    }

    public boolean eliminarUsuario(int id) {
        boolean removed = usuarios.removeIf(usuarioActual -> usuarioActual.getId() == id);
        if (removed) {
            guardarDatos(); 
            System.out.println("DEBUG: Usuario con ID " + id + " eliminado.");
        } else {
            System.out.println("DEBUG: Usuario con ID " + id + " no encontrado para eliminar.");
        }
        return removed;
    }

    // Métodos para CLIENTE 
    public void agregarCliente(Cliente nuevoCliente) {
        nuevoCliente.setId(nextClienteId);  // ✅ Asigna un ID único al cliente
        clientes.add(nuevoCliente);
        nextClienteId++;  // ✅ Incrementa el contador
        guardarDatos();   // ✅ Guarda los datos actualizados
    }


    public List<Cliente> obtenerTodosLosClientes() { 
        return clientes;
    }

    public Cliente buscarClientePorId(int id) {
        for (Cliente clienteActual : clientes) { 
            if (clienteActual.getId() == id) {
                System.out.println("DEBUG: Cliente con ID " + id + " encontrado.");
                return clienteActual;
            }
        }
        System.out.println("DEBUG: Cliente con ID " + id + " no encontrado.");
        return null;
    }  

    public boolean eliminarCliente(int id) {
        boolean removed = clientes.removeIf(clienteActual -> clienteActual.getId() == id);
        if (removed) {
            guardarDatos(); 
            System.out.println("DEBUG: Cliente con ID " + id + " eliminado.");
        } else {
            System.out.println("DEBUG: Cliente con ID " + id + " no encontrado para eliminar.");
        }
        return removed; 
    }

    public void actualizarCliente(Cliente clienteActualizado) {
    for (int i = 0; i < clientes.size(); i++) {
        if (clientes.get(i).getId() == clienteActualizado.getId()) {
            clientes.set(i, clienteActualizado); // ✅ Reemplaza el cliente con sus nuevas reservas
            guardarDatos(); // ✅ Asegura que los datos se guarden en los archivos
            return;
        }
    }
    System.out.println("❌ Cliente no encontrado en la base de datos.");
}


    // Métodos para BARBERO 
    public void agregarBarbero(Barbero barbero) { 
        barbero.setId(nextBarberoId++);
        barberos.add(barbero);
        guardarDatos();
        System.out.println("DEBUG: Barbero agregado. Total: " + barberos.size());
    }

    public List<Barbero> obtenerTodosLosBarberos() { 
        return barberos;
    }

    public Barbero buscarBarberoPorId(int id) {
        for (Barbero barberoActual : barberos) { 
            if (barberoActual.getId() == id) {
                System.out.println("DEBUG: Barbero con ID " + id + " encontrado.");
                return barberoActual;
            }
        }
        System.out.println("DEBUG: Barbero con ID " + id + " no encontrado.");
        return null;
    }

   public boolean eliminarBarbero(int id) {
        boolean removed = barberos.removeIf(barberoActual -> barberoActual.getId() == id);
        if (removed) {
            guardarDatos();
            System.out.println("DEBUG: Barbero con ID " + id + " eliminado.");
        } else {
            System.out.println("DEBUG: Barbero con ID " + id + " no encontrado para eliminar.");
        }
        return removed;
    }

    // Métodos para SERVICIO
    public void agregarServicio(Servicio servicio) { 
        servicio.setId(getNextServicioId());  // ✅ Asigna un ID único
        servicios.add(servicio);
        incrementarServicioId();  // ✅ Asegura que el contador avance correctamente
        guardarDatos();
    }


    public List<Servicio> obtenerTodosLosServicios() { 
        return servicios; 
    }

    public Servicio buscarServicioPorId(int id) { 
        for (Servicio servicioActual : servicios) { 
            if (servicioActual.getId() == id) {
                System.out.println("DEBUG: Servicio con ID " + id + " encontrado.");
                return servicioActual;
            }
        }
        System.out.println("DEBUG: Servicio con ID " + id + " no encontrado.");
        return null;
    }

    public boolean eliminarServicio(int id) {
        boolean removed = servicios.removeIf(servicioActual -> servicioActual.getId() == id);

        if (removed) {
            guardarDatos(); 
            System.out.println("DEBUG: Servicio con ID " + id + " eliminado.");
        } else {
            System.out.println("DEBUG: Servicio con ID " + id + " no encontrado para eliminar.");
        }
        return removed; 
    }

        // Métodos para RESERVA
    public void agregarReserva(Reserva reserva) { 
        reserva.setIdReserva(nextReservaId++); 
        reservas.add(reserva);
        guardarDatos();
        System.out.println("DEBUG: Reserva agregada. Total: " + reservas.size());
    }

    public List<Reserva> obtenerTodasLasReservas() { 
        return reservas; 
    }

    public Reserva buscarReservaPorId(int id) { 
        for (Reserva reservaActual : reservas) { 
            if (reservaActual.getIdReserva() == id) {
                System.out.println("DEBUG: Reserva con ID " + id + " encontrada.");
                return reservaActual;
            }
        }
        System.out.println("DEBUG: Reserva con ID " + id + " no encontrada.");
        return null;
    }

    public boolean eliminarReserva(int id) {
        boolean removed = reservas.removeIf(reservaActual -> reservaActual.getIdReserva() == id);

        if (removed) {
            guardarDatos(); 
            System.out.println("DEBUG: Reserva con ID " + id + " eliminada.");
        } else {
            System.out.println("DEBUG: Reserva con ID " + id + " no encontrada para eliminar.");
        }
        return removed;
    }

}