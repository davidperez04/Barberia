package modelo;

public abstract class Usuario {
     protected String id;
    protected String nombre;
    protected String telefono;

    public Usuario(String id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public abstract void mostrarInformacion();
}
