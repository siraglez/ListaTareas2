package com.example.listatareas2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListadoTareasActivity : AppCompatActivity() {

    private lateinit var tareaAdapter: TareaAdapter
    private val listaTareas = mutableListOf<Tarea>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_tareas)

        val btnHechas = findViewById<Button>(R.id.btnHechas)
        val btnPendientes = findViewById<Button>(R.id.btnPendientes)
        val btnAgregarTarea = findViewById<ImageButton>(R.id.btnAgregarTarea)
        val listViewTareas = findViewById<ListView>(R.id.listViewTareas)

        tareaAdapter = TareaAdapter(this, listaTareas)
        listViewTareas.adapter = tareaAdapter

        btnAgregarTarea.setOnClickListener {
            val intent = Intent(this, RegistroTareaActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_REGISTRO)
        }

        btnHechas.setOnClickListener {
            tareaAdapter.filter { it.hecha }
        }

        btnPendientes.setOnClickListener {
            tareaAdapter.filter { !it.hecha }
        }

        listViewTareas.setOnItemClickListener { _, _, position, _ ->
            val tarea = listaTareas[position]
            val intent = Intent(this, DetallesTareaActivity::class.java).apply {
                putExtra("tarea", tarea)
            }
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_REGISTRO && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Tarea>("nuevaTarea")?.let {
                listaTareas.add(it)
                tareaAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_REGISTRO = 1
    }
}
