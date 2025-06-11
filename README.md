# Bienvenidos a nuestro proyecto
Este es el digrama UML para las clases del sistema de gestión de reservas en una barbería
![image](https://github.com/user-attachments/assets/975f0006-340f-4df0-adc5-82663e1dd0dd)
-------------------------------------------------------------------
Código del diagrama UML en plantuml :

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

