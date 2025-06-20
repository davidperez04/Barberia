package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idReserva;
    private LocalDateTime fechaHora;
    private EstadoReserva estado;
    private Cliente cliente;
    private Barbero barbero;
    private Servicio servicio;
    private List<Servicio> servicios;

    public Reserva(){
        this.estado = EstadoReserva.PENDIENTE;
        this.servicios = new ArrayList<>();
    }

    // Constructor alternativo si quieres crear la reserva sin estado inicial
    public Reserva(int idReserva, LocalDateTime fechaHora, Cliente cliente, Barbero barbero, Servicio servicio) {
    if (idReserva <= 0) 
        throw new IllegalArgumentException("El id de la reserva debe ser un número positivo.");
    if (fechaHora == null)
        throw new IllegalArgumentException("La fecha y hora no pueden ser nulas.");
    if (cliente == null)
        throw new IllegalArgumentException("El cliente no puede ser nulo.");
    if (barbero == null)
        throw new IllegalArgumentException("El barbero no puede ser nulo.");
    if (servicio == null)
        throw new IllegalArgumentException("El servicio no puede ser nulo.");

    this.idReserva = idReserva; 
    this.fechaHora = fechaHora;
    this.estado = EstadoReserva.PENDIENTE; 
    this.cliente = cliente;
    this.barbero = barbero; // Agregar Barbero
    this.servicio = servicio; // Agregar Servicio
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

    // ...existing code...
public double calcularTotal() {
    double total = 0;
    // Suma el precio del servicio principal si existe
    if (servicio != null) {
        total += servicio.getPrecio();
    }
    // Suma los precios de los servicios adicionales (si hay)
    for (Servicio servicios : servicios) {
        if (servicios != null) {
            total += servicios.getPrecio();
        }
    }
    return total;
}
// ...existing code...

    public int getIdReserva(){
        return idReserva;
    }

    public void setIdReserva(int id){
        if (id <= 0)
            throw new IllegalArgumentException("El id de la reserva debe ser un número positivo.");
        this.idReserva = id;
    }

    public LocalDateTime getFechaHora(){
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) { 
        if (fechaHora == null)
            throw new IllegalArgumentException("La fecha y hora no pueden ser nulas");
        this.fechaHora = fechaHora;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

     public void setEstado(EstadoReserva nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        switch (estado) {
            case PENDIENTE:
                if (nuevoEstado == EstadoReserva.CONFIRMADA || nuevoEstado == EstadoReserva.CANCELADA) {
                    this.estado = nuevoEstado;
                }
                break;
            case CONFIRMADA:
                if (nuevoEstado == EstadoReserva.COMPLETADA || nuevoEstado == EstadoReserva.CANCELADA) {
                    this.estado = nuevoEstado;
                }
                break;
            default:
                // No se permite cambiar desde CANCELADA o COMPLETADA
                break;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null)
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        this.cliente = cliente;
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public void asignarBarbero(Barbero barbero) {
        if (barbero == null) {
            throw new IllegalArgumentException("El barbero no puede ser nulo");
        }
        this.barbero = barbero;
    }

    public void agregarServicio(Servicio servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo");
        }
        servicios.add(servicio);
    }

     public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        if (servicio == null)
            throw new IllegalArgumentException("El servicio no puede ser nulo");
        this.servicio = servicio;
    }


    // Devuelve una copia inmodificable de la lista de servicios
    public List<Servicio> getServicios() {
        return Collections.unmodifiableList(servicios);
    }

    @Override
public String toString() {
    return "Reserva{" +
           "id=" + idReserva +
           ", fechaHora=" + fechaHora +
           ", estado=" + estado +
           ", clienteId=" + cliente.getId() +
           ", barberoId=" + barbero.getId() +
           ", total=" + calcularTotal() + // Ahora muestra el total calculado
           '}';
}

}