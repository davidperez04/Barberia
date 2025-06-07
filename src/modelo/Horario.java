package modelo;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Horario {
    private Date fecha;
    private String horaInicio;
    private String horaFin;
    private boolean disponible;

    public Horario(Date fecha, String horaInicio, String horaFin) {
        if (fecha == null) throw new IllegalArgumentException("La fecha no puede ser nula");
        if (!esHoraValida(horaInicio)) throw new IllegalArgumentException("Hora de inicio inválida");
        if (!esHoraValida(horaFin)) throw new IllegalArgumentException("Hora de fin inválida");
        // Copia defensiva de la fecha
        this.fecha = new Date(fecha.getTime());
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponible = true;
    }

    public void marcarComoOcupado() {
        this.disponible = false;
    }

    // Copia defensiva al devolver la fecha
    public Date getFecha() {
        return new Date(fecha.getTime());
    }

    public boolean isDisponible() {
        return disponible;
    }

    // Validación de formato HH:mm
    private boolean esHoraValida(String hora) {
        if (hora == null) return false;
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
        formatoHora.setLenient(false);
        try {
            formatoHora.parse(hora);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}