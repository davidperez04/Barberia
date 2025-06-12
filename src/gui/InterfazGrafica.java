package gui;

import datos.BaseDeDatos;
import java.awt.BorderLayout;
import java.awt.Component;
import java.time.LocalDateTime;
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
   private BaseDeDatos baseDatos = new BaseDeDatos();

   public InterfazGrafica() {
      this.setTitle("BarberíaFlow");
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
    DefaultTableModel model = new DefaultTableModel(
        new String[]{"ID", "Nombre", "Teléfono", "Especialidades", "Horarios"}, 0
    );
    JTable table = new JTable(model) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    // Renderizador para mostrar saltos de línea en la columna de horarios
    table.getColumnModel().getColumn(4).setCellRenderer((table1, value, isSelected, hasFocus, row, column) -> {
        javax.swing.JTextArea area = new javax.swing.JTextArea();
        area.setText(value != null ? value.toString() : "");
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setOpaque(true);
        if (isSelected) {
            area.setBackground(table1.getSelectionBackground());
            area.setForeground(table1.getSelectionForeground());
        } else {
            area.setBackground(table1.getBackground());
            area.setForeground(table1.getForeground());
        }
        return area;
    });

    // Llenar la tabla con los barberos y ajustar altura de filas
    for (Barbero barbero : this.baseDatos.obtenerTodosLosBarberos()) {
        String especialidades = String.join(", ", barbero.getEspecialidades());
        StringBuilder horarios = new StringBuilder();
        for (var h : barbero.getHorarioTrabajo()) {
            horarios.append(h.toString()).append("\n");
        }
        model.addRow(new Object[]{
            barbero.getId(),
            barbero.getNombre(),
            barbero.getTelefono(),
            especialidades,
            horarios.toString().trim()
        });
    }

    // Ajustar el ancho de las columnas y permitir que la tabla se expanda con el panel
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    table.getColumnModel().getColumn(0).setPreferredWidth(40);  // ID
    table.getColumnModel().getColumn(1).setPreferredWidth(120); // Nombre
    table.getColumnModel().getColumn(2).setPreferredWidth(70);  // Teléfono
    table.getColumnModel().getColumn(3).setPreferredWidth(120); // Especialidades
    table.getColumnModel().getColumn(4).setPreferredWidth(450); // Horarios

    // Ajustar altura de todas las filas según el contenido de la columna de horarios
    for (int i = 0; i < model.getRowCount(); i++) {
        ajustarAlturaFila(table, i, 4);
    }

    JButton agregar = new JButton("Agregar Barbero");
    agregar.addActionListener(e -> {
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
        Barbero nuevo = new Barbero(this.baseDatos.getNextBarberoId(), nombre, telefono);
        this.baseDatos.agregarBarbero(nuevo);
        model.addRow(new Object[]{
            nuevo.getId(),
            nombre,
            telefono,
            "",
            ""
        });
        // Ajustar altura de la nueva fila
        ajustarAlturaFila(table, model.getRowCount() - 1, 4);
    });

    JButton eliminar = new JButton("Eliminar Seleccionado");
    eliminar.addActionListener(e -> {
        int fila = table.getSelectedRow();
        if (fila >= 0) {
            int id = (Integer) model.getValueAt(fila, 0);
            this.baseDatos.eliminarBarbero(id);
            model.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        }
    });

    JButton agregarEspecialidad = new JButton("Agregar Especialidad");
    agregarEspecialidad.addActionListener(e -> {
        int fila = table.getSelectedRow();
        if (fila >= 0) {
            int id = (Integer) model.getValueAt(fila, 0);
            Barbero barbero = this.baseDatos.buscarBarberoPorId(id);
            if (barbero != null) {
                String especialidad = this.solicitarSoloLetras("Nueva especialidad:");
                if (especialidad != null && !especialidad.trim().isEmpty()) {
                    barbero.agregarEspecialidad(especialidad);
                    this.baseDatos.guardarDatos();
                    model.setValueAt(String.join(", ", barbero.getEspecialidades()), fila, 3);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un barbero para agregar especialidad.");
        }
    });

    JButton agregarHorario = new JButton("Agregar Horario");
    agregarHorario.addActionListener(e -> {
        int fila = table.getSelectedRow();
        if (fila >= 0) {
            int id = (Integer) model.getValueAt(fila, 0);
            Barbero barbero = this.baseDatos.buscarBarberoPorId(id);
            if (barbero != null) {
                String fechaStr = JOptionPane.showInputDialog(this, "Fecha (YYYY-MM-DD):");
                String horaInicio = JOptionPane.showInputDialog(this, "Hora inicio (HH:mm):");
                String horaFin = JOptionPane.showInputDialog(this, "Hora fin (HH:mm):");
                try {
                    modelo.Horario nuevoHorario = new modelo.Horario(java.sql.Date.valueOf(fechaStr), horaInicio, horaFin);
                    barbero.agregarHorario(nuevoHorario);
                    this.baseDatos.guardarDatos();
                    StringBuilder horarios = new StringBuilder();
                    for (var h : barbero.getHorarioTrabajo()) {
                        horarios.append(h.toString()).append("\n");
                    }
                    model.setValueAt(horarios.toString().trim(), fila, 4);
                    ajustarAlturaFila(table, fila, 4);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Datos de horario inválidos.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un barbero para agregar horario.");
        }
    });

    JButton verDisponibilidad = new JButton("Ver Disponibilidad");
    verDisponibilidad.addActionListener(e -> {
        int fila = table.getSelectedRow();
        if (fila >= 0) {
            int id = (Integer) model.getValueAt(fila, 0);
            Barbero barbero = this.baseDatos.buscarBarberoPorId(id);
            if (barbero != null) {
                String fechaStr = JOptionPane.showInputDialog(this, "Fecha para consultar (YYYY-MM-DD):");
                try {
                    java.util.Date fecha = java.sql.Date.valueOf(fechaStr);
                    java.util.List<modelo.Horario> disponibles = barbero.verDisponibilidad(fecha);
                    if (disponibles.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No hay horarios disponibles para esa fecha.");
                    } else {
                        StringBuilder sb = new StringBuilder("Horarios disponibles:\n");
                        for (modelo.Horario h : disponibles) {
                            sb.append(h.toString()).append("\n");
                        }
                        JOptionPane.showMessageDialog(this, sb.toString());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Fecha inválida.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un barbero para ver disponibilidad.");
        }
    });

    JPanel botones = new JPanel();
    botones.add(agregar);
    botones.add(eliminar);
    botones.add(agregarEspecialidad);
    botones.add(agregarHorario);
    botones.add(verDisponibilidad);

    JScrollPane scroll = new JScrollPane(table);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    panel.add(scroll, BorderLayout.CENTER);
    panel.add(botones, BorderLayout.SOUTH);
    return panel;
}

// Método para ajustar la altura de la fila según el contenido de la celda de horarios
private void ajustarAlturaFila(JTable table, int fila, int columna) {
    javax.swing.table.TableCellRenderer renderer = table.getCellRenderer(fila, columna);
    java.awt.Component comp = table.prepareRenderer(renderer, fila, columna);
    int altura = Math.max(40, comp.getPreferredSize().height);
    table.setRowHeight(fila, altura);
}

   private JPanel crearPanelClientes() {
      JPanel panel = new JPanel(new BorderLayout());
      // Agregamos columnas para ID, Nombre, Teléfono y Email
      DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Teléfono", "Email"}, 0);
      JTable table = new JTable(model);

      for (Cliente cliente : this.baseDatos.obtenerTodosLosClientes()) {
         model.addRow(new Object[]{
               cliente.getId(),
               cliente.getNombre(),
               cliente.getTelefono(),
               cliente.getEmail()
         });
      }

      JButton agregar = new JButton("Agregar Cliente");
      agregar.addActionListener(e -> {
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
         Cliente nuevo = new Cliente(this.baseDatos.getNextClienteId(), nombre, telefono, email);
         this.baseDatos.agregarCliente(nuevo);
         model.addRow(new Object[]{
               nuevo.getId(),
               nombre,
               telefono,
               email
         });
      });

      JButton eliminar = new JButton("Eliminar Seleccionado");
      eliminar.addActionListener(e -> {
         int fila = table.getSelectedRow();
         if (fila >= 0) {
               int id = (Integer) model.getValueAt(fila, 0);
               this.baseDatos.eliminarCliente(id);
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
    // Agregamos la columna "Descripción"
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripción", "Precio", "Duración"}, 0);
    JTable table = new JTable(model) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    // Renderizador para mostrar saltos de línea en la columna de descripción
    table.getColumnModel().getColumn(2).setCellRenderer((table1, value, isSelected, hasFocus, row, column) -> {
        javax.swing.JTextArea area = new javax.swing.JTextArea();
        area.setText(value != null ? value.toString() : "");
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setOpaque(true);
        if (isSelected) {
            area.setBackground(table1.getSelectionBackground());
            area.setForeground(table1.getSelectionForeground());
        } else {
            area.setBackground(table1.getBackground());
            area.setForeground(table1.getForeground());
        }
        return area;
    });

    for (Servicio servicio : this.baseDatos.obtenerTodosLosServicios()) {
        model.addRow(new Object[]{
            servicio.getId(),
            servicio.getNombre(),
            servicio.getDescripcion(),
            servicio.getPrecio(),
            servicio.getDuracionMinutos()
        });
    }

    // Ajustar el ancho de las columnas y permitir que la tabla se expanda con el panel
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    table.getColumnModel().getColumn(0).setPreferredWidth(40);   // ID
    table.getColumnModel().getColumn(1).setPreferredWidth(120);  // Nombre
    table.getColumnModel().getColumn(2).setPreferredWidth(300);  // Descripción (más ancha)
    table.getColumnModel().getColumn(3).setPreferredWidth(70);   // Precio
    table.getColumnModel().getColumn(4).setPreferredWidth(70);   // Duración

    // Ajustar altura de todas las filas según el contenido de la columna de descripción
    for (int i = 0; i < model.getRowCount(); i++) {
        ajustarAlturaFila(table, i, 2);
    }

    JButton agregar = new JButton("Agregar Servicio");
    agregar.addActionListener(e -> {
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
            Servicio servicio = new Servicio(this.baseDatos.getNextServicioId(), nombre, descripcion, precio, duracion);
            this.baseDatos.agregarServicio(servicio);
            model.addRow(new Object[]{servicio.getId(), nombre, descripcion, precio, duracion});
            // Ajustar altura de la nueva fila por si la descripción es larga
            ajustarAlturaFila(table, model.getRowCount() - 1, 2);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos.");
        }
    });

    JButton eliminar = new JButton("Eliminar Seleccionado");
    eliminar.addActionListener(e -> {
        int fila = table.getSelectedRow();
        if (fila >= 0) {
            int id = (Integer) model.getValueAt(fila, 0);
            this.baseDatos.eliminarServicio(id);
            model.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        }
    });

    JPanel botones = new JPanel();
    botones.add(agregar);
    botones.add(eliminar);

    JScrollPane scroll = new JScrollPane(table);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    panel.add(scroll, BorderLayout.CENTER);
    panel.add(botones, BorderLayout.SOUTH);
    return panel;
}
   private JPanel crearPanelReservas() {
      JPanel panel = new JPanel(new BorderLayout());
      DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Cliente", "Barbero", "Servicio", "Precio", "Fecha"}, 0);
      JTable table = new JTable(model);

      for (Reserva reserva : this.baseDatos.obtenerTodasLasReservas()) {
         String nombreServicio = "";
         String precioServicio = "";
         // Primero intenta obtener el servicio principal
         if (reserva.getServicio() != null) {
            nombreServicio = reserva.getServicio().getNombre();
            precioServicio = String.valueOf(reserva.getServicio().getPrecio());
         } else if (reserva.getServicios() != null && !reserva.getServicios().isEmpty()) {
            // Si no hay servicio principal, toma el primero de la lista
            Servicio servicio = reserva.getServicios().get(0);
            nombreServicio = servicio.getNombre();
            precioServicio = String.valueOf(servicio.getPrecio());
         }
         model.addRow(new Object[]{
            reserva.getIdReserva(),
            reserva.getCliente() != null ? reserva.getCliente().getNombre() : "",
            reserva.getBarbero() != null ? reserva.getBarbero().getNombre() : "",
            nombreServicio,
            precioServicio,
            reserva.getFechaHora() != null ? reserva.getFechaHora().toString() : ""
         });
      }

      JButton agregar = new JButton("Agregar Reserva");
      agregar.addActionListener(e -> {
         if (!this.baseDatos.obtenerTodosLosClientes().isEmpty() && !this.baseDatos.obtenerTodosLosBarberos().isEmpty() && !this.baseDatos.obtenerTodosLosServicios().isEmpty()) {
            Cliente cliente = (Cliente) JOptionPane.showInputDialog(this, "Selecciona el cliente:", "Cliente", 3, (Icon) null, this.baseDatos.obtenerTodosLosClientes().toArray(), (Object) null);
            Barbero barbero = (Barbero) JOptionPane.showInputDialog(this, "Selecciona el barbero:", "Barbero", 3, (Icon) null, this.baseDatos.obtenerTodosLosBarberos().toArray(), (Object) null);
            Servicio servicio = (Servicio) JOptionPane.showInputDialog(this, "Selecciona el servicio:", "Servicio", 3, (Icon) null, this.baseDatos.obtenerTodosLosServicios().toArray(), (Object) null);
            String fechaStr = JOptionPane.showInputDialog(this, "Ingresa la fecha y hora (AAAA-MM-DDTHH:MM):", LocalDateTime.now().toString().substring(0, 16));

            try {
               LocalDateTime fecha = LocalDateTime.parse(fechaStr);
               if (cliente != null && barbero != null && servicio != null) {
                  Reserva reserva = new Reserva(this.baseDatos.getNextReservaId(), fecha, cliente, barbero, servicio);
                  this.baseDatos.agregarReserva(reserva);
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
      eliminar.addActionListener(e -> {
         int fila = table.getSelectedRow();
         if (fila >= 0) {
            int id = (Integer) model.getValueAt(fila, 0);
            this.baseDatos.eliminarReserva(id);
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