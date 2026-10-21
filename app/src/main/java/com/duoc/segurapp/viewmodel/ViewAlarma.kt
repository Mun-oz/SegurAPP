package com.duoc.segurapp.viewmodel
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import com.duoc.segurapp.model.Alarma
import com.duoc.segurapp.repository.AlarmaRepository

class ViewAlarma(
    private val reposiory: AlarmaRepository = AlarmaRepository()
): ViewModel(){

    private val _alarmas = mutableStateListOf<Alarma>()
    val alarmas: List<Alarma>
        get() = _alarmas

    fun registrarAlarma(alarma: Alarma){
        val nuevaAlarma = alarma.copy(
            id = java.util.UUID.randomUUID().toString(),
            estado = "Pendiente"
        )

        reposiory.registrarAlarma(nuevaAlarma)
        _alarmas.add(nuevaAlarma)
    }
}