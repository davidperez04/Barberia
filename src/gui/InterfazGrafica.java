package gui;

import datos.BaseDeDatos;
import java.awt.BorderLayout;
import java.awt.Component;
import java.time.LocalDateTime;
import java.util.Iterator;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import modelo.Barbero;
import modelo.Cliente;
import modelo.Reserva;
import modelo.Servicio;

public class InterfazGrafica extends JFrame {
   private BaseDeDatos bd = new BaseDeDatos();

   public InterfazGrafica() {
      this.setTitle("Sistema de Barbería");
      this.setSize(800, 600);
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);
      JTabbedPane tabs = new JTabbedPane();
      tabs.add("Barberos", this.crearPanelBarberos());
      tabs.add("Clientes", this.crearPanelClientes());
      tabs.add("Servicios", this.crearPanelServicios());
      tabs.add("Reservas", this.crearPanelReservas());
      this.add(tabs);
      this.setVisible(true);
   }

   private JPanel crearPanelBarberos() {
    JPanel panel = new JPanel(new BorderLayout());
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
    JTable table = new JTable(model);
    Iterator var5 = this.bd.obtenerTodosLosBarberos().iterator();

    while (var5.hasNext()) {
        Barbero b = (Barbero) var5.next();
        model.addRow(new Object[]{b.getId(), b.getNombre()});
    }

    JButton agregar = new JButton("Agregar Barbero");
    agregar.addActionListener((e) -> {
        String nombre = this.solicitarSoloLetras("Nombre del barbero:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
            return;
        }
        String telefono = this.solicitarSoloNumeros("Teléfono del barbero:");
        if (telefono == null || telefono.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El teléfono es obligatorio.");
            return;
        }
        String correo = this.solicitarCorreo("Email del barbero:");
        if (correo == null || correo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El correo es obligatorio.");
            return;
        }
        Barbero nuevo = new Barbero(this.bd.getNextBarberoId(), nombre, telefono);
        this.bd.agregarBarbero(nuevo);
        model.addRow(new Object[]{nuevo.getId(), nombre});
    });

    JButton eliminar = new JButton("Eliminar Seleccionado");
    eliminar.addActionListener((e) -> {
        int fila = table.getSelectedRow();
        if (fila >= 0) {
            int id = (Integer) model.getValueAt(fila, 0);
            this.bd.eliminarBarbero(id);
            model.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        }
    });
    JPanel botones = new JPanel();
    botones.add(agregar);
    botones.add(eliminar);
    panel.add(new JScrollPane(table), "Center");
    panel.add(botones, "South");
    return panel;
}

private JPanel crearPanelClientes() {
    JPanel panel = new JPanel(new BorderLayout());
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
    JTable table = new JTable(model);
    Iterator var5 = this.bd.obtenerTodosLosClientes().iterator();

    while (var5.hasNext()) {
        Cliente c = (Cliente) var5.next();
        model.addRow(new Object[]{c.getId(), c.getNombre()});
    }

    JButton agregar = new JButton("Agregar Cliente");
    agregar.addActionListener((e) -> {
        String nombre = this.solicitarSoloLetras("Nombre del cliente:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
            return;
        }
        String telefono = this.solicitarSoloNumeros("Teléfono del cliente:");
        if (telefono == null || telefono.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El teléfono es obligatorio.");
            return;
        }
        String email = this.solicitarCorreo("Email del cliente:");
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El correo es obligatorio.");
            return;
        }
        Cliente nuevo = new Cliente(this.bd.getNextClienteId(), nombre, telefono, email);
        this.bd.agregarCliente(nuevo);
        model.addRow(new Object[]{nuevo.getId(), nombre});
    });

    JButton eliminar = new JButton("Eliminar Seleccionado");
    eliminar.addActionListener((e) -> {
        int fila = table.getSelectedRow();
        if (fila >= 0) {
            int id = (Integer) model.getValueAt(fila, 0);
            this.bd.eliminarCliente(id);
            model.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        }
    });
    JPanel botones = new JPanel();
    botones.add(agregar);
    botones.add(eliminar);
    panel.add(new JScrollPane(table), "Center");
    panel.add(botones, "South");
    return panel;
}

private JPanel crearPanelServicios() {
    JPanel panel = new JPanel(new BorderLayout());
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio", "Duración"}, 0);
    JTable table = new JTable(model);
    Iterator var5 = this.bd.obtenerTodosLosServicios().iterator();

    while (var5.hasNext()) {
        Servicio s = (Servicio) var5.next();
        model.addRow(new Object[]{s.getId(), s.getNombre(), s.getPrecio(), s.getDuracionMinutos()});
    }

    JButton agregar = new JButton("Agregar Servicio");
    agregar.addActionListener((e) -> {
        String nombre = this.solicitarSoloLetras("Nombre del servicio:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
            return;
        }
        String descripcion = JOptionPane.showInputDialog(this, "Descripción del servicio:");
        if (descripcion == null || descripcion.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La descripción es obligatoria.");
            return;
        }
        String precioStr = this.solicitarSoloNumeros("Precio del servicio:");
        if (precioStr == null || precioStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El precio es obligatorio.");
            return;
        }
        String duracionStr = this.solicitarSoloNumeros("Duración en minutos:");
        if (duracionStr == null || duracionStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La duración es obligatoria.");
            return;
        }
        try {
            double precio = Double.parseDouble(precioStr);
            int duracion = Integer.parseInt(duracionStr);
            Servicio s = new Servicio(this.bd.getNextServicioId(), nombre, descripcion, precio, duracion);
            this.bd.agregarServicio(s);
            model.addRow(new Object[]{s.getId(), nombre, precio, duracion});
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos.");
        }
    });

    JButton eliminar = new JButton("Eliminar Seleccionado");
    eliminar.addActionListener((e) -> {
        int fila = table.getSelectedRow();
        if (fila >= 0) {
            int id = (Integer) model.getValueAt(fila, 0);
            this.bd.eliminarServicio(id);
            model.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        }
    });
    JPanel botones = new JPanel();
    botones.add(agregar);
    botones.add(eliminar);
    panel.add(new JScrollPane(table), "Center");
    panel.add(botones, "South");
    return panel;
}

