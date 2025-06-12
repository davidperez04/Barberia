package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Barbero extends Usuario {
    private static final long serialVersionUID = 1L;


    private List<String> especialidades;
    private List<Horario> horarioTrabajo;
    private List<Servicio> servicios;

    // Constructor vacío por razones de deserialización
    public Barbero() {
        super();
        this.especialidades = new ArrayList<>();
        this.horarioTrabajo = new ArrayList<>();
    }

    // Constructor actualizado para incluir la contraseña del barbero
    public Barbero(int id, String nombre, String telefono) { 
        super(id, nombre, telefono);
        this.especialidades = new ArrayList<>();
        this.horarioTrabajo = new ArrayList<>();
        this.servicios = new ArrayList<>();
    }

    public List<Servicio> getServicios(){
        return servicios;
    }

    public void setServicios(List<Servicio> servicios){
        this.servicios = servicios;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public List<Horario> getHorarioTrabajo() {
        return horarioTrabajo;
    }

    public void setEspecialidades(List<String> especialidades) { 
        this.especialidades = especialidades;
    }

    public void setHorarioTrabajo(List<Horario> horarioTrabajo) { 
        this.horarioTrabajo = horarioTrabajo;
    }


    public List<Horario> verDisponibilidad(Date fecha) {
        List<Horario> disponibles = new ArrayList<>();
        for (Horario horario : horarioTrabajo) {
            if (horario.getFecha().equals(fecha) && horario.isDisponible()) {
                disponibles.add(horario);
            }
        }
        return disponibles;
    }

    public void agregarEspecialidad(String especialidad) {
        especialidades.add(especialidad);
    }

    public void agregarHorario(Horario horario) {
        horarioTrabajo.add(horario);
    }

    public void agregarServicio(Servicio servicio) {
    if (servicio == null) {
        throw new IllegalArgumentException("El servicio no puede ser nulo.");
    }
    servicios.add(servicio);
}

    @Override
    public void mostrarInformacion() {
        System.out.println("Barbero ID: " + getId() + ", Nombre: " + getNombre() + ", Teléfono: " + getTelefono() + ", Especialidades: " + especialidades);
    }
    
     @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        salida.append("ID: ").append(id).append("\n")
        .append("Nombre: ").append(nombre).append("\n")
        .append("Teléfono: ").append(telefono).append("\n")
        .append("Especialidades: ").append(especialidades).append("\n")
        .append("Horarios:\n");
        for (Horario hora : horarioTrabajo) {
            salida.append("  ").append(hora).append("\n");
        }
        return salida.toString();
    }
}