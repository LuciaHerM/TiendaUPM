# 🏬 TiendaUPM – Sistema de Gestión de Tienda (POO)

**TiendaUPM** es una aplicación desarrollada para la asignatura **Programación Orientada a Objetos (POO)** en la Universidad Politécnica de Madrid.
El objetivo del proyecto es implementar un sistema completo, modular y extensible para la gestión de una tienda mediante **arquitectura orientada a objetos**, **patrones de diseño** y una **interfaz de línea de comandos (CLI)**.

📦 **Esta versión corresponde a la *Entrega 3 y final*** del proyecto, integrando:

* Modelo de dominio completo
* Sistema de comandos
* Capa de persistencia con base de datos

---

## 📌 Características principales

* ✔️ Arquitectura orientada a objetos en capas
* ✔️ Sistema de comandos extensible (patrón Command)
* ✔️ Persistencia de datos mediante DAOs
* ✔️ Gestión de productos, eventos y servicios
* ✔️ Gestión de clientes normales y de empresa
* ✔️ Gestión de cajeros y tickets
* ✔️ Control de estados de ticket
* ✔️ Manejo centralizado de excepciones
* ✔️ Preparado para ampliaciones futuras (GUI, nuevos comandos, nuevos productos)

---

## 🧱 Arquitectura del Proyecto

El proyecto se estructura en **tres grandes bloques**, cada uno reflejado en su correspondiente **diagrama UML**:

1. **Modelo de Dominio (Aplicación)**
2. **Sistema de Comandos (CLI)**
3. **Capa de Persistencia (DAO + Base de Datos)**

Cada bloque es independiente pero está correctamente acoplado mediante interfaces claras y responsabilidades bien definidas.

---

# 1️⃣ Modelo de Dominio (Aplicación)

Este módulo representa la **lógica principal del negocio** y está reflejado en el UML de la aplicación.

## 🏬 TiendaUPM

Clase principal que actúa como **fachada del sistema**.

Responsabilidades:

* Mantener el catálogo de productos
* Gestionar clientes y cajeros
* Controlar tickets activos y cerrados
* Procesar comandos introducidos por el usuario
* Inicializar y cerrar la aplicación

Es el **punto de entrada** y coordinación de toda la aplicación.

---

## 📦 Product y Jerarquía de Productos

### 🔹 Product

Clase base que representa cualquier producto de la tienda.

Atributos principales:

* `id`
* `name`
* `price`

---

### 🔹 Product_Basic

Extiende `Product` y añade:

* `category` (enum **Category**)

#### Category

Define categorías estándar:

| Categoría   |
| ----------- |
| MERCH       |
| STATIONERY  |
| CLOTHES     |
| BOOK        |
| ELECTRONICS |

---

### 🔹 Personalized

Extiende `Product_Basic` y permite:

* Personalizaciones
* Número máximo de personas

---

### 🔹 Events

Extiende `Product` para representar **eventos y servicios temporales**.

Incluye:

* Fecha de expiración
* Número máximo de participantes
* Tipo de evento
* Validaciones específicas

#### TypeEvent

| Tipo    |
| ------- |
| FOOD    |
| MEETING |

---

### 🔹 Services

Producto especial asociado a servicios, con:

* Categoría de servicio
* Descuento
* Fecha de expiración

#### Category_Service

| Categoría Servicio |
| ------------------ |
| TRANSPORT          |
| SHOWS              |
| INSURANCE          |

---

## 👤 Clientes

### 🔹 Client

Clase base de cliente.

Atributos:

* `cashId`
* Lista de tickets

Subclases:

* **NormalClient** → cliente estándar (DNI)
* **BusinessClient** → cliente empresa (NIF)

---

## 💼 Cash (Cajeros)

Representa a los **empleados de la tienda**.

Incluye:

* Identificador
* Nombre
* Lista de tickets gestionados

---

## 🧾 Ticket

Modela un ticket de compra.

Atributos:

* Lista de productos (`cart`)
* Precio total
* Descuento total
* Estado del ticket
* Cajero asociado

### 🎛️ TicketStatus

| Estado |
| ------ |
| EMPTY  |
| OPEN   |
| CLOSE  |

Subtipos:

* **TicketComunes**
* **TicketEmpresa** (con validaciones adicionales)

---

# 2️⃣ Sistema de Comandos (CLI)

Este bloque implementa el **patrón Command**, reflejado en el UML de comandos.

## 🔧 Commands

Clase abstracta que define:

```java
apply()
```

Todos los comandos concretos heredan de esta clase, permitiendo:

* Alta cohesión
* Bajo acoplamiento
* Fácil extensibilidad

---

## 📦 Comandos de Productos

* `prod add`
* `prod add food`
* `prod add meeting`
* `prod add services`
* `prod list`
* `prod update`
* `prod remove`

Cada comando tiene su propia clase (`prodAdd`, `prodUpdate`, etc.).

---

## 🧾 Comandos de Tickets

* `ticket new`
* `ticket add`
* `ticket remove`
* `ticket print`
* `ticket list`
* `ticket business new`

---

## 👤 Comandos de Clientes

* `client add`
* `client list`
* `client remove`

---

## 💼 Comandos de Cajeros

* `cash add`
* `cash list`
* `cash remove`
* `cash tickets`

---

## ⚙️ Comandos Generales

* `help`
* `echo`
* `deleteAll`
* `exit`
* `unknownCommand`

---

# 3️⃣ Capa de Persistencia

Este módulo está reflejado en el **UML de persistencia** y permite almacenar los datos de forma permanente.

## 🗄️ DatabaseManager

Clase **Singleton** responsable de:

* Crear y mantener la conexión con la base de datos
* Inicializar tablas
* Proveer conexiones a los DAOs
* Borrar datos (modo desarrollo)

---

## 📂 DAO (Data Access Object)

Cada entidad principal tiene su DAO correspondiente:

### 🔹 ProductDAO

* `save(Product)`
* `findAll()`
* `update(...)`
* `delete(id)`

---

### 🔹 TicketDAO

* Gestión completa de tickets
* Apertura y cierre
* Añadir y eliminar productos
* Consultas cruzadas con productos

---

### 🔹 ClientDAO

* Persistencia de clientes
* Relación cliente–ticket

---

### 🔹 CashDAO

* Gestión de cajeros
* Asociación con tickets

---

Los DAOs **aislan la lógica de persistencia** del modelo de dominio, cumpliendo el principio de **separación de responsabilidades**.

---

## ⚠️ Gestión de Excepciones

La aplicación utiliza una excepción personalizada:

### 🔹 TiendaUPMException

Incluye:

* Código de error
* Descripción
* Mensaje claro para el usuario

Centraliza el control de errores en toda la aplicación.

---

# 🧩 Diseño y Patrones Utilizados

* **Command** → sistema CLI
* **Singleton** → DatabaseManager
* **DAO** → persistencia
* **Herencia y Polimorfismo** → productos, clientes y tickets
* **Composición** → tickets y productos
* **Enumeraciones** → categorías y estados

---

# 🚀 Conclusión

Esta **Entrega 3** presenta una aplicación:

* Completa
* Persistente
* Modular
* Extensible
* Alineada con los principios de POO

El diseño permite futuras ampliaciones como:

* Interfaz gráfica (GUI)
* Nuevos métodos de pago
* Informes avanzados
* Conexión con servicios externos

---

# 📄 Licencia

Proyecto desarrollado exclusivamente con fines académicos para la Universidad Politécnica de Madrid (UPM).

---
* Añadir un apartado de **decisiones de diseño**
* O alinearlo palabra por palabra con los UML para defensa oral
