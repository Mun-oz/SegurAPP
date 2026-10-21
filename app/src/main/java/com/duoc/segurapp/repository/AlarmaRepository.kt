package com.duoc.segurapp.repository
import com.duoc.segurapp.model.Alarma

class AlarmaRepository {
    private val alarmas = mutableListOf<Alarma>()
    fun registrarAlarma(alarma: Alarma){
        alarmas.add(alarma)
    }

    fun obtenerAlarmas(): List<Alarma>{
        return alarmas.toList()
    }

}