package menu;

import controller.ServicioController;
import datos.BaseDeDatos;
import java.util.Scanner;

public class ServicioMenu {
    public static void mostrar(BaseDeDatos baseDatos, Scanner scanner) {
        ServicioController.gestionar(baseDatos, scanner);
    }
}