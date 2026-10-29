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

    fun actualizarAlarma(alarmaActualizada: Alarma){
        val indice = alarmas.indexOfFirst { it.id == alarmaActualizada.id }
        if(indice != -1){
            alarmas[indice] = alarmaActualizada
        }
    }


    fun buscarAlarmaPorId(id: String): Alarma? {
        return alarmas.find { it.id == id }
    }

    fun obtenerAlarmasPorEstado(estado: String): List<Alarma> {
        return alarmas.filter { it.estado == estado }
    }

}