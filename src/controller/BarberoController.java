package controller;

import datos.BaseDeDatos;
import modelo.Barbero;
import modelo.Horario;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class BarberoController {
    public static void gestionar(BaseDeDatos baseDeDatos, Scanner scanner) {
        int opcion = -1;
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

                opcion = Integer.parseInt(scanner.nextLine());

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
                        } while (telefono.length() != 10 || !telefono.matches("\\d+"));
                        Barbero nuevoBarbero = new Barbero(baseDeDatos.getNextBarberoId(), nombre, telefono);
                        baseDeDatos.agregarBarbero(nuevoBarbero);
                        System.out.println("✅ Barbero agregado con ID único.");
                        break;
                    case 2:
                        System.out.print("ID del barbero a eliminar: ");
                        int idEliminar = Integer.parseInt(scanner.nextLine());
                        if (baseDeDatos.eliminarBarbero(idEliminar)) {
                            System.out.println("✅ Barbero eliminado.");
                        } else {
                            System.out.println("❌ No se encontró el barbero.");
                        }
                        break;
                    case 3:
                        List<Barbero> barberos = baseDeDatos.obtenerTodosLosBarberos();
                        barberos.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("Lista de barberos disponibles :");
                        List<Barbero> barberosOut = baseDeDatos.obtenerTodosLosBarberos();
                        barberosOut.forEach(System.out::println);
                        System.out.println();
                        System.out.println("ID del barbero al que quieres agregar horario: ");
                        int idBarberoHorario = Integer.parseInt(scanner.nextLine());
                        System.out.print("Fecha (YYYY-MM-DD): ");
                        String fechaStr = scanner.nextLine();
                        System.out.print("Hora inicio (HH:mm): ");
                        String horaInicio = scanner.nextLine();
                        System.out.print("Hora fin (HH:mm): ");
                        String horaFin = scanner.nextLine();
                        Horario nuevoHorario = new Horario(java.sql.Date.valueOf(fechaStr), horaInicio, horaFin);
                        Barbero barbero = baseDeDatos.buscarBarberoPorId(idBarberoHorario);
                        if (barbero != null) {
                            barbero.agregarHorario(nuevoHorario);
                            baseDeDatos.guardarDatos();
                            System.out.println("✅ Horario agregado.");
                        } else {
                            System.out.println("❌ Barbero no encontrado.");
                        }
                        break;
                    case 5:
                        System.out.println("Lista de barberos disponibles :");
                        List<Barbero> barberosOut2 = baseDeDatos.obtenerTodosLosBarberos();
                        barberosOut2.forEach(System.out::println);
                        System.out.print("ID del barbero al que quieres asignar especialidades: ");
                        int idBarberoEspecialidad = Integer.parseInt(scanner.nextLine());
                        Barbero barberoEspecialidad = baseDeDatos.buscarBarberoPorId(idBarberoEspecialidad);
                        if (barberoEspecialidad != null) {
                            System.out.print("Ingrese las especialidades separadas por comas (Ej: Corte, Afeitado, Diseño): ");
                            String inputEspecialidades = scanner.nextLine();
                            List<String> especialidades = Arrays.asList(inputEspecialidades.split(", "));
                            barberoEspecialidad.setEspecialidades(especialidades);
                            baseDeDatos.guardarDatos();
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
        } while (opcion != 6);
    }
}
