package com.example.listatareas2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegistroTareaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_tarea)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val etFecha = findViewById<EditText>(R.id.etFecha)
        val etPrioridad = findViewById<EditText>(R.id.etPrioridad)
        val etCoste = findViewById<EditText>(R.id.etCoste)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnCancelar = findViewById<Button>(R.id.btnCancelar)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val descripcion = etDescripcion.text.toString()
            val fecha = etFecha.text.toString()
            val prioridad = etPrioridad.text.toString()
            val coste = etCoste.text.toString().toDoubleOrNull()

            val nuevaTarea = Tarea(nombre, descripcion, fecha, prioridad, coste)
            val resultIntent = Intent().apply {
                putExtra("nuevaTarea", nuevaTarea) // Asegúrate de enviar como Parcelable
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        btnCancelar.setOnClickListener {
            etNombre.text.clear()
            etDescripcion.text.clear()
            etFecha.text.clear()
            etPrioridad.text.clear()
            etCoste.text.clear()
        }
    }
}
