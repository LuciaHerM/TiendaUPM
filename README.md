Este proyecto forma parte de la práctica de la asignatura de “Programación Orientada a Objetos” (POO). El objetivo general es informatizar la venta de diversos artículos (merchandising, papelería, ropa, libros, electrónica y, a futuro, otros servicios) para la Tienda UPM.
La aplicación está diseñada como una interfaz de línea de comandos (CLI), sobre la cual se espera añadir una capa visual externa en fases futuras. El desarrollo es incremental, constando de tres iteraciones principales: E1 (funcionalidad mínima), E2 (funcionalidad práctica) y E3 (funcionalidad completa).
Esta entrega actual (E1) representa el Proyecto base de funcionalidad mínima.

El modelo del proyecto se basa en las siguientes clases y relaciones clave:
Clase / Enumeración -> Propósito
TiendaUPM -> Clase principal que gestiona el estado de la aplicación, el catálogo de productos (products) y el ticket activo (ticket). Contiene la lógica de los comandos CLI.
Product -> Define las propiedades básicas de un producto (ID, nombre, categoría, precio).
Ticket -> Gestiona la lista de productos en la cesta (cart), el precio total (totalPrice) y el descuento total (totalDiscount).
Category -> Enumeración que lista las categorías de productos válidas.
Relaciones principales: TiendaUPM se relaciona con Ticket y Product. Ticket se relaciona con Product, y Product utiliza Category.

La aplicación debe implementar los siguientes comandos:
Comando -> Descripción
'prod add <id> "<nombre>" <categoria> <precio>' -> Agrega un nuevo producto al catálogo.
'prod list' -> Lista los productos actuales en el catálogo.
'prod update <id> NAME|CATEGORY|PRICE <value>' -> Actualiza un campo de un producto existente. Campos válidos: NAME, CATEGORY, PRICE.
'prod remove <id>' -> Elimina un producto del catálogo.
'ticket new' -> Resetea e inicia un nuevo ticket en curso.
'ticket add <prodId> <cantidad>' -> Agrega la cantidad especificada de un producto al ticket.
'ticket remove <prodId>' -> Elimina todas las apariciones de ese producto en el ticket.
'ticket print' -> Imprime la factura del ticket actual.
'help' -> Lista todos los comandos disponibles.
'echo “<texto>”' -> Imprime el texto proporcionado.
'exit' -> Cierra la aplicación.

