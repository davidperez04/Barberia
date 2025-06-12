package menu;

import controller.ClienteController;
import datos.BaseDeDatos;
import java.util.Scanner;

public class ClienteMenu {
    public static void mostrar(BaseDeDatos baseDatos, Scanner scanner) {
        ClienteController.gestionar(baseDatos, scanner);
    }
}