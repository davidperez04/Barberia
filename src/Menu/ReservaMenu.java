package Menu;

import controller.ReservaController;
import datos.BaseDeDatos;
import java.util.Scanner;

public class ReservaMenu {
    public static void mostrar(BaseDeDatos baseDatos, Scanner scanner) {
        ReservaController.gestionar(baseDatos, scanner);
    }
}