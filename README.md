# ListaTareas2
 
Link al repositorio: https://github.com/siraglez/ListaTareas2.git

# Explicación del Código 

Este proyecto es una aplicación Android desarrollada en Kotlin que permite a los usuarios gestionar una lista de tareas. La aplicación incluye las funcionalidades para registrar, listar, y detallar tareas, así como marcarlas como completadas. A continuación, se describe el funcionamiento de cada componente principal del código:

## 1. Clase `Tarea`

La clase `Tarea` es un **data class** que representa una tarea en la aplicación. Implementa la interfaz `Parcelable` para permitir que los objetos de tipo `Tarea` sean pasados entre actividades. Contiene los siguientes atributos:
- `nombre`: Nombre de la tarea.
- `descripcion`: Descripción de la tarea.
- `fecha`: Fecha límite para la tarea.
- `prioridad`: Prioridad de la tarea.
- `coste`: Coste asociado a la tarea, que puede ser nulo.
- `hecha`: Booleano que indica si la tarea ha sido completada (por defecto es `false`).

La clase incluye un constructor secundario que recibe un objeto `Parcel` para reconstruir la tarea y métodos para escribir y leer el objeto desde un `Parcel`.

## 2. Actividad `RegistroTareaActivity`

Esta actividad permite al usuario registrar una nueva tarea. Al crear la actividad:
- Se obtienen referencias a los campos de texto y botones mediante `findViewById`.
- Se establece un **Listener** para el botón de registrar, que crea una nueva instancia de `Tarea` a partir de los datos ingresados. La tarea se envía de vuelta a la actividad anterior a través de un `Intent`.
- Un botón de cancelar permite limpiar los campos de texto si el usuario decide no registrar la tarea.

## 3. Actividad `ListadoTareasActivity`

Esta actividad muestra la lista de tareas registradas. Funciona de la siguiente manera:
- Se inicializa una lista mutable de tareas y un `ArrayAdapter` para mostrar los nombres de las tareas en un `ListView`.
- Los botones permiten filtrar las tareas completadas o pendientes, mostrando solo las tareas correspondientes.
- Al hacer clic en un ítem de la lista, se abre `DetallesTareaActivity` para ver más información sobre la tarea seleccionada.
- Implementa el método `onActivityResult` para manejar el resultado del registro de nuevas tareas y la modificación de tareas existentes.

## 4. Actividad `DetallesTareaActivity`

Esta actividad muestra los detalles de una tarea seleccionada. Al crear la actividad:
- Se recupera el objeto `Tarea` enviado a través del `Intent`.
- Se muestran los detalles de la tarea, como nombre, descripción, fecha, prioridad y coste.
- Los botones permiten marcar la tarea como completada o eliminarla. Cuando una tarea es marcada como completada, se actualiza el estado de la tarea y se envía el resultado de vuelta a la actividad anterior.

## Resumen

La aplicación utiliza la interacción entre actividades para gestionar la información de las tareas, empleando `Parcelable` para facilitar el paso de datos. La lógica de la aplicación se centra en el manejo de listas de tareas, permitiendo al usuario registrar, visualizar y modificar sus tareas de manera eficiente.
