package app;

import datos.BaseDeDatos;
import modelo.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
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
        int opcion = -1; // Inicializa con un valor inválido

        do {
            try {
                System.out.println("\n--- Gestión de Barberos ---");
                System.out.println("1. Agregar barbero");
                System.out.println("2. Eliminar barbero");
                System.out.println("3. Listar barberos");
                System.out.println("4. Agregar horario a barbero");
                System.out.println("5. Asignar especialidad a barbero");
                System.out.println("6. Salir");
                System.out.print("Selecciona una opción: ");

                opcion = Integer.parseInt(scanner.nextLine()); // ✅ Validación de entrada con `parseInt`

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del barbero: ");
                        String nombre = scanner.nextLine();
                        String telefono;
                        do {
                            System.out.print("Teléfono del barbero (10 dígitos): ");
                            telefono = scanner.nextLine();

                            if (telefono.length() != 10 || !telefono.matches("\\d+")) {
                                System.out.println("❌ ERROR: El número de teléfono debe tener exactamente 10 dígitos numéricos.");
                            }

                        } while (telefono.length() != 10 || !telefono.matches("\\d+")); // 🔄 Repite hasta que sea válido

                        Barbero nuevoBarbero = new Barbero(bd.getNextBarberoId(), nombre, telefono);
                        bd.agregarBarbero(nuevoBarbero);
                        System.out.println("✅ Barbero agregado con ID único.");
                        break;
                    case 2:
                        System.out.print("ID del barbero a eliminar: ");
                        int idEliminar = Integer.parseInt(scanner.nextLine());
                        if (bd.eliminarBarbero(idEliminar)) {
                            System.out.println("✅ Barbero eliminado.");
                        } else {
                            System.out.println("❌ No se encontró el barbero.");
                        }
                        break;
                    case 3:
                        List<Barbero> barberos = bd.obtenerTodosLosBarberos();
                        barberos.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.print("ID del barbero al que quieres agregar horario: ");
                        int idBarberoHorario = Integer.parseInt(scanner.nextLine());
                        System.out.print("Fecha (YYYY-MM-DD): ");
                        String fechaStr = scanner.nextLine();
                        System.out.print("Hora inicio (HH:mm): ");
                        String horaInicio = scanner.nextLine();
                        System.out.print("Hora fin (HH:mm): ");
                        String horaFin = scanner.nextLine();
                        Horario nuevoHorario = new Horario(java.sql.Date.valueOf(fechaStr), horaInicio, horaFin);
                        Barbero barbero = bd.buscarBarberoPorId(idBarberoHorario);
                        if (barbero != null) {
                            barbero.agregarHorario(nuevoHorario);
                            System.out.println("✅ Horario agregado.");
                        } else {
                            System.out.println("❌ Barbero no encontrado.");
                        }
                        break;

                    
                    case 5:

                        System.out.print("ID del barbero al que quieres asignar especialidades: ");
                        int idBarberoEspecialidad = Integer.parseInt(scanner.nextLine());
                        Barbero barberoEspecialidad = bd.buscarBarberoPorId(idBarberoEspecialidad);
                        
                        if (barberoEspecialidad != null) {
                            System.out.print("Ingrese las especialidades separadas por comas (Ej: Corte, Afeitado, Diseño): ");
                            String inputEspecialidades = scanner.nextLine();
                            List<String> especialidades = Arrays.asList(inputEspecialidades.split(", ")); // 🔥 Convierte la entrada en lista
                            barberoEspecialidad.setEspecialidades(especialidades);
                            bd.guardarDatos(); // 🔥 Guarda el cambio en la base de datos
                            System.out.println("✅ Especialidades asignadas correctamente.");
                        } else {
                            System.out.println("❌ Barbero no encontrado.");
                        }
                        break;

                    case 6:
                        System.out.println("📌 Saliendo de Gestión de Barberos...");
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Ingresa un número del menú.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Ingresa un número válido del menú.");
            }

        } while (opcion != 6); // 🔄 Solo sale del bucle cuando el usuario elige "Salir"
    }

    private static void gestionarClientes(BaseDeDatos bd, Scanner scanner) {
        int opcion = -1; // Inicializa con un valor inválido

        do {
            try {
                System.out.println("\n--- Gestión de Clientes ---");
                System.out.println("1. Agregar cliente");
                System.out.println("2. Eliminar cliente");
                System.out.println("3. Listar clientes");
                System.out.println("4. Salir");
                System.out.print("Selecciona una opción: ");

                opcion = Integer.parseInt(scanner.nextLine()); // ✅ Validación de entrada con `parseInt`

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del cliente: ");
                        String nombre = scanner.nextLine();
                        String telefono;
                        do {
                            System.out.print("Teléfono del cliente (10 dígitos): ");
                            telefono = scanner.nextLine();

                            if (telefono.length() != 10 || !telefono.matches("\\d+")) {
                                System.out.println("❌ ERROR: El número de teléfono debe tener exactamente 10 dígitos numéricos.");
                            }

                        } while (telefono.length() != 10 || !telefono.matches("\\d+"));
                        System.out.print("Email del cliente: ");
                        String email = scanner.nextLine();
                        Cliente nuevoCliente = new Cliente(bd.getNextClienteId(), nombre, telefono, email);
                        bd.agregarCliente(nuevoCliente);
                        System.out.println("✅ Cliente agregado con éxito.");
                        break;
                    case 2:
                        System.out.println("📌 Lista de clientes disponibles:");
                        for (Cliente cliente : bd.obtenerTodosLosClientes()) {
                            System.out.println("ID: " + cliente.getId() + " | Nombre: " + cliente.getNombre());
                        }

                        int idEliminar = -1;
                        boolean entradaValida = false;

                        do {
                            try {
                                System.out.print("ID del cliente a eliminar: ");
                                idEliminar = Integer.parseInt(scanner.nextLine());
                                entradaValida = true; // ✅ ID correcto
                            } catch (NumberFormatException e) {
                                System.out.println("❌ ERROR: Ingresa un número válido.");
                            }
                        } while (!entradaValida);

                        if (bd.eliminarCliente(idEliminar)) {
                            System.out.println("✅ Cliente eliminado.");
                        } else {
                            System.out.println("❌ No se encontró el cliente.");
                        }
                        break;
                    case 3:
                        List<Cliente> clientes = bd.obtenerTodosLosClientes();
                        clientes.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("📌 Saliendo de Gestión de Clientes...");
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Ingresa un número del menú.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Ingresa un número válido del menú.");
            }

        } while (opcion != 4); // 🔄 Solo sale del bucle cuando el usuario elige "Salir"
    }


    private static void gestionarReservas(BaseDeDatos bd, Scanner scanner) {
    int opcion = -1;

    do {
        try {
            System.out.println("\n--- Gestión de Reservas ---");
            System.out.println("1. Hacer reserva");
            System.out.println("2. Listar reservas");
            System.out.println("3. Eliminar reserva");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("📌 Lista de clientes disponibles:");
                    for (Cliente cliente : bd.obtenerTodosLosClientes()) {
                        System.out.println("ID: " + cliente.getId() + " | Nombre: " + cliente.getNombre());
                    }

                    System.out.print("ID del cliente que hace la reserva: ");
                    int idCliente = Integer.parseInt(scanner.nextLine());
                    Cliente cliente = bd.buscarClientePorId(idCliente);
                    if (cliente == null) {
                        System.out.println("❌ Cliente no encontrado.");
                        return;
                    }

                    System.out.println("\n📌 Lista de barberos disponibles:");
                    for (Barbero barbero : bd.obtenerTodosLosBarberos()) {
                        System.out.println("ID: " + barbero.getId() + " | Nombre: " + barbero.getNombre());
                    }

                    System.out.print("ID del barbero: ");
                    int idBarbero = Integer.parseInt(scanner.nextLine());
                    Barbero barbero = bd.buscarBarberoPorId(idBarbero);
                    if (barbero == null) {
                        System.out.println("❌ Barbero no encontrado.");
                        return;
                    }

                    System.out.print("Fecha y hora de la reserva (YYYY-MM-DD HH:mm): ");
                    String fechaHoraStr = scanner.nextLine();
                    LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    System.out.println("\n📌 Servicios disponibles:");
                    List<Servicio> servicios = bd.obtenerTodosLosServicios();
                    servicios.forEach(servicio -> System.out.println("- " + servicio.getId() + ": " + servicio.getNombre()));

                    System.out.print("Elige el ID del servicio para la reserva: ");
                    int idServicio = Integer.parseInt(scanner.nextLine());
                    Servicio servicioSeleccionado = bd.buscarServicioPorId(idServicio);
                    if (servicioSeleccionado == null) {
                        System.out.println("❌ ERROR: Servicio no encontrado.");
                        return;
                    }

                    Reserva nuevaReserva = new Reserva(bd.getNextReservaId(), fechaHora, cliente, barbero, servicioSeleccionado);
                    
                    bd.agregarReserva(nuevaReserva);
                    bd.guardarDatos();
                    System.out.println("✅ Reserva creada con éxito.");
                    break;

                case 2: // Listar reservas y permitir actualización
                    List<Reserva> reservas = bd.obtenerTodasLasReservas();
                    reservas.forEach(System.out::println);

                    System.out.print("¿Deseas actualizar alguna reserva? (si/no): ");
                    String respuesta = scanner.nextLine().toLowerCase();
                    if (respuesta.equals("si")) {
                        System.out.print("ID de la reserva a actualizar: ");
                        int idReservaActualizar = Integer.parseInt(scanner.nextLine());
                        Reserva reservaActualizar = bd.buscarReservaPorId(idReservaActualizar);

                        if (reservaActualizar == null) { // 🔥 Si la reserva no existe, mostrar error
                            System.out.println("❌ ERROR: Reserva no encontrada.");
                            break;
                        }

                        System.out.println("Opciones de estado:");
                        System.out.println("1. Confirmar reserva");
                        System.out.println("2. Cancelar reserva");
                        System.out.println("3. Servicio completado");
                        System.out.print("Selecciona una opción: ");
                        int opcionEstado = Integer.parseInt(scanner.nextLine());

                        switch (opcionEstado) {
                            case 1:
                                reservaActualizar.confirmar();
                                break;
                            case 2:
                                reservaActualizar.cancelar();
                                break;
                            case 3:
                                reservaActualizar.completar();
                                break;
                            default:
                                System.out.println("❌ Opción no válida.");
                                return;
                        }

                        bd.guardarDatos(); // 🔥 Asegurar que se persiste el cambio en la base de datos
                        System.out.println("✅ Estado de la reserva actualizado correctamente.");
                    }
                    break;


                case 3:
                    System.out.print("ID de la reserva a eliminar: ");
                    int idReserva = Integer.parseInt(scanner.nextLine());
                    if (bd.eliminarReserva(idReserva)) {
                        System.out.println("✅ Reserva eliminada.");
                    } else {
                        System.out.println("❌ No se encontró la reserva.");
                    }
                    break;

                case 4:
                    System.out.println("📌 Saliendo de Gestión de Reservas...");
                    break;

                default:
                    System.out.println("❌ Opción no válida. Ingresa un número del menú.");
            }

        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("❌ ERROR: Ingresa un valor válido.");
        }

    } while (opcion != 4);
}


    private static void gestionarServicios(BaseDeDatos bd, Scanner scanner) {
    int opcion = -1;

    do {
        try {
            System.out.println("\n--- Gestión de Servicios ---");
            System.out.println("1. Crear un nuevo servicio");
            System.out.println("2. Listar servicios disponibles");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del servicio: ");
                    String nombreServicio = scanner.nextLine();
                    System.out.print("Descripción del servicio: ");
                    String descServicio = scanner.nextLine();

                    double precioServicio = 0;
                    int duracionServicio = 0;
                    boolean entradaValida = false;

                    do {
                        try {
                            System.out.print("Precio del servicio: ");
                            precioServicio = Double.parseDouble(scanner.nextLine());
                            System.out.print("Duración en minutos: ");
                            duracionServicio = Integer.parseInt(scanner.nextLine());

                            if (precioServicio < 0 || duracionServicio <= 0) {
                                System.out.println("❌ ERROR: El precio y la duración deben ser valores positivos.");
                                continue;
                            }

                            entradaValida = true;
                        } catch (NumberFormatException e) {
                            System.out.println("❌ ERROR: Ingresa solo números válidos para precio y duración.");
                        }
                    } while (!entradaValida);

                    Servicio nuevoServicio = new Servicio(bd.getNextServicioId(), nombreServicio, descServicio, precioServicio, duracionServicio);
                    bd.agregarServicio(nuevoServicio); // 🔥 Guardarlo en la base de datos general
                    bd.guardarDatos();
                    System.out.println("✅ Servicio agregado correctamente.");
                    break;

                case 2:
                    List<Servicio> servicios = bd.obtenerTodosLosServicios();
                    if (servicios.isEmpty()) {
                        System.out.println("❌ No hay servicios registrados aún.");
                    } else {
                        servicios.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.println("📌 Saliendo de Gestión de Servicios...");
                    break;

                default:
                    System.out.println("❌ Opción no válida. Ingresa un número del menú.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ERROR: Ingresa un número válido del menú.");
        }
    } while (opcion != 3);
}



    // Métodos para gestionar clientes, reservas y servicios siguen el mismo patrón...

}

