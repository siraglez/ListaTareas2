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
                putExtra("tarea", tarea) // Pasamos el objeto tarea
            }
            startActivityForResult(intent, REQUEST_CODE_DETALLES)
        }
    }

    private fun mostrarTareas(tareas: List<Tarea>) {
        val tareasTexto = tareas.map { it.nombre }
        arrayAdapter.clear()
        arrayAdapter.addAll(tareasTexto)
        arrayAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_REGISTRO && resultCode == Activity.RESULT_OK) {
            val nuevaTarea = data?.getParcelableExtra<Tarea>("nuevaTarea")
            nuevaTarea?.let {
                listaTareas.add(it)
                mostrarTareas(listaTareas)
            } ?: Toast.makeText(this, "No se recibió la nueva tarea", Toast.LENGTH_SHORT).show()
        } else if (requestCode == REQUEST_CODE_DETALLES && resultCode == Activity.RESULT_OK) {
            val tareaModificada = data?.getParcelableExtra<Tarea>("tarea") // Recibe la tarea modificada
            tareaModificada?.let {
                // Busca la tarea en la lista y actualiza su estado
                listaTareas.find { t -> t.nombre == it.nombre }?.hecha = true
                mostrarTareas(listaTareas) // Actualiza la lista
            }
        }
    }

    companion object {
        const val REQUEST_CODE_REGISTRO = 1
        const val REQUEST_CODE_DETALLES = 2
    }
}
