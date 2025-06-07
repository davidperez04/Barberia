Este es el digrama UML para las clases del sistema de gestión de reservas en una barbería
![uml-Barberia](https://github.com/user-attachments/assets/be32fed5-7985-4a7f-8db0-557799e271e9)
-------------------------------------------------------------------
# 📖 Documentación de Clases

## 📌 EstadoReserva
Enum que representa los posibles estados de una reserva en la barbería.

- **`PENDIENTE`** 🟡 → La reserva está creada pero aún no ha sido confirmada.
- **`CONFIRMADA`** ✅ → La reserva ha sido confirmada y está lista para su uso.
- **`CANCELADA`** ❌ → La reserva fue cancelada y no se llevará a cabo.
- **`COMPLETADA`** 💈 → El servicio de la reserva ha sido realizado exitosamente.

---

## 🕒 Horario
Clase que representa un horario disponible para agendar una reserva.

### 📌 Atributos
- **`fecha`** 📅 → Fecha del horario.
- **`horaInicio`** ⏰ → Hora de inicio (_formato 12 horas_, ej: `"09:00 AM"`).
- **`horaFin`** ⏰ → Hora de fin (_formato 12 horas_, ej: `"10:00 AM"`).
- **`disponible`** ✅ → Indica si el horario está disponible.

### 🛠 Métodos principales
- **`marcarComoOcupado()`** ❌ → Marca el horario como no disponible.
- **`getFecha()`** 📅 → Devuelve la fecha del horario.
- **`isDisponible()`** ✅ → Indica si el horario está disponible.

---

## 📅 Reserva
Clase que representa una reserva realizada por un cliente.

### 🔹 Atributos
- **`idReserva`** 🆔 → Identificador único de la reserva.
- **`fechaHora`** ⏳ → Fecha y hora de la reserva.
- **`estado`** 📌 → Estado actual de la reserva (`EstadoReserva`).
- **`cliente`** 👤 → Cliente que realiza la reserva.
- **`barbero`** ✂️ → Barbero asignado a la reserva.
- **`servicios`** 💼 → Lista de servicios solicitados.

### 🛠 Métodos principales
- **`confirmar()`** ✅ → Cambia el estado a `CONFIRMADA`.
- **`cancelar()`** ❌ → Cambia el estado a `CANCELADA`.
- **`completar()`** 💈 → Cambia el estado a `COMPLETADA`.
- **`calcularTotal()`** 💰 → Suma el precio de todos los servicios.
- **`agregarServicio(Servicio)`** ➕ → Agrega un servicio a la reserva.
- **`asignarBarbero(Barbero)`** ✂️ → Asigna un barbero a la reserva.

---

## 💇‍♂️ Servicio
Clase que representa un servicio o corte ofrecido por la barbería.

### 🏷️ Atributos
- **`nombre`** ✂️ → Nombre del servicio (ej: `"Corte clásico"`).
- **`precio`** 💰 → Precio del servicio.

### 🛠 Métodos principales
- **`getNombre()`** 🔍 → Devuelve el nombre del servicio.
- **`getPrecio()`** 💰 → Devuelve el precio del servicio.
- **`setNombre(String)`** ✏️ → Cambia el nombre del servicio _(no permite nulos o vacíos)_.
- **`setPrecio(double)`** 🔢 → Cambia el precio del servicio _(no permite valores negativos)_.