package com.example.proyectofinal

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

// Clase base para actividades con menús
open class ActivityConMenus : AppCompatActivity() {
    companion object {
        var activityActual = 0  // Variable estática para almacenar la actividad actual
    }

    // Método llamado cuando se crea el menú de opciones en la barra de herramientas
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú desde el archivo XML
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)

        // Desactivar la opción de la actividad en la que ya estamos
        for (i in 0 until menu.size()) {
            if (i == activityActual) menu.getItem(i).isEnabled = false
            else menu.getItem(i).isEnabled = true
        }

        return true
    }

    // Método llamado cuando se selecciona una opción del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.inicio -> {
                // Abrir la pantalla del listado de viviendas
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                activityActual = 0
                startActivity(intent)
                true
            }

            R.id.anadir_vivienda -> {
                // Abrir la pantalla del formulario para añadir viviendas
                val intent = Intent(this, AnadirViviendaActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                activityActual = 1
                startActivity(intent)
                true
            }

            R.id.actualizar_vivienda -> {
                // Abrir la pantalla para actualizar viviendas
                val intent = Intent(this, ActualizarViviendaActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                activityActual = 2
                startActivity(intent)
                true
            }

            R.id.eliminar_vivienda -> {
                // Abrir la pantalla para eliminar viviendas
                val intent = Intent(this, EliminarViviendaActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                activityActual = 3
                startActivity(intent)
                true
            }

            R.id.cerrar_sesion -> {
                // Cerrar sesión y abrir la pantalla de inicio de sesión
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                activityActual = 4
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
