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
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

### Problema Identificado:

En el contexto de las barberías, la gestión de barberos, clientes, reservas y servicios representa un desafío significativo cuando se realiza de manera manual o mediante sistemas poco eficientes. Esta situación genera problemas que afectan tanto la administración interna como la experiencia del cliente. La falta de un sistema centralizado dificulta el registro y seguimiento de los horarios de los barberos, las especialidades que ofrecen y las reservas realizadas por los clientes, lo que conduce a una administración desordenada y poco profesional. Además, la gestión manual incrementa el riesgo de errores, como reservas duplicadas, pérdida de información importante y dificultades para asignar horarios y servicios de manera adecuada, lo que impacta negativamente en la calidad del servicio y la confianza de los clientes.

Por otro lado, los procesos manuales requieren una inversión significativa de tiempo, que podría destinarse a mejorar la experiencia del cliente o a optimizar otros aspectos del negocio. A medida que la barbería crece, manejar mayores volúmenes de información se vuelve cada vez más complicado sin un sistema centralizado que permita una administración eficiente y escalable. Estos problemas subrayan la necesidad de una solución tecnológica que automatice y optimice la gestión de barberías, mejorando la organización, reduciendo errores y facilitando el crecimiento del negocio.

### Solución propuesta:

El sistema de Gestión de Barbería se presenta como una solución integral para automatizar y optimizar la administración de barberías. Este programa ha sido diseñado para abordar las necesidades específicas de organización y eficiencia en este tipo de negocios, ofreciendo herramientas que simplifican los procesos operativos y mejoran la experiencia tanto de los administradores como de los clientes.

La solución permite gestionar de manera eficiente a los barberos, proporcionando funcionalidades para agregar, eliminar y listar sus datos, asignar horarios y especialidades, y mantener un registro organizado de su información. Esto asegura una administración profesional y estructurada de los recursos humanos de la barbería.

Asimismo, el sistema facilita la gestión de clientes, permitiendo registrar su información, mantener un historial detallado de reservas y garantizar una experiencia personalizada. Esto contribuye a mejorar la relación con los clientes y a ofrecer un servicio más enfocado en sus necesidades.

En cuanto a las reservas, el programa permite realizar, confirmar, cancelar y completar citas, asegurando que los horarios de los barberos y las preferencias de los clientes estén perfectamente sincronizados. Esto elimina conflictos de horarios y optimiza la operación diaria de la barbería.

Además, el sistema incluye herramientas para la gestión de servicios, permitiendo a los administradores definir los servicios ofrecidos, establecer precios y duración, y asignarlos a los barberos disponibles. Esto asegura una administración clara y profesional de las opciones disponibles para los clientes.

Toda la información se centraliza en una base de datos, lo que facilita el acceso, la modificación y la consulta de datos en tiempo real. Esto garantiza la integridad de la información y permite tomar decisiones basadas en datos precisos y actualizados.

Finalmente, el programa cuenta con una interfaz intuitiva, diseñada con menús claros y opciones fáciles de usar, lo que reduce la curva de aprendizaje para los usuarios y asegura que el sistema pueda ser utilizado de manera eficiente incluso por personas sin experiencia técnica. En conjunto, el sistema de Gestión de Barbería representa una herramienta poderosa para mejorar la organización, reducir errores y optimizar la operación de las barberías.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
### Explicación de las Clases y Métodos Principales: 
El programa está diseñado para gestionar una barbería, permitiendo administrar barberos, clientes, reservas y servicios. A continuación, se describen las clases y métodos principales que conforman el sistema:

#### Clase Main
##### Descripción:
Es la clase principal del programa y el punto de entrada. Su función es mostrar el menú principal y delegar las operaciones específicas a los menús correspondientes.
##### Métodos:
main(String[] args)
Este método inicializa el sistema creando una instancia de la clase BaseDeDatos para gestionar la persistencia de datos y un objeto Scanner para la entrada del usuario.
Muestra un menú principal con las siguientes opciones:
- Gestionar barberos.
- Gestionar clientes.
- Gestionar reservas.
- Gestionar servicios.
- Salir.
Según la opción seleccionada, delega la gestión a las clases de menú específicas (BarberoMenu, ClienteMenu, ReservaMenu, ServicioMenu).
Al salir, guarda los datos en la base de datos y cierra el programa.

#### Clase BaseDeDatos
##### Descripción:
Es la clase encargada de la persistencia de datos. Almacena y gestiona la información de barberos, clientes, reservas y servicios.
##### Métodos principales:
- agregarBarbero(Barbero barbero): Agrega un nuevo barbero a la lista de barberos.
- eliminarBarbero(int id): Elimina un barbero por su ID.
- obtenerTodosLosBarberos(): Devuelve una lista de todos los barberos registrados.
- guardarDatos(): Serializa los datos y los guarda en un archivo para persistencia.
- cargarDatos(): Deserializa los datos desde un archivo al iniciar el programa.

