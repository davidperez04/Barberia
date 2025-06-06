package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
      private String idReserva;
    private LocalDateTime fechaHora;
    private EstadoReserva estado;
    private Cliente cliente;
    private Barbero barbero;
    private List<Servicio> servicios;

    public Reserva(String idReserva, LocalDateTime fechaHora, Cliente cliente) {
        this.idReserva = idReserva;
        this.fechaHora = fechaHora;
        this.estado = EstadoReserva.PENDIENTE;
        this.cliente = cliente;
        this.servicios = new ArrayList<>();
    }

    public void confirmar() {
        if (estado == EstadoReserva.PENDIENTE) {
            this.estado = EstadoReserva.CONFIRMADA;
        }
    }

    public void cancelar() {
        if (estado == EstadoReserva.PENDIENTE || estado == EstadoReserva.CONFIRMADA) {
            this.estado = EstadoReserva.CANCELADA;
        }
    }

    public void completar() {
        if (estado == EstadoReserva.CONFIRMADA) {
            this.estado = EstadoReserva.COMPLETADA;
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Servicio s : servicios) {
            total += s.getPrecio();
        }
        return total;
    }

    public void asignarBarbero(Barbero barbero) {
        this.barbero = barbero;
    }

    public void agregarServicio(Servicio servicio) {
        servicios.add(servicio);
    }

    public String getIdReserva() {
        return idReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }
}
