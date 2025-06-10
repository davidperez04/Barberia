package controller;

import datos.BaseDeDatos;
import java.time.format.DateTimeFormatter;
import modelo.Barbero;
import modelo.Cliente;
import modelo.EstadoReserva;
import modelo.Horario;
import modelo.Reserva;
import modelo.Servicio;
import java.time.ZoneId;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.List;

public class ReservaController {
    public static void gestionar(BaseDeDatos baseDatos, Scanner scanner) {
        int opcion = -1;
        do {
            try {
                System.out.println("\n--- Gestión de Reservas ---");
                System.out.println("1. Agregar reserva");
                System.out.println("2. Eliminar reserva");
                System.out.println("3. Listar reservas");
                System.out.println("4. Salir");
                System.out.print("Selecciona una opción: ");

                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("Lista de clientes:");
                        baseDatos.obtenerTodosLosClientes().forEach(System.out::println);
                        System.out.print("ID del cliente: ");
                        int idCliente = Integer.parseInt(scanner.nextLine());
                        Cliente cliente = baseDatos.buscarClientePorId(idCliente);
                        if (cliente == null) {
                            System.out.println("❌ Cliente no encontrado.");
                            break;
                        }

                        System.out.println("Lista de barberos:");
                        baseDatos.obtenerTodosLosBarberos().forEach(System.out::println);
                        System.out.print("ID del barbero: ");
                        int idBarbero = Integer.parseInt(scanner.nextLine());
                        Barbero barbero = baseDatos.buscarBarberoPorId(idBarbero);
                        if (barbero == null) {
                            System.out.println("❌ Barbero no encontrado.");
                            break;
                        }

                        System.out.println("Lista de servicios:");
                        baseDatos.obtenerTodosLosServicios().forEach(System.out::println);
                        System.out.print("ID del servicio: ");
                        int idServicio = Integer.parseInt(scanner.nextLine());
                        Servicio servicio = baseDatos.buscarServicioPorId(idServicio);
                        if (servicio == null) {
                            System.out.println("❌ Servicio no encontrado.");
                            break;
                        }

                        System.out.print("Fecha y hora de la reserva (YYYY-MM-DD HH:MM): ");
                        String fechaHoraStr = scanner.nextLine();
                        LocalDateTime fechaHora;
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
                        } catch (Exception e) {
                            System.out.println("❌ Formato de fecha y hora incorrecto.");
                            break;
                        }

                        // Buscar horario disponible en rango
                        Horario horarioSeleccionado = null;
                        for (Horario hora : barbero.getHorarioTrabajo()) {
                            if (
                                hora.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(fechaHora.toLocalDate())
                                && hora.isDisponible()
                            ) {
                                LocalTime inicio = LocalTime.parse(hora.getHoraInicio());
                                LocalTime fin = LocalTime.parse(hora.getHoraFin());
                                LocalTime reserva = fechaHora.toLocalTime();

                                // Permite reservar si la hora está dentro del rango [inicio, fin)
                                if (!reserva.isBefore(inicio) && reserva.isBefore(fin)) {
                                    horarioSeleccionado = hora;
                                    break;
                                }
                            }
                        }

                        if (horarioSeleccionado != null) {
                            horarioSeleccionado.setDisponible(false);
                            Reserva nuevaReserva = new Reserva(baseDatos.getNextReservaId(), fechaHora, cliente, barbero, servicio);
                            nuevaReserva.setEstado(EstadoReserva.CONFIRMADA);
                            baseDatos.agregarReserva(nuevaReserva);
                            System.out.println("✅ Reserva agregada y confirmada.");
                        } else {
                            System.out.println("❌ No hay horario disponible para esa fecha y hora. Reserva no confirmada.");
                        }
                        break;

                    case 2:
                        List<Reserva> reservasEliminar = baseDatos.obtenerTodasLasReservas();
                        if (reservasEliminar.isEmpty()) {
                            System.out.println("No hay reservas para eliminar.");
                            break;
                        }
                        System.out.println("Lista de reservas:");
                        reservasEliminar.forEach(System.out::println);
                        System.out.print("ID de la reserva a eliminar: ");
                        int idReservaEliminar = Integer.parseInt(scanner.nextLine());
                        boolean eliminada = baseDatos.eliminarReserva(idReservaEliminar);
                        if (eliminada) {
                            System.out.println("✅ Reserva eliminada.");
                        } else {
                            System.out.println("❌ No se encontró la reserva con ese ID.");
                        }
                        break;
                    case 3:
                        List<Reserva> reservas = baseDatos.obtenerTodasLasReservas();
                        reservas.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("📌 Saliendo de Gestión de Reservas...");
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Ingresa un número del menú.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Ingresa un número válido del menú.");
            }
        } while (opcion != 4);
    }
}