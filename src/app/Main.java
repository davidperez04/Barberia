package app;

import datos.BaseDeDatos;
import Menu.*;
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
            int opcion = -1;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    BarberoMenu.mostrar(bd, scanner);
                    break;
                case 2:
                    ClienteMenu.mostrar(bd, scanner);
                    break;
                case 3:
                    ReservaMenu.mostrar(bd, scanner);
                    break;
                case 4:
                    ServicioMenu.mostrar(bd, scanner);
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
}