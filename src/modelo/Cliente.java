package modelo;
import java.util.ArrayList;
import java.util.List;
public class Cliente extends Usuario{
     private String email;
    private List<Reserva> historialReservas;

    public Cliente(String id, String nombre, String telefono, String email) {
        super(id, nombre, telefono);
        this.email = email;
        this.historialReservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        historialReservas.add(reserva);
    }

    public void cancelarReserva(String idReserva) {
        for (Reserva r : historialReservas) {
            if (r.getIdReserva().equals(idReserva)) {
                r.setEstado(EstadoReserva.CANCELADA);
            }
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cliente: " + nombre + ", Email: " + email);
    }
}
