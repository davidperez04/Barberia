package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{

    private String email;
    private List<Reserva> historialReservas;

    public String getEmail(){
        return email;
    }

    public List<Reserva> getHistorialReservas(){
        return historialReservas;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setHistorialReservas(List<Reserva> historialReservas){
        this.historialReservas = historialReservas;
    }

    public Cliente(int id, String nombre, String telefono, String email) {
        super(id, nombre, telefono);
        this.email = email;
        this.historialReservas = new ArrayList<>();
    }

    public Cliente() { super(); } //Base de datos

    public void agregarReserva(Reserva reserva) {
        historialReservas.add(reserva);
    }

    public void cancelarReserva(int idReserva) {
    for (Reserva reserva : historialReservas) {
        if (reserva.getIdReserva() == idReserva) { 
            reserva.setEstado(EstadoReserva.CANCELADA);
            }
        }
    }
    
    @Override
    public void mostrarInformacion() {
        System.out.println("Cliente ID: " + getId() + ", Nombre: " + getNombre() + ", Email: " + email + ", Teléfono: " + getTelefono());
    }
    
    @Override
public String toString() {
    return "Cliente{" +
           "id='" + getId() + '\'' + 
           ", nombre='" + getNombre() + '\'' + 
           ", telefono='" + getTelefono() + '\'' + 
           ", email='" + email + '\'' + 
           ", historialReservas=" + historialReservas.size() + " reservas" + 
           '}';
}
}
