package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal.databinding.ActivityRegistroActityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// Actividad para el registro de usuarios
class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroActityBinding

    // Método llamado cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el layout de la actividad usando ViewBinding
        binding = ActivityRegistroActityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener la instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        // Configurar OnClickListener para el botón de registro
        binding.btnRegistrarse.setOnClickListener {
            // Comprobar que todos los campos del formulario están completos
            if (binding.nombre.text.isNotEmpty() && binding.apellidos.text.isNotEmpty() && binding.correoE.text.isNotEmpty()
                && binding.contrasenia.text.isNotEmpty()
            ) {
                // Crear un nuevo usuario en Firebase Authentication con el método createUserWithEmailAndPassword
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.correoE.text.toString(), binding.contrasenia.text.toString()
                )
                    .addOnCompleteListener { task ->
                        // Verificar si el registro de usuario fue exitoso
                        if (task.isSuccessful) {
                            // Si el registro fue exitoso, guardar los datos del usuario en Firebase Firestore
                            db.collection("usuarios").document(binding.correoE.text.toString())
                                .set(
                                    mapOf(
                                        "nombre" to binding.nombre.text.toString(),
                                        "apellido" to binding.apellidos.text.toString()
                                    )
                                )
                            // Iniciar la actividad ListadoActivity después del registro exitoso
                            startActivity(Intent(this, ListadoActivity::class.java))
                        } else { // Si el registro falló, mostrar un mensaje de error
                            Toast.makeText(this, "No se ha podido registrar", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            } else { // Si algún campo está vacío, mostrar un mensaje al usuario
                Toast.makeText(this, "Algún campo está vacío", Toast.LENGTH_LONG).show()
            }
        }

        // Configurar OnClickListener para el botón de volver
        binding.btnVolver.setOnClickListener {
            // Crear un Intent para volver a la MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