#### Clases Principales del Paquete modelo
El paquete modelo contiene las clases que representan las entidades principales del sistema. Estas clases encapsulan los datos y comportamientos relacionados con los barberos, clientes, reservas, servicios y otros elementos esenciales de la barbería.

#### Clase Barbero
##### Descripción:
Representa a un barbero de la barbería, con especialidades, horarios y servicios asignados.
##### Atributos:
List<String> especialidades: Lista de especialidades del barbero.
List<Horario> horarioTrabajo: Lista de horarios disponibles para el barbero.
List<Servicio> servicios: Lista de servicios que el barbero puede realizar.
#### Métodos:
- agregarEspecialidad(String especialidad):Agrega una especialidad a la lista de especialidades del barbero.
- agregarHorario(Horario horario):Agrega un horario a la lista de horarios del barbero.
- agregarServicio(Servicio servicio):Asigna un servicio al barbero.
- verDisponibilidad(Date fecha):Verifica si el barbero está disponible en una fecha específica.
- mostrarInformacion():Muestra la información detallada del barbero.

#### Clase Cliente
##### Descripción:
Representa a un cliente de la barbería, con un historial de reservas.
##### Atributos:
String email: Correo electrónico del cliente.
List<Reserva> historialReservas: Lista de reservas realizadas por el cliente.
##### Métodos:
- agregarReserva(Reserva reserva):Agrega una reserva al historial del cliente.
- cancelarReserva(int idReserva):Cancela una reserva específica del cliente.
- mostrarInformacion():Muestra la información detallada del cliente.

#### Clase Reserva
##### Descripción:
Representa una reserva realizada por un cliente para un servicio específico.
##### Atributos:
int idReserva: Identificador único de la reserva.
LocalDateTime fechaHora: Fecha y hora de la reserva.
EstadoReserva estado: Estado actual de la reserva (pendiente, confirmada, cancelada, completada).
Cliente cliente: Cliente que realizó la reserva.
Barbero barbero: Barbero asignado a la reserva.
Servicio servicio: Servicio solicitado en la reserva.
##### Métodos:
- confirmar():Cambia el estado de la reserva a "confirmada".
- cancelar():Cambia el estado de la reserva a "cancelada".
- completar():Cambia el estado de la reserva a "completada".
- calcularTotal():Calcula el costo total de la reserva.
- agregarServicio(Servicio servicio):Agrega un servicio adicional a la reserva.

#### Clase Servicio
##### Descripción:
Representa un servicio ofrecido por la barbería.
##### Atributos:
int id: Identificador único del servicio.
String nombre: Nombre del servicio.
String descripcion: Descripción del servicio.
double precio: Precio del servicio.
int duracionMinutos: Duración del servicio en minutos.
##### Métodos:
- setNombre(String nombre):Establece el nombre del servicio.
- setDescripcion(String descripcion):Establece la descripción del servicio.
- setPrecio(double precio):Establece el precio del servicio.
- setDuracionMinutos(int duracion):Establece la duración del servicio.
- toString():Devuelve una representación en texto del servicio.

#### Clase Horario
#### Descripción:
Representa un horario disponible para un barbero.
##### Atributos:
int id: Identificador único del horario.
Date fecha: Fecha del horario.
String horaInicio: Hora de inicio del horario.
String horaFin: Hora de fin del horario.
boolean disponible: Indica si el horario está disponible.
##### Métodos:
- marcarComoOcupado():Marca el horario como ocupado.
- marcarComoDisponible():Marca el horario como disponible.
- esHoraValida(String hora):Verifica si una hora es válida dentro del horario.
- toString():Devuelve una representación en texto del horario.

#### Clase EstadoReserva
##### Descripción:
Es un enumerador que define los posibles estados de una reserva.
##### Valores:
- PENDIENTE: La reserva está pendiente de confirmación.
- CONFIRMADA: La reserva ha sido confirmada.
- CANCELADA: La reserva ha sido cancelada.
- COMPLETADA: La reserva ha sido completada.
Estas clases del paquete modelo representan las entidades fundamentales del sistema y encapsulan los datos y comportamientos necesarios para la gestión de la barbería..

