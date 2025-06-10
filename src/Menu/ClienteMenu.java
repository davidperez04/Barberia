package Menu;

import controller.ClienteController;
import datos.BaseDeDatos;
import java.util.Scanner;

public class ClienteMenu {
    public static void mostrar(BaseDeDatos bd, Scanner scanner) {
        ClienteController.gestionar(bd, scanner);
    }
}