private JPanel crearPanelReservas() {
    JPanel panel = new JPanel(new BorderLayout());
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Cliente", "Barbero", "Servicio", "Precio", "Fecha"}, 0);
    JTable table = new JTable(model);
    Iterator var5 = this.bd.obtenerTodasLasReservas().iterator();

    while (var5.hasNext()) {
        Reserva r = (Reserva) var5.next();
        String nombreServicio = "";
        String precioServicio = "";
        // Primero intenta obtener el servicio principal
        if (r.getServicio() != null) {
            nombreServicio = r.getServicio().getNombre();
            precioServicio = String.valueOf(r.getServicio().getPrecio());
        } else if (r.getServicios() != null && !r.getServicios().isEmpty()) {
            // Si no hay servicio principal, toma el primero de la lista
            Servicio s = r.getServicios().get(0);
            nombreServicio = s.getNombre();
            precioServicio = String.valueOf(s.getPrecio());
        }
        model.addRow(new Object[]{
            r.getIdReserva(),
            r.getCliente() != null ? r.getCliente().getNombre() : "",
            r.getBarbero() != null ? r.getBarbero().getNombre() : "",
            nombreServicio,
            precioServicio,
            r.getFechaHora() != null ? r.getFechaHora().toString() : ""
        });
    }

    JButton agregar = new JButton("Agregar Reserva");
    agregar.addActionListener((e) -> {
        if (!this.bd.obtenerTodosLosClientes().isEmpty() && !this.bd.obtenerTodosLosBarberos().isEmpty() && !this.bd.obtenerTodosLosServicios().isEmpty()) {
            Cliente cliente = (Cliente) JOptionPane.showInputDialog(this, "Selecciona el cliente:", "Cliente", 3, (Icon) null, this.bd.obtenerTodosLosClientes().toArray(), (Object) null);
            Barbero barbero = (Barbero) JOptionPane.showInputDialog(this, "Selecciona el barbero:", "Barbero", 3, (Icon) null, this.bd.obtenerTodosLosBarberos().toArray(), (Object) null);
            Servicio servicio = (Servicio) JOptionPane.showInputDialog(this, "Selecciona el servicio:", "Servicio", 3, (Icon) null, this.bd.obtenerTodosLosServicios().toArray(), (Object) null);
            String fechaStr = JOptionPane.showInputDialog(this, "Ingresa la fecha y hora (AAAA-MM-DDTHH:MM):", LocalDateTime.now().toString().substring(0, 16));

            try {
                LocalDateTime fecha = LocalDateTime.parse(fechaStr);
                if (cliente != null && barbero != null && servicio != null) {
                    Reserva reserva = new Reserva(this.bd.getNextReservaId(), fecha, cliente, barbero, servicio);
                    this.bd.agregarReserva(reserva);
                    model.addRow(new Object[]{
                        reserva.getIdReserva(),
                        cliente.getNombre(),
                        barbero.getNombre(),
                        servicio.getNombre(),
                        servicio.getPrecio(),
                        fecha.toString()
                    });
                }
            } catch (Exception var9) {
                JOptionPane.showMessageDialog(this, "Fecha inválida. Debe estar en el formato AAAA-MM-DDTHH:MM");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Debe haber al menos un cliente, barbero y servicio registrado.");
        }
    });
    JButton eliminar = new JButton("Eliminar Seleccionado");
    eliminar.addActionListener((e) -> {
        int fila = table.getSelectedRow();
        if (fila >= 0) {
            int id = (Integer) model.getValueAt(fila, 0);
            this.bd.eliminarReserva(id);
            model.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        }
    });
    JPanel botones = new JPanel();
    botones.add(agregar);
    botones.add(eliminar);
    panel.add(new JScrollPane(table), "Center");
    panel.add(botones, "South");
    return panel;
}

   private String solicitarSoloLetras(String mensaje) {
      while(true) {
         String input = JOptionPane.showInputDialog(this, mensaje);
         if (input == null) {
            return null;
         }

         if (input.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+")) {
            return input;
         }

         JOptionPane.showMessageDialog(this, "Solo se permiten letras.");
      }
   }

   private String solicitarSoloNumeros(String mensaje) {
      while(true) {
         String input = JOptionPane.showInputDialog(this, mensaje);
         if (input == null) {
            return null;
         }

         if (input.matches("\\d+")) {
            return input;
         }

         JOptionPane.showMessageDialog(this, "Solo se permiten números.");
      }
   }

   private String solicitarCorreo(String mensaje) {
      while(true) {
         String input = JOptionPane.showInputDialog(this, mensaje);
         if (input == null) {
            return null;
         }

         if (input.matches("^[\\w\\.-]+@[\\w\\.-]+\\.(com|co)$")) {
            return input;
         }

         JOptionPane.showMessageDialog(this, "Correo inválido. Debe contener '@' y terminar en '.com' o '.co'");
      }
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
         new InterfazGrafica();
      });
   }
}
