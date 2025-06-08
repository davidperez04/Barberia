package app;
import datos.BaseDeDatos;
import modelo.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    try {
        // Inicializar la base de datos
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenido al sistema de gestión de la barbería");

        boolean salir = false;
        while (!salir) {
            System.out.println("\n Menú Principal:");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Barberos");
            System.out.println("3. Gestionar Servicios");
            System.out.println("4. Gestionar Reservas");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                switch (opcion) {
                    case 1:
                        gestionarClientes(baseDeDatos, scanner);
                        break;
                    case 2:
                        gestionarBarberos(baseDeDatos, scanner);
                        break;
                    case 3:
                        gestionarServicios(baseDeDatos, scanner);
                        break;
                    case 4:
                        gestionarReservas(baseDeDatos, scanner);
                        break;
                    case 5:
                        salir = true;
                        baseDeDatos.guardarDatos(); // Guardar cambios antes de salir
                        System.out.println("¡Gracias por usar el sistema!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número.");
                scanner.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    } catch (Exception e) {
        System.out.println("Error grave al iniciar la aplicación: " + e.getMessage());
    }
}


    private static void gestionarClientes(BaseDeDatos baseDeDatos, Scanner scanner) {
    System.out.println("\nGestión de Clientes:");
    System.out.println("1. Agregar Cliente");
    System.out.println("2. Listar Clientes");
    System.out.println("3. Buscar Cliente");
    System.out.println("4. Eliminar Cliente");
    System.out.println("5. Volver al menú principal");
    System.out.print("Selecciona una opción: ");

    try {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        switch (opcion) {
            case 1:
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Teléfono: ");
                String telefono = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();

                if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,6}$")) {
                    throw new IllegalArgumentException("Email ingresado no es válido.");
                }

                Cliente nuevoCliente = new Cliente(0, nombre, telefono, email);
                baseDeDatos.incrementarClienteId();
                baseDeDatos.agregarCliente(nuevoCliente);
                System.out.println("Cliente agregado correctamente.");
                break;
            case 2:

                List<Cliente> clientes = baseDeDatos.obtenerTodosLosClientes();
                for (Cliente cliente : clientes) {
                    System.out.println(cliente);
                    
                    // 📌 Muestra cada reserva del historial del cliente
                    if (!cliente.getHistorialReservas().isEmpty()) {
                        System.out.println("Historial de Reservas:");
                        cliente.getHistorialReservas().forEach(System.out::println);
                    } else {
                        System.out.println("Este cliente no tiene reservas.");
                    }
                }
                break;

            case 3:
                System.out.print("ID del Cliente: ");
                int idBuscar = scanner.nextInt();
                Cliente clienteEncontrado = baseDeDatos.buscarClientePorId(idBuscar);
                System.out.println(clienteEncontrado != null ? clienteEncontrado : "Cliente no encontrado.");
                break;
            case 4:
                System.out.print("ID del Cliente a eliminar: ");
                int idEliminar = scanner.nextInt();
                if (baseDeDatos.eliminarCliente(idEliminar)) {
                    System.out.println("Cliente eliminado.");
                } else {
                    System.out.println("No se encontró el cliente.");
                }
                break;
            case 5:
                return;
            default:
                System.out.println(" Opción no válida.");
        }
    } catch (InputMismatchException e) {
        System.out.println("Error: Debes ingresar un número.");
        scanner.nextLine();
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
    }
}

    private static final String CONTRASENA_MAESTRA = "barberia123"; 

private static void gestionarBarberos(BaseDeDatos baseDeDatos, Scanner scanner) {
    System.out.println("\nGestión de Barberos:");
    System.out.println("1. Agregar Barbero");
    System.out.println("2. Listar Barberos");
    System.out.println("3. Buscar Barbero");
    System.out.println("4. Eliminar Barbero");
    System.out.println("5. Volver al menú principal");
    System.out.print("Selecciona una opción: ");

    try {
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese la contraseña de la barbería: ");
                String contrasenaIngresada = scanner.nextLine();

                if (!contrasenaIngresada.equals(CONTRASENA_MAESTRA)) {
                    System.out.println("Acceso denegado. La contraseña es incorrecta.");
                    return;
                }

                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Teléfono: ");
                String telefono = scanner.nextLine();
                System.out.print("Contraseña personal: ");
                String contrasenaPersonal = scanner.nextLine();

                Barbero nuevoBarbero = new Barbero(0, nombre, telefono, contrasenaPersonal);
                baseDeDatos.agregarBarbero(nuevoBarbero);
                System.out.println("Barbero agregado correctamente.");
                break;

            case 2:
                List<Barbero> barberos = baseDeDatos.obtenerTodosLosBarberos();
                barberos.forEach(System.out::println);
                break;

            case 3:
                System.out.print("ID del Barbero: ");
                int idBuscar = scanner.nextInt();
                Barbero barberoEncontrado = baseDeDatos.buscarBarberoPorId(idBuscar);
                System.out.println(barberoEncontrado != null ? barberoEncontrado : "Barbero no encontrado.");
                break;

            case 4:
                System.out.print("ID del Barbero a eliminar: ");
                int idEliminar = scanner.nextInt();
                if (baseDeDatos.eliminarBarbero(idEliminar)) {
                    System.out.println("Barbero eliminado.");
                } else {
                    System.out.println("No se encontró el barbero.");
                }
                break;

            case 5:
                return;

            default:
                System.out.println("Opción no válida.");
        }
    } catch (InputMismatchException e) {
        System.out.println("Error: Debes ingresar un número.");
        scanner.nextLine();
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
    }
}


