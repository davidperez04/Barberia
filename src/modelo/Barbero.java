package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Barbero extends Usuario {

    private List<String> especialidades;
    private List<Horario> horarioTrabajo;
    private String contrasena; 

    // Constructor vacío por razones de deserialización
    public Barbero() {
        super();
        this.especialidades = new ArrayList<>();
        this.horarioTrabajo = new ArrayList<>();
    }

    // Constructor actualizado para incluir la contraseña del barbero
    public Barbero(int id, String nombre, String telefono, String contrasena) { 
        super(id, nombre, telefono);
        this.especialidades = new ArrayList<>();
        this.horarioTrabajo = new ArrayList<>();
        this.contrasena = contrasena; 
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

    public String getContrasena() { 
        return contrasena;
    }

    public void setContrasena(String contrasena) { 
        this.contrasena = contrasena;
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
        System.out.println("Barbero ID: " + getId() + ", Nombre: " + getNombre() + ", Teléfono: " + getTelefono() + ", Especialidades: " + especialidades + ", Contraseña: " + contrasena); // Muestra la contraseña para depuración, puedes quitarla en prod.
    }

    @Override
    public String toString() {
        return "Barbero{" +
               "id=" + getId() +
               ", nombre='" + getNombre() + '\'' +
               ", telefono='" + getTelefono() + '\'' +
               ", especialidades=" + especialidades +
               ", horarioTrabajo=" + horarioTrabajo +
               ", contrasena='[CENSURADO]'" + 
               '}';
    }
}