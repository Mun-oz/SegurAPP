package com.duoc.segurapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.duoc.segurapp.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioViewModel(
    private val repositorio: UsuarioRepository
) : ViewModel() {

    fun registrarUsuario(
        usuario: String,
        contrasena: String,
        onResultado: (Boolean) -> Unit
    ) {
        viewModelScope.launch {

            val registrado = withContext(Dispatchers.IO) {
                repositorio.registrarUsuario(
                    usuario,
                    contrasena
                )
            }

            onResultado(registrado)
        }
    }

    fun iniciarSesion(
        usuario: String,
        contrasena: String,
        onResultado: (String?) -> Unit
    ) {
        viewModelScope.launch {

            val nombre = withContext(Dispatchers.IO) {
                repositorio.iniciarSesion(
                    usuario,
                    contrasena
                )
            }

            onResultado(nombre)
        }
    }
}

class UsuarioViewModelFactory(
    private val repositorio: UsuarioRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        require(
            modelClass.isAssignableFrom(
                UsuarioViewModel::class.java
            )
        )

        return UsuarioViewModel(repositorio) as T
    }
}