#### Clases Principales del Paquete controller
El paquete controller contiene las clases encargadas de implementar la lógica de negocio del sistema. Estas clases interactúan con la base de datos y gestionan las operaciones relacionadas con los barberos, clientes, reservas y servicios.
#### Clase BarberoController
##### Descripción:
Gestiona las operaciones relacionadas con los barberos, como agregar, eliminar, listar, asignar horarios y especialidades.
##### Métodos principales:
- gestionar(BaseDeDatos baseDeDatos, Scanner scanner):Muestra un menú para realizar las operaciones relacionadas con los barberos. Las opciones incluyen:
  - Agregar un nuevo barbero, validando el formato del número de teléfono.
  - Eliminar un barbero por su ID.
  - Listar todos los barberos registrados.
  - Asignar horarios y especialidades a un barbero.
Este método utiliza un bucle para permitir múltiples operaciones hasta que el usuario decida salir.

#### Clase ClienteController
##### Descripción:
Gestiona las operaciones relacionadas con los clientes, como registrar información y consultar su historial de reservas.
##### Métodos principales:
- gestionar(BaseDeDatos baseDeDatos, Scanner scanner):Muestra un menú para realizar las operaciones relacionadas con los clientes. Las opciones incluyen:
  - Registrar un nuevo cliente, validando su información.
  - Consultar el historial de reservas de un cliente.
  - Eliminar un cliente por su ID.
Este método interactúa con la base de datos para realizar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

#### Clase ReservaController
##### Descripción:
Gestiona las operaciones relacionadas con las reservas realizadas por los clientes.
##### Métodos principales:
gestionar(BaseDeDatos baseDeDatos, Scanner scanner):Muestra un menú para realizar las operaciones relacionadas con las reservas. Las opciones incluyen:
  -  Crear una nueva reserva, asignando un barbero y un servicio.
  -  Confirmar, cancelar o completar una reserva existente.
  -  Consultar todas las reservas realizadas.
Este método asegura que los horarios de los barberos y las preferencias de los clientes estén sincronizados.

#### Clase ServicioController
##### Descripción:
Gestiona las operaciones relacionadas con los servicios ofrecidos por la barbería.
##### Métodos principales:
gestionar(BaseDeDatos baseDeDatos, Scanner scanner):Muestra un menú para realizar las operaciones relacionadas con los servicios. Las opciones incluyen:
  - Agregar un nuevo servicio, definiendo su nombre, descripción, precio y duración.
  - Modificar los detalles de un servicio existente.
  - Eliminar un servicio por su ID.
  - Listar todos los servicios disponibles.
Este método interactúa con la base de datos para mantener actualizada la información de los servicios.

#### Relación entre las Clases del Paquete controller
- Cada clase del paquete controller se encarga de gestionar una entidad específica del sistema (Barbero, Cliente, Reserva, Servicio).
- Todas las clases interactúan con la clase BaseDeDatos para realizar las operaciones CRUD y garantizar la persistencia de los datos.
Estas clases son invocadas desde el menú principal (Main) para ejecutar las operaciones seleccionadas por el usuario.
#### Flujo General de las Clases del Paquete controller
- El usuario selecciona una opción en el menú principal.
- La clase correspondiente del paquete controller es invocada para gestionar la entidad seleccionada.
- La clase realiza las operaciones necesarias interactuando con la base de datos y validando la entrada del usuario.
- Los resultados de las operaciones se reflejan en la base de datos y se muestran al usuario.
Estas clases encapsulan la lógica de negocio del sistema, asegurando que las operaciones se realicen de manera eficiente y organizada.
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
### Instrucciones para Usar el Programa
El sistema de Gestión de Barbería es una herramienta interactiva que permite administrar barberos, clientes, reservas y servicios de manera sencilla. Sigue estos pasos para utilizar el programa:
#### 1. Inicio del Programa
Ejecuta el programa desde tu entorno de desarrollo o terminal.
Al iniciar, se mostrará un menú principal con las opciones disponibles.
#### 2. Menú Principal
El programa mostrará las siguientes opciones en pantalla:
##### Gestionar barberos:
Permite agregar, eliminar, listar barberos, asignar horarios y especialidades.
- Gestionar clientes: Facilita el registro de clientes y la consulta de su historial de reservas.
- Gestionar reservas: Proporciona herramientas para crear, confirmar, cancelar y completar reservas.
- Gestionar servicios: Permite definir servicios, establecer precios y duración, y asignarlos a barberos.
- Salir: Finaliza el programa y guarda automáticamente los datos.
#### 3. Selección de Opciones
Ingresa el número correspondiente a la opción que deseas realizar.
Sigue las instrucciones que aparecerán en pantalla para completar la operación.
#### 4. Finalización
Para salir del programa, selecciona la opción "5. Salir".
El sistema guardará automáticamente todos los datos antes de cerrar.
Con estas instrucciones, podrás utilizar el sistema de Gestión de Barbería de manera rápida y sencilla.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

