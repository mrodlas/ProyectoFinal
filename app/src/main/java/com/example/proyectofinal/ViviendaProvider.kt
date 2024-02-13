package com.example.proyectofinal

// Clase que proporciona una lista estática de viviendas
class ViviendaProvider {
    // Objeto compañero para contener la lista de viviendas
    companion object {
        // Lista mutable de viviendas, inicialmente vacía
        var viviendaList = listOf<Vivienda>(
            // Puedes agregar viviendas a esta lista aquí
        )
    }
}
