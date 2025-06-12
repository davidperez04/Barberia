package Menu;

import controller.BarberoController;
import datos.BaseDeDatos;
import java.util.Scanner;

public class BarberoMenu {
    public static void mostrar(BaseDeDatos baseDatos, Scanner scanner) {
        BarberoController.gestionar(baseDatos, scanner);
    }
}