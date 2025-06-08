package modelo;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int id;
    protected String nombre;
    protected String telefono;

    public Usuario(int id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Usuario(){}

    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setId( int id){//Base de datos
        this.id = id;
    }

    public abstract void mostrarInformacion();

    @Override
    public String toString() {
        return "Usuario{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", telefono='" + telefono + '\'' +
               '}';
    }
}
