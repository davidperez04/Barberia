package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Barbero extends Usuario{

    private List<String> especialidades;
    private List<Horario> horarioTrabajo;

    //constructor vacío por razones de deserialización
    public Barbero(){
        super();
        this.especialidades = new ArrayList<>();
        this.horarioTrabajo = new ArrayList<>();
    }

    public Barbero(int id, String nombre, String telefono) {
        super(id, nombre, telefono);
        this.especialidades = new ArrayList<>();
        this.horarioTrabajo = new ArrayList<>();
    }

    public List<String> getEspecialidades(){
        return especialidades;
    }

    public List<Horario> getHorarioTrabajo(){
        return horarioTrabajo;
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

   @Override
    public void mostrarInformacion() {
        System.out.println("Barbero ID: " + getId() + ", Nombre: " + getNombre() + ", Teléfono: " + getTelefono() + ", Especialidades: " + especialidades);
    }

    @Override
    public String toString() {
        return "Barbero{" +
            "id=" + getId() + 
            ", nombre='" + getNombre() + '\'' +
            ", telefono='" + getTelefono() + '\'' +
            ", especialidades=" + especialidades + 
            ", numHorarios=" + (horarioTrabajo != null ? horarioTrabajo.size() : 0) +
            '}';
    }

}
