package com.example.listatareas2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListadoTareasActivity : AppCompatActivity() {

    private val listaTareas = mutableListOf<Tarea>()
    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_tareas)

        val btnHechas = findViewById<Button>(R.id.btnHechas)
        val btnPendientes = findViewById<Button>(R.id.btnPendientes)
        val btnAgregarTarea = findViewById<Button>(R.id.btnAdd)
        val listViewTareas = findViewById<ListView>(R.id.listViewTareas)

        // Inicializamos el ArrayAdapter
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listViewTareas.adapter = arrayAdapter

        // Manejo del botón para agregar tarea
        btnAgregarTarea.setOnClickListener {
            val intent = Intent(this, RegistroTareaActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_REGISTRO)
        }

        // Filtrar tareas hechas
        btnHechas.setOnClickListener {
            val hechas = listaTareas.filter { it.hecha }
            mostrarTareas(hechas)
        }

        // Filtrar tareas pendientes
        btnPendientes.setOnClickListener {
            val pendientes = listaTareas.filter { !it.hecha }
            mostrarTareas(pendientes)
        }

        // Manejo de clic en un item de la lista
        listViewTareas.setOnItemClickListener { _, _, position, _ ->
            val tarea = listaTareas[position]
            val intent = Intent(this, DetallesTareaActivity::class.java).apply {
                putExtra("tareaNombre", tarea.nombre)
                putExtra("tareaDescripcion", tarea.descripcion)
                putExtra("tareaFecha", tarea.fecha)
                putExtra("tareaPrioridad", tarea.prioridad)
                putExtra("tareaCoste", tarea.coste)
                putExtra("tareaHecha", tarea.hecha)
            }
            startActivity(intent)
        }
    }

    private fun mostrarTareas(tareas: List<Tarea>) {
        val tareasTexto = tareas.map {
            "${it.nombre} - ${it.descripcion} - ${it.fecha} - Prioridad: ${it.prioridad} - Coste: ${it.coste}€"
        }
        arrayAdapter.clear()
        arrayAdapter.addAll(tareasTexto)
        arrayAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_REGISTRO && resultCode == Activity.RESULT_OK) {
            val nuevaTarea = data?.getSerializableExtra("nuevaTarea") as? Tarea
            nuevaTarea?.let {
                listaTareas.add(it)
                mostrarTareas(listaTareas)
            } ?: Toast.makeText(this, "No se recibió la nueva tarea", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val REQUEST_CODE_REGISTRO = 1
    }
}
