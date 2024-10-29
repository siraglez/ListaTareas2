package com.example.listatareas2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetallesTareaActivity : AppCompatActivity() {

    private lateinit var tarea: Tarea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_tarea)

        tarea = intent.getParcelableExtra("tarea") ?: return

        findViewById<TextView>(R.id.tvNombreTarea).text = tarea.nombre
        findViewById<TextView>(R.id.tvDescripcion).text = tarea.descripcion
        findViewById<TextView>(R.id.tvFecha).text = tarea.fecha
        findViewById<TextView>(R.id.tvPrioridad).text = tarea.prioridad.uppercase()
        findViewById<TextView>(R.id.tvCoste).text = String.format("Tiene un coste de %.2f€", tarea.coste)

        findViewById<Button>(R.id.btnEliminar).setOnClickListener {
            val resultIntent = Intent().apply { putExtra("eliminarTarea", true) }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        findViewById<Button>(R.id.btnMarcarHecha).setOnClickListener {
            tarea.hecha = true
            setResult(RESULT_OK, Intent())
            finish()
        }

        findViewById<Button>(R.id.btnVolver).setOnClickListener {
            finish()
        }
    }
}
