package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

// Actividad principal donde los usuarios pueden iniciar sesión o registrarse
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    // Método llamado cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el layout de la actividad usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar OnClickListener para el botón de iniciar sesión
        binding.btnAcceder.setOnClickListener {
            login()
        }

        // Configurar OnClickListener para el botón de registrarse
        binding.btnRegistrarse.setOnClickListener {
            registro()
        }

        binding.btnOlvidarContrasenia.setOnClickListener {
            startActivity(Intent(this, OlvidarContrasenia::class.java))
        }
    }

    // Método para iniciar sesión
    private fun login() {
        // Comprobar que los campos de correo y contraseña no estén vacíos
        if (binding.etUsuario.text.isNotEmpty() && binding.etContrasenia.text.isNotEmpty()) {
            // Iniciar sesión con el método signInWithEmailAndPassword de FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.etUsuario.text.toString(),
                binding.etContrasenia.text.toString()
            )
                .addOnCompleteListener { task ->
                    // Verificar si la autenticación tuvo éxito
                    if (task.isSuccessful) {
                        // Si la autenticación fue exitosa, iniciar la actividad ListadoActivity
                        val intent = Intent(this, ListadoActivity::class.java)
                        // Mostrar un mensaje cuando haya iniciado sesión correctamente
                        Toast.makeText(this, "Ha iniciado sesión correctamente", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, ListadoActivity::class.java))
                    } else {
                        // Si la autenticación falló, mostrar un mensaje de error al usuario
                        Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            // Si algún campo está vacío, mostrar un mensaje al usuario
            Toast.makeText(this, "Alguno de los campos está vacío", Toast.LENGTH_LONG).show()
        }
    }
    // Método para iniciar la actividad de registro
    private fun registro() {
        startActivity(Intent(this, RegistroActivity::class.java))
    }
}
