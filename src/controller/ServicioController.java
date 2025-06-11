package controller;

import datos.BaseDeDatos;
import modelo.Servicio;
import java.util.Scanner;
import java.util.List;

public class ServicioController {
    public static void gestionar(BaseDeDatos baseDatos, Scanner scanner) {
        int opcion = -1;
        do {
            try {
                System.out.println("\n--- Gestión de Servicios ---");
                System.out.println("1. Agregar servicio");
                System.out.println("2. Eliminar servicio");
                System.out.println("3. Listar servicios");
                System.out.println("4. Salir");
                System.out.print("Selecciona una opción: ");

                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del servicio: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Descripción del servicio: ");
                        String descripcion = scanner.nextLine();
                        System.out.print("Precio del servicio: ");
                        double precio = Double.parseDouble(scanner.nextLine());
                        System.out.print("Duración en minutos: ");
                        int duracion = Integer.parseInt(scanner.nextLine());

                        Servicio nuevoServicio = new Servicio(
                            baseDatos.getNextServicioId(),
                            nombre,
                            descripcion,
                            precio,
                            duracion
                        );
                        baseDatos.agregarServicio(nuevoServicio);
                        System.out.println("Servicio agregado.");
                        break;
                    case 2:
                        System.out.print("ID del servicio a eliminar: ");
                        int idEliminar = Integer.parseInt(scanner.nextLine());
                        if (baseDatos.eliminarServicio(idEliminar)) {
                            System.out.println("Servicio eliminado.");
                        } else {
                            System.out.println("No se encontró el servicio.");
                        }
                        break;
                    case 3:
                        List<Servicio> servicios = baseDatos.obtenerTodosLosServicios();
                        servicios.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("Saliendo de Gestión de Servicios...");
                        break;
                    default:
                        System.out.println("Opción no válida. Ingresa un número del menú.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Ingresa un número válido del menú.");
            }
        } while (opcion != 4);
    }
}