private static void gestionarServicios(BaseDeDatos baseDedatos, Scanner scanner) {
    System.out.println("\nGestión de Servicios:");
    System.out.println("1. Agregar Servicio");
    System.out.println("2. Listar Servicios");
    System.out.println("3. Buscar Servicio");
    System.out.println("4. Eliminar Servicio");
    System.out.println("5. Volver al menú principal");
    System.out.print("Selecciona una opción: ");

    try {
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nombre del servicio: ");
                String nombre = scanner.nextLine();
                System.out.print("Descripción: ");
                String descripcion = scanner.nextLine();
                System.out.print("Precio: ");
                double precio = scanner.nextDouble();
                System.out.print("Duración en minutos: ");
                int duracion = scanner.nextInt();
                scanner.nextLine();

                if (precio < 0) {
                    throw new IllegalArgumentException("El precio no puede ser negativo.");
                }
                if (duracion <= 0) {
                    throw new IllegalArgumentException("La duración debe ser mayor a cero.");
                }

                Servicio nuevoServicio = new Servicio(0, nombre, descripcion, precio, duracion);
                baseDedatos.incrementarServicioId();
                baseDedatos.agregarServicio(nuevoServicio);
                System.out.println("Servicio agregado correctamente.");
                break;
            case 2:
                List<Servicio> servicios = baseDedatos.obtenerTodosLosServicios();
                servicios.forEach(System.out::println);
                break;
            case 3:
                System.out.print("ID del Servicio: ");
                int idBuscar = scanner.nextInt();
                Servicio servicioEncontrado = baseDedatos.buscarServicioPorId(idBuscar);
                System.out.println(servicioEncontrado != null ? servicioEncontrado : "❌ Servicio no encontrado.");
                break;
            case 4:
                System.out.print("ID del Servicio a eliminar: ");
                int idEliminar = scanner.nextInt();
                if (baseDedatos.eliminarServicio(idEliminar)) {
                    System.out.println("Servicio eliminado.");
                } else {
                    System.out.println("No se encontró el servicio.");
                }
                break;
            case 5:
                return;
            default:
                System.out.println("Opción no válida.");
        }
    } catch (InputMismatchException e) {
        System.out.println("Error: Debes ingresar un número.");
        scanner.nextLine();
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
    }
}

private static void gestionarReservas(BaseDeDatos baseDeDatos, Scanner scanner) {
    System.out.println("\nGestión de Reservas:");
    System.out.println("1. Agregar Reserva");
    System.out.println("2. Listar Reservas");
    System.out.println("3. Buscar Reserva");
    System.out.println("4. Eliminar Reserva");
    System.out.println("5. Volver al menú principal");
    System.out.print("Selecciona una opción: ");

    try {
        int opcion = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcion) {
            case 1:
                System.out.print("ID del Cliente: ");
                int idCliente = scanner.nextInt();
                Cliente cliente = baseDeDatos.buscarClientePorId(idCliente);
                if (cliente == null) {
                    System.out.println("Cliente no encontrado.");
                    return;
                }

                scanner.nextLine(); // Limpiar buffer
                System.out.print("Fecha y hora (YYYY-MM-DD HH:mm): ");
                String fechaHoraStr = scanner.nextLine().trim(); // Elimina espacios innecesarios

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
 
                    int nuevoIdReserva = baseDeDatos.getNextReservaId(); // Obtener el próximo ID válido
                    System.out.println("DEBUG: ID de nueva reserva -> " + nuevoIdReserva); // Para verificar el valor
                    Reserva nuevaReserva = new Reserva(baseDeDatos.getNextReservaId(), fechaHora, cliente);
                    cliente.agregarReserva(nuevaReserva);  // ✅ Agrega la reserva al historial del cliente
                    baseDeDatos.agregarReserva(nuevaReserva);  // ✅ Guarda la reserva en la base de datos
                    baseDeDatos.actualizarCliente(cliente); // ✅ Asegura que el historial de reservas se guarde
                    System.out.println("✅ Reserva creada correctamente.");

                } catch (DateTimeParseException e) {
                    System.out.println("Error en el formato de fecha. Usa estrictamente: YYYY-MM-DD HH:mm (Ejemplo: 2025-06-07 02:30).");
                }
                break;

            case 2:
                List<Reserva> reservas = baseDeDatos.obtenerTodasLasReservas();
                reservas.forEach(System.out::println);
                break;

            case 3:
                System.out.print("ID de la Reserva: ");
                int idBuscar = scanner.nextInt();
                Reserva reservaEncontrada = baseDeDatos.buscarReservaPorId(idBuscar);
                System.out.println(reservaEncontrada != null ? reservaEncontrada : "Reserva no encontrada.");
                break;

            case 4:
                System.out.print("ID de la Reserva a eliminar: ");
                int idEliminar = scanner.nextInt();
                if (baseDeDatos.eliminarReserva(idEliminar)) {
                    System.out.println("Reserva eliminada.");
                } else {
                    System.out.println("No se encontró la reserva.");
                }
                break;

            case 5:
                return;

            default:
                System.out.println("Opción no válida.");
        }

    } catch (InputMismatchException e) {
        System.out.println("Error: Debes ingresar un número.");
        scanner.nextLine(); // Limpiar el buffer
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
    }
}

}
