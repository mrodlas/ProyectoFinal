package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.proyectofinal.databinding.ActivitySplashBinding

// Actividad de pantalla de inicio (Splash)
class SplashActivity : AppCompatActivity() {
    // Declaración de la variable binding usando ViewBinding
    public lateinit var binding: ActivitySplashBinding

    // Método llamado cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        // Instalar la pantalla de inicio (Splash)
        val screenSplash = installSplashScreen()

        // Llamar al método onCreate de la superclase
        super.onCreate(savedInstanceState)

        // Inflar el layout de la actividad usando ViewBinding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar condición para mantener la pantalla de inicio (Splash)
        screenSplash.setKeepOnScreenCondition { true }

        // Simular un retraso de 4 segundos (4000 milisegundos) antes de pasar a la siguiente actividad
        Thread.sleep(4000)

        // Crear un Intent para iniciar la MainActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        // Finalizar la actividad de la pantalla de inicio (Splash) para evitar volver a ella
        finish()
    }
}
