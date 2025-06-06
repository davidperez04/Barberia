package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Barbero extends Usuario{
     private List<String> especialidades;
    private List<Horario> horarioTrabajo;

    public Barbero(String id, String nombre, String telefono) {
        super(id, nombre, telefono);
        this.especialidades = new ArrayList<>();
        this.horarioTrabajo = new ArrayList<>();
    }

    public List<Horario> verDisponibilidad(Date fecha) {
        List<Horario> disponibles = new ArrayList<>();
        for (Horario h : horarioTrabajo) {
            if (h.getFecha().equals(fecha) && h.isDisponible()) {
                disponibles.add(h);
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

    @Override
    public void mostrarInformacion() {
        System.out.println("Barbero: " + nombre + ", Especialidades: " + especialidades);
    }
}
