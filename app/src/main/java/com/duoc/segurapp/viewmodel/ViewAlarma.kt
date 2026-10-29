package com.duoc.segurapp.viewmodel
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.duoc.segurapp.model.Alarma
import com.duoc.segurapp.repository.AlarmaRepository

class ViewAlarma(
    private val repositorio: AlarmaRepository = AlarmaRepository()
) : ViewModel() {
    private val _alarmas = mutableStateListOf<Alarma>()
    val alarmas: List<Alarma>
        get() = _alarmas
    fun registrarAlarma(alarma: Alarma) {
        val nuevaAlarma = alarma.copy(
            id = java.util.UUID.randomUUID().toString(),
            estado = "Pendiente"
        )
        repositorio.registrarAlarma(nuevaAlarma)
        _alarmas.add(nuevaAlarma)
    }

    fun confirmarAlarma(id: String){
        val indice = _alarmas.indexOfFirst { it.id == id }

        if(indice != -1){
            val alarma = _alarmas[indice]

            if(alarma.estado == "Pendiente"){
                val alarmaConfirmada = alarma.copy(estado = "Confirmada")

                repositorio.actualizarAlarma(alarmaConfirmada)
                _alarmas[indice] = alarmaConfirmada
            }
        }
    }
}