package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal.databinding.ActivityOlvidarContraseniaBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import org.checkerframework.checker.nullness.qual.NonNull

class OlvidarContrasenia : AppCompatActivity() {

    lateinit var binding: ActivityOlvidarContraseniaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOlvidarContraseniaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRecuperar.setOnClickListener {
            if (binding.etCorreo.text.isNotEmpty()) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(binding.etCorreo.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Correo enviado", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Correo no enviado", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Campo vacío", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnVolverMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}