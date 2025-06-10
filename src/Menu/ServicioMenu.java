package Menu;

import controller.ServicioController;
import datos.BaseDeDatos;
import java.util.Scanner;

public class ServicioMenu {
    public static void mostrar(BaseDeDatos bd, Scanner scanner) {
        ServicioController.gestionar(bd, scanner);
    }
}