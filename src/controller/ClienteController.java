package controller;

import datos.BaseDeDatos;
import modelo.Cliente;
import java.util.Scanner;
import java.util.List;

public class ClienteController {
    public static void gestionar(BaseDeDatos bd, Scanner scanner) {
        int opcion = -1;
        do {
            try {
                System.out.println("\n--- Gestión de Clientes ---");
                System.out.println("1. Agregar cliente");
                System.out.println("2. Eliminar cliente");
                System.out.println("3. Listar clientes");
                System.out.println("4. Salir");
                System.out.print("Selecciona una opción: ");

                opcion = Integer.parseInt(scanner.nextLine());

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
                            System.out.println("✅ Cliente agregado.");
                            break;

                    case 2:
                        System.out.print("ID del cliente a eliminar: ");
                        int idEliminar = Integer.parseInt(scanner.nextLine());
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
        } while (opcion != 4);
    }
}
