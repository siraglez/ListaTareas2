package com.example.listatareas2

data class Tarea(
    val nombre: String,
    val descripcion: String,
    val fecha: String,
    val prioridad: String,
    val coste: Double,
    var hecha: Boolean = false
)
