package app;

import datos.BaseDeDatos;
import modelo.*;

import java.time.LocalDateTime;
//import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BaseDeDatos bd = new BaseDeDatos();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Gestión de Barbería ---");
            System.out.println("1. Gestionar barberos");
            System.out.println("2. Gestionar clientes");
            System.out.println("3. Gestionar reservas");
            System.out.println("4. Gestionar servicios");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    gestionarBarberos(bd, scanner);
                    break;
                case 2:
                    gestionarClientes(bd, scanner);
                    break;
                case 3:
                    gestionarReservas(bd, scanner);
                    break;
                case 4:
                    gestionarServicios(bd, scanner);
                    break;
                case 5:
                    continuar = false;
                    bd.guardarDatos();
                    System.out.println("Saliendo y guardando datos...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
        scanner.close();
    }

    // Métodos para gestionar cada sección...

    private static void gestionarBarberos(BaseDeDatos bd, Scanner scanner) {
        System.out.println("\n--- Gestión de Barberos ---");
        System.out.println("1. Agregar barbero");
        System.out.println("2. Eliminar barbero");
        System.out.println("3. Listar barberos");
        System.out.println("4. Agregar horario a barbero");
        System.out.println("5. Agregar servicio a barbero");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nombre del barbero: ");
                String nombre = scanner.nextLine();
                System.out.print("Teléfono del barbero: ");
                String telefono = scanner.nextLine();
                System.out.print("Contraseña del barbero: ");
                String contrasena = scanner.nextLine();
                Barbero nuevoBarbero = new Barbero(bd.getNextBarberoId(), nombre, telefono, contrasena);
                bd.agregarBarbero(nuevoBarbero);
                System.out.println("Barbero agregado con ID único.");
                break;
            case 2:
                System.out.print("ID del barbero a eliminar: ");
                int idEliminar = scanner.nextInt();
                if (bd.eliminarBarbero(idEliminar)) {
                    System.out.println("Barbero eliminado.");
                } else {
                    System.out.println("No se encontró el barbero.");
                }
                break;
            case 3:
                List<Barbero> barberos = bd.obtenerTodosLosBarberos();
                barberos.forEach(System.out::println);
                break;
            case 4:
                System.out.print("ID del barbero al que quieres agregar horario: ");
                int idBarberoHorario = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Fecha (YYYY-MM-DD): ");
                String fechaStr = scanner.nextLine();
                System.out.print("Hora inicio (HH:mm AM/PM): ");
                String horaInicio = scanner.nextLine();
                System.out.print("Hora fin (HH:mm AM/PM): ");
                String horaFin = scanner.nextLine();
                Horario nuevoHorario = new Horario(java.sql.Date.valueOf(fechaStr), horaInicio, horaFin);
                Barbero barbero = bd.buscarBarberoPorId(idBarberoHorario);
                if (barbero != null) {
                    barbero.agregarHorario(nuevoHorario);
                    System.out.println("Horario agregado.");
                } else {
                    System.out.println("Barbero no encontrado.");
                }
                break;
            case 5:
                System.out.print("ID del barbero al que quieres agregar servicio: ");
                int idBarberoServicio = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nombre del servicio: ");
                String nombreServicio = scanner.nextLine();
                System.out.print("Descripción del servicio: ");
                String descServicio = scanner.nextLine();
                System.out.print("Precio del servicio: ");
                double precioServicio = scanner.nextDouble();
                System.out.print("Duración en minutos: ");
                int duracionServicio = scanner.nextInt();
                Servicio nuevoServicio = new Servicio(bd.getNextServicioId(), nombreServicio, descServicio, precioServicio, duracionServicio);
                Barbero barberoServicio = bd.buscarBarberoPorId(idBarberoServicio);
                if (barberoServicio != null) {
                    barberoServicio.agregarServicio(nuevoServicio); // Agrega el servicio al barbero
                    bd.agregarServicio(nuevoServicio); // ✅ También almacénalo en la base de datos
                    bd.guardarDatos(); // 🔥 Persistencia asegurada
                    System.out.println("Servicio agregado correctamente y guardado.");
                } else {
                    System.out.println("Barbero no encontrado.");
                }

                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void gestionarClientes(BaseDeDatos bd, Scanner scanner) {
        System.out.println("\n--- Gestión de Clientes ---");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Eliminar cliente");
        System.out.println("3. Listar clientes");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nombre del cliente: ");
                String nombre = scanner.nextLine();
                System.out.print("Teléfono del cliente: ");
                String telefono = scanner.nextLine();
                System.out.print("Email del cliente: ");
                String email = scanner.nextLine();
                Cliente nuevoCliente = new Cliente(bd.getNextClienteId(), nombre, telefono, email);
                bd.agregarCliente(nuevoCliente);
                System.out.println("Cliente agregado con éxito.");
                break;
            case 2:
                System.out.print("ID del cliente a eliminar: ");
                int idEliminar = scanner.nextInt();
                if (bd.eliminarCliente(idEliminar)) {
                    System.out.println("Cliente eliminado.");
                } else {
                    System.out.println("No se encontró el cliente.");
                }
                break;
            case 3:
                List<Cliente> clientes = bd.obtenerTodosLosClientes();
                clientes.forEach(System.out::println);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void gestionarReservas(BaseDeDatos bd, Scanner scanner) {
        System.out.println("\n--- Gestión de Reservas ---");
        System.out.println("1. Hacer reserva");
        System.out.println("2. Listar reservas");
        System.out.println("3. Eliminar reserva");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("ID del cliente que hace la reserva: ");
                int idCliente = scanner.nextInt();
                Cliente cliente = bd.buscarClientePorId(idCliente);
                if (cliente == null) {
                    System.out.println("Cliente no encontrado.");
                    return;
                }

                System.out.print("ID del barbero: ");
                int idBarbero = scanner.nextInt();
                Barbero barbero = bd.buscarBarberoPorId(idBarbero);
                if (barbero == null) {
                    System.out.println("Barbero no encontrado.");
                    return;
                }

                System.out.print("Fecha y hora de la reserva (YYYY-MM-DD HH:mm): ");
                scanner.nextLine();
                String fechaHoraStr = scanner.nextLine();
                LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr.replace(" ", "T"));

                Reserva nuevaReserva = new Reserva(bd.getNextReservaId(), fechaHora, cliente);
                nuevaReserva.asignarBarbero(barbero);

                System.out.print("ID del servicio: ");
                int idServicio = scanner.nextInt();
                Servicio servicio = bd.buscarServicioPorId(idServicio);
                if (servicio != null) {
                    nuevaReserva.agregarServicio(servicio);
                } else {
                    System.out.println("Servicio no encontrado.");
                    return;
                }

                barbero.getHorarioTrabajo().forEach(horario -> {
                    if (horario.getFecha().equals(java.sql.Date.valueOf(fechaHora.toLocalDate()))) {
                        horario.marcarComoOcupado();
                    }
                });

                bd.agregarReserva(nuevaReserva);
                cliente.agregarReserva(nuevaReserva); // 🔥 Asegura que el historial del cliente se actualiza
                bd.guardarDatos(); // 💾 Guarda los datos para que persistan
                System.out.println("Reserva agregada al historial del cliente.");
                System.out.println("Reserva creada con éxito.");
                break;
            case 2:
                List<Reserva> reservas = bd.obtenerTodasLasReservas();
                reservas.forEach(System.out::println);
                break;
            case 3:
                System.out.print("ID de la reserva a eliminar: ");
                int idReserva = scanner.nextInt();
                if (bd.eliminarReserva(idReserva)) {
                    System.out.println("Reserva eliminada.");
                } else {
                    System.out.println("No se encontró la reserva.");
                }
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void gestionarServicios(BaseDeDatos bd, Scanner scanner) {
    System.out.println("\n--- Gestión de Servicios ---");
    System.out.println("1. Agregar servicio a barbero");
    System.out.println("2. Listar servicios");
    System.out.print("Selecciona una opción: ");
    int opcion = scanner.nextInt();
    scanner.nextLine();

    switch (opcion) {
        case 1:
            System.out.print("ID del barbero al que quieres agregar servicio: ");
            int idBarberoServicio = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nombre del servicio: ");
            String nombreServicio = scanner.nextLine();
            System.out.print("Descripción del servicio: ");
            String descServicio = scanner.nextLine();
            System.out.print("Precio del servicio: ");
            double precioServicio = scanner.nextDouble();
            System.out.print("Duración en minutos: ");
            int duracionServicio = scanner.nextInt();

            Servicio nuevoServicio = new Servicio(bd.getNextServicioId(), nombreServicio, descServicio, precioServicio, duracionServicio);
            Barbero barbero = bd.buscarBarberoPorId(idBarberoServicio);
            if (barbero != null) {
                barbero.agregarServicio(nuevoServicio);
                bd.agregarServicio(nuevoServicio); // 💡 También guardarlo en la base de datos general
                bd.guardarDatos(); // 🔥 Asegurar la persistencia
                System.out.println("Servicio agregado correctamente y guardado.");
            } else {
                System.out.println("Barbero no encontrado.");
            }

            break;
        case 2:
            List<Servicio> servicios = bd.obtenerTodosLosServicios();
            servicios.forEach(System.out::println);
            break;
        default:
            System.out.println("Opción no válida.");
    }
}



    // Métodos para gestionar clientes, reservas y servicios siguen el mismo patrón...

}

