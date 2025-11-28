# 🏬 TiendaUPM – Sistema de Gestión de Tienda (POO)

**TiendaUPM** es una aplicación desarrollada para la asignatura **Programación Orientada a Objetos (POO)**.
Su objetivo es ofrecer una plataforma modular y extensible para gestionar productos, clientes, cajeros, tickets y eventos dentro de la **Tienda UPM**, utilizando una **interfaz de línea de comandos (CLI)**.

Este proyecto corresponde a la **Entrega E2 (funcionalidad práctica)** del desarrollo incremental planteado para el curso.

---

## 📌 Características principales

* ✔️ Arquitectura completamente orientada a objetos
* ✔️ Sistema de comandos extensible mediante clases especializadas
* ✔️ Gestión de productos y categorías
* ✔️ Creación y edición de tickets
* ✔️ Registro de clientes
* ✔️ Gestión del personal (cajeros)
* ✔️ Soporte para productos especiales tipo evento
* ✔️ Código preparado para futuras capas visuales (GUI)

---

## 🧱 Arquitectura del Proyecto

El proyecto se divide en **dos grandes módulos**:

1. **Modelo de dominio** (TiendaUPM, Product, Ticket, Client, Cash...)
2. **Sistema de comandos** (Commands, prodAdd, ticketPrint, cashList...)

Los siguientes diagramas UML (no incluidos en el README, pero usados para el diseño) detallan la estructura interna.

---

# 1️⃣ Modelo de Dominio

### 🏬 TiendaUPM

Clase principal que coordina la aplicación.

Gestiona:

* Catálogo de productos
* Lista de clientes
* Cajeros (empleados de la tienda)
* Tickets en curso y finalizados
* Ejecución de comandos

Es responsable del **estado general de la tienda**.

---

### 📦 Product

Representa cualquier producto estándar de la tienda.

Atributos principales:

* `id`
* `name`
* `category` (enum **Category**)
* `price`
* `personalizaciones` (si aplica)

---

### 📚 Category

Enumeración que define **categorías generales de producto**.

| Categoría   |
| ----------- |
| MERCH       |
| STATIONERY  |
| CLOTHES     |
| BOOK        |
| ELECTRONICS |

---

### 🎟️ Events y TypeEvent

`Events` extiende `Product` para soportar servicios/eventos especiales.
Incluye:

* Fecha de expiración
* Número de asistentes o aforo
* Cálculo dinámico de precio
* Tipo de evento

**TypeEvent** define tipos específicos:

| Tipo de Evento |
| -------------- |
| FOOD           |
| MEETING        |

---

### 👤 Client

Define a un cliente registrado.

Atributos:

* `DNI`
* `cashId` (cajero asignado)
* Lista de tickets asociados

---

### 💼 Cash (Cajeros)

Representa a los **empleados de la tienda**, no a máquinas.

Un cajero contiene:

* `id`
* `name`
* Lista de tickets gestionados

---

### 🧾 Ticket

Modela un ticket de compra.

Incluye:

* Productos añadidos (`cart`)
* Total (`totalPrice`)
* Descuento total (`totalDiscount`)
* ID
* Cajero asociado
* Estado del ticket (`TicketStatus`)

---

### 🎛️ TicketStatus

Estados del ticket:

| Estado    |
| --------- |
| **EMPTY** |
| **OPEN**  |
| **CLOSE** |

---

# 2️⃣ Sistema de Comandos (CLI)

El sistema está construido mediante una jerarquía de clases que extienden la abstracción:

### **Commands**

* Define el método `apply()`
* Cada comando lo implementa según su función
* Facilita añadir nuevas órdenes sin modificar la arquitectura

---

## 📦 Comandos de Productos

| Comando                                | Descripción                  |
| -------------------------------------- | ---------------------------- |
| `prod add "<name>" <category> <price>` | Añade un producto            |
| `prod add food ...`                    | Añade un producto tipo FOOD  |
| `prod add meeting ...`                 | Añade un evento tipo MEETING |
| `prod list`                            | Lista los productos          |
| `prod update <field> <id> <value>`     | Modifica un producto         |
| `prod remove <id>`                     | Elimina un producto          |

---

## 🧾 Comandos de Tickets

| Comando                | Descripción                |
| ---------------------- | -------------------------- |
| `ticket new`           | Inicia un nuevo ticket     |
| `ticket add <id>`      | Añade un producto          |
| `ticket remove <id>`   | Elimina un producto        |
| `ticket print`         | Imprime el ticket actual   |
| `ticket list <cashId>` | Lista tickets de un cajero |

---

## 👤 Comandos de Clientes

| Comando               | Descripción        |
| --------------------- | ------------------ |
| `client add`          | Añade un cliente   |
| `client list`         | Lista los clientes |
| `client remove <dni>` | Elimina un cliente |

---

## 💼 Comandos de Cajeros

| Comando             | Descripción                 |
| ------------------- | --------------------------- |
| `cash add`          | Añade un nuevo cajero       |
| `cash list`         | Muestra los cajeros         |
| `cash remove <id>`  | Elimina un cajero           |
| `cash tickets <id>` | Muestra tickets gestionados |

---

## ⚙️ Comandos Generales

| Comando         | Descripción                |
| --------------- | -------------------------- |
| `help`          | Lista comandos disponibles |
| `echo "<text>"` | Muestra texto              |
| `exit`          | Cierra la aplicación       |

---

# 🧩 Diseño Extensible

El proyecto está diseñado para que futuras entregas (E3) puedan añadir:

* Nuevos tipos de productos
* Más comandos
* Métodos de pago
* Sistema gráfico (GUI)
* Conexión con base de datos

Gracias al uso de herencia, composición y abstracciones limpias, la arquitectura permite ampliar funcionalidades sin romper el diseño actual.

---

# 📄 Licencia

Este proyecto ha sido desarrollado exclusivamente para fines académicos en la UPM.

---
