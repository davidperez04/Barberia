# **BarberFlow**
## Integrantes del grupo :
#### Jesús David Pérez Duran 192480 - Desarrollador de Lógica y Base de Datos.
- Aseguró la navegación fluida del sistema a través de consola (Main.java).
- Participó en la implementación de operaciones CRUD y estructuras de datos en BaseDeDatos.
- Implementó funcionalidades específicas en los controladores.
- Desarrolló los menús interactivos para barberos, clientes, servicios y reservas.
- Apoyó en pruebas funcionales y corrección de errores lógicos.
- se encargó de elaborar la estructura del código, así como el diagrama UML.
- Participó en la modularización del código y los paquetes que contienen cada clase. 

#### Luifer Rojas Arévalo 192494 - Desarrollador de Lógica.
- Diseñó y codificó la lógica para el manejo de horarios y disponibilidad (Horario).
- Encargado del desarrollo del módulo de reservas (Reserva, EstadoReserva).
- Se encargó de la lógica de servicios (Servicio, CRUD y asignación a barberos).
- Participó en la modularización del código y los paquetes que contienen cada clase.
- Apoyó en pruebas funcionales y corrección de errores lógicos.
- Implementó funcionalidades específicas en los controladores.
- Aseguró la navegación fluida del sistema a través de consola (Main.java).

#### Sebastián León Carrascal 192506 - Desarrollador de Lógica.
- Implementó clases y métodos relacionados con la gestión de usuarios (Usuario, Cliente, Barbero).
- Apoyó en la creación del sistema de herencia y relaciones entre entidades.
- Se aseguró de que el código se viese ordenado y de que fuera mantenible.
- Apoyó en pruebas funcionales y corrección de errores lógicos.
- Se aseguró de que el desarrollo del trabajo siguiese los lineamientos del SDLC, asegurando un flujo adecuado y que se lograse lo planeado.

#### Fabian camilo Perez Galvis 192518 - Desarrollador y Diseñador de Interfaz Gráfica.
- Diseñó e implementó la interfaz gráfica del sistema utilizando Java Swing.
- Desarrolló ventanas con JFrame, pestañas con JTabbedPane, y tablas con JTable.
- Integró visualmente las funcionalidades del sistema en paneles de barberos, clientes, servicios y reservas.
- Aseguró la experiencia de usuario mediante navegación clara e interacción gráfica.
- Apoyó en pruebas funcionales y corrección de errores lógicos.
- Aseguró la navegación fluida del sistema a través de consola (Main.java).

Este es el digrama UML para las clases del sistema de gestión de reservas en una barbería
![image](https://github.com/user-attachments/assets/975f0006-340f-4df0-adc5-82663e1dd0dd)
-------------------------------------------------------------------
Código del diagrama UML en plantuml :
<pre>
@startuml
' Estilos opcionales
skinparam classAttributeIconSize 0
skinparam classFontSize 12
skinparam classAttributeFontSize 11
skinparam classMethodFontSize 11

' Enumeración
enum EstadoReserva {
  PENDIENTE
  CONFIRMADA
  CANCELADA
  COMPLETADA
}

' Clases principales
class Main {
  +main(String[] args)
}

class BaseDeDatos {
  -List<Usuario> usuarios
  -List<Barbero> barberos
  -List<Cliente> clientes
  -List<Servicio> servicios
  -List<Reserva> reservas
  -int nextClienteId
  -int nextBarberoId
  -int nextServicioId
  -int nextReservaId
  -int nextUsuarioId
  +guardarDatos()
  +cargarDatos()
  +CRUD...
  +buscarPorId()
}

abstract class Usuario {
  -int id
  -String nombre
  -String telefono
  +mostrarInformacion()
  +toString()
}

class Barbero {
  -List<String> especialidades
  -List<Horario> horarioTrabajo
  -List<Servicio> servicios
  +agregarEspecialidad(String)
  +agregarHorario(Horario)
  +agregarServicio(Servicio)
  +verDisponibilidad(Date)
  +mostrarInformacion()
  +toString()
}

class Cliente {
  -String email
  -List<Reserva> historialReservas
  +agregarReserva(Reserva)
  +cancelarReserva(int)
  +mostrarInformacion()
  +toString()
}

class Reserva {
  -int idReserva
  -LocalDateTime fechaHora
  -EstadoReserva estado
  -Cliente cliente
  -Barbero barbero
  -Servicio servicio
  -List<Servicio> servicios
  +confirmar()
  +cancelar()
  +completar()
  +calcularTotal()
  +agregarServicio(Servicio)
  +toString()
}

class Horario {
  -int id
  -Date fecha
  -String horaInicio
  -String horaFin
  -boolean disponible
  +marcarComoOcupado()
  +marcarComoDisponible()
  +esHoraValida(String)
  +toString()
}

class Servicio {
  -int id
  -String nombre
  -String descripcion
  -double precio
  -int duracionMinutos
  +setNombre(String)
  +setDescripcion(String)
  +setPrecio(double)
  +setDuracionMinutos(int)
  +toString()
}

' Menús y controladores
class BarberoMenu {
  +mostrar(BaseDeDatos, Scanner)
}
class ClienteMenu {
  +mostrar(BaseDeDatos, Scanner)
}
class ReservaMenu {
  +mostrar(BaseDeDatos, Scanner)
}
class ServicioMenu {
  +mostrar(BaseDeDatos, Scanner)
}

class BarberoController {
  +gestionar(BaseDeDatos, Scanner)
}
class ClienteController {
  +gestionar(BaseDeDatos, Scanner)
}
class ReservaController {
  +gestionar(BaseDeDatos, Scanner)
}
class ServicioController {
  +gestionar(BaseDeDatos, Scanner)
}

' Relaciones
Usuario <|-- Barbero
Usuario <|-- Cliente

Reserva --> Cliente
Reserva --> Barbero
Reserva --> Servicio
Reserva *-- "1..*" Servicio

Barbero --> "0..*" Horario
BaseDeDatos o-- Usuario
BaseDeDatos o-- Barbero
BaseDeDatos o-- Cliente
BaseDeDatos o-- Servicio
BaseDeDatos o-- Reserva

BarberoMenu ..> BarberoController
ClienteMenu ..> ClienteController
ReservaMenu ..> ReservaController
ServicioMenu ..> ServicioController

BarberoController ..> BaseDeDatos
ClienteController ..> BaseDeDatos
ReservaController ..> BaseDeDatos
ServicioController ..> BaseDeDatos

@enduml
</pre>

