package modelo;

import java.io.Serializable;

public class Servicio implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int duracionMinutos;

    public Servicio(){

    }

    public Servicio(int id, String nombre, String descripcion, double precio, int duracionMinutos) {
        if (id <= 0) throw new IllegalArgumentException("El ID del servicio debe ser un número positivo.");
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre del servicio no puede ser nulo o vacío.");
        if (precio < 0) throw new IllegalArgumentException("El precio del servicio no puede ser negativo.");
        if (duracionMinutos <= 0) throw new IllegalArgumentException("La duración del servicio debe ser mayor a cero.");

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionMinutos = duracionMinutos;
    }

    public int getId() {
        return id;
    }

    // Setter necesario para asignar el ID desde BaseDeDatos
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("El ID del servicio debe ser un número positivo.");
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { // Setter para nombre si se puede modificar
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

     public void setDescripcion(String descripcion) { // Setter para descripción si se puede modificar
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) { 
        if (duracionMinutos <= 0) throw new IllegalArgumentException("La duración debe ser mayor a cero.");
        this.duracionMinutos = duracionMinutos;
    }

    @Override
    public String toString() {
        return "Servicio{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", precio=" + String.format("%.2f", precio) + // Formato para el precio
               ", duracionMinutos=" + duracionMinutos +
               '}';
    }